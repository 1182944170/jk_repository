package com.rpframework.core.mybatis.plugin.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapper;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapper;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 通过注解生成sql
 * 
 * @author david
 * 
 */
public class SqlBuilder {
    /**
     * 缓存TableMapper
     */
    private static HashMap<Class<?>, TableMapper> tableMapperCache = new HashMap<Class<?>, TableMapper>(128);

    /**
     * 由传入的dto对象的class构建TableMapper对象，构建好的对象存入缓存中，以后使用时直接从缓存中获取
     * 
     * @param dtoClass
     * @return TableMapper
     */
    private static TableMapper buildTableMapper(Class<?> dtoClass) {

        HashMap<String, FieldMapper> fieldMapperCache = null;
        ArrayList<FieldMapper> fieldMapperList = null;
        Field[] fields = null;

        FieldMapperAnnotation fieldMapperAnnotation = null;
        FieldMapper fieldMapper = null;
        TableMapper tableMapper = null;
        synchronized (tableMapperCache) {
            tableMapper = tableMapperCache.get(dtoClass);
            if (tableMapper != null) {
                return tableMapper;
            }
            tableMapper = new TableMapper();
            Annotation[] classAnnotations = dtoClass.getDeclaredAnnotations();
            if (classAnnotations.length == 0) {
                throw new RuntimeException("Class " + dtoClass.getName()
                        + " has no annotation, I can't build 'TableMapper' for it.");
            }
            for (Annotation an : classAnnotations) {
                if (an instanceof TableMapperAnnotation) {
                    tableMapper.setTableMapperAnnotation(an);
                }
            }
            if (tableMapper.getTableMapperAnnotation() == null) {
                throw new RuntimeException("Class " + dtoClass.getName() + " has no 'TableMapperAnnotation', "
                        + "which has the database table information," + " I can't build 'TableMapper' for it.");
            }
            fields = dtoClass.getDeclaredFields();
            fieldMapperCache = new HashMap<String, FieldMapper>();
            fieldMapperList = new ArrayList<FieldMapper>();
            Annotation[] fieldAnnotations = null;
            for (Field field : fields) {
                fieldAnnotations = field.getDeclaredAnnotations();
                if (fieldAnnotations.length == 0) {
                    continue;
                }
                for (Annotation an : fieldAnnotations) {
                    if (an instanceof FieldMapperAnnotation) {
                        fieldMapperAnnotation = (FieldMapperAnnotation) an;
                        fieldMapper = new FieldMapper();
                        fieldMapper.setFieldType(fieldMapperAnnotation.fieldType());
                        fieldMapper.setFieldName(field.getName());
                        
                        if(StringUtils.isBlank(fieldMapperAnnotation.dbFieldName())) {
                        	fieldMapper.setDbFieldName(field.getName());
                        } else {
                        	fieldMapper.setDbFieldName(fieldMapperAnnotation.dbFieldName());
                        }
                        fieldMapperCache.put(fieldMapper.getDbFieldName(), fieldMapper);
                        fieldMapperList.add(fieldMapper);
                    }
                }
            }
            tableMapper.setFieldMapperCache(fieldMapperCache);
            tableMapper.setFieldMapperList(fieldMapperList);
            tableMapperCache.put(dtoClass, tableMapper);
            return tableMapper;
        }
    }

    /**
     * 从注解里获取唯一键信息
     * 
     * @param tableMapper
     * @return
     */
    private static String[] buildUniqueKey(TableMapper tableMapper) {
        TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
        String[] uniqueKeyNames = null;
        if (tma.uniqueKeyType().equals(UniqueKeyType.Single)) {
            uniqueKeyNames = new String[1];
            uniqueKeyNames[0] = tma.uniqueKey();
        } else {
            uniqueKeyNames = tma.uniqueKey().split(",");
        }
        return uniqueKeyNames;
    }

    /**
     * 由传入的对象生成insert sql语句
     * 
     * @param tableMapper
     * @param dto
     * @return sql
     * @throws Exception
     */
    public static String buildInsertSql(Object object) throws Exception {
        if (null == object) {
            throw new RuntimeException("Sorry,I refuse to build sql for a null object!");
        }
        Map<String, Object> dtoFieldMap = PropertyUtils.describe(object);
        // 从参数对象里提取注解信息
        TableMapper tableMapper = buildTableMapper(object.getClass());
        // 从表注解里获取表名等信息
        TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
        String tableName = tma.tableName();
        StringBuffer tableSql = new StringBuffer();
        StringBuffer valueSql = new StringBuffer();

        tableSql.append("insert into ").append(tableName).append("(");
        valueSql.append("values(");

        boolean allFieldNull = true;
        // 根据字段注解和属性值联合生成sql语句
        for (String dbFieldName : tableMapper.getFieldMapperCache().keySet()) {
            FieldMapper fieldMapper = tableMapper.getFieldMapperCache().get(dbFieldName);
            Object value = getValue(fieldMapper, dtoFieldMap);
            // 由于要根据字段对象值是否为空来判断是否将字段加入到sql语句中，因此DTO对象的属性不能是简单类型，反而必须是封装类型
            if (value == null) {
                continue;
            }
            allFieldNull = false;
            tableSql.append(dbFieldName).append(",");
            valueSql.append(formatValue(value)).append(",");
        }
        if (allFieldNull) {
            throw new RuntimeException("Are you joking? Object " + object.getClass().getName()
                    + "'s all fields are null, how can i build sql for it?!");
        }
        tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
        valueSql.delete(valueSql.lastIndexOf(","), valueSql.lastIndexOf(",") + 1);
        return tableSql.append(") ").append(valueSql).append(")").toString();
    }
    
    public static Object getValue(FieldMapper fieldMapper, Map<String, Object> dtoFieldMap) throws Exception{
    	if(fieldMapper.getFieldType() == FieldType.G) {
    		return dtoFieldMap.get(fieldMapper.getFieldName());
    	} else { //Object
    		Object obj = dtoFieldMap.get(fieldMapper.getFieldName());
    		if(obj instanceof Domain) {
    			Map<String, Object> subChildMap = PropertyUtils.describe(obj);
    			
    			return subChildMap.get("id");
    		}
    		
    		throw new RuntimeException("getValue 返回对象的Object 没有实现 Domian."); 
    	}
    }

    /**
     * 由传入的对象生成update sql语句
     * 
     * @param object
     * @return sql
     * @throws Exception
     */
    public static String buildUpdateSql(Object object) throws Exception {
        if (null == object) {
            throw new RuntimeException("Sorry,I refuse to build sql for a null object!");
        }
        Map<String, Object> dtoFieldMap = PropertyUtils.describe(object);
        TableMapper tableMapper = buildTableMapper(object.getClass());
        TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
        String tableName = tma.tableName();
        String[] uniqueKeyNames = buildUniqueKey(tableMapper);

        StringBuffer tableSql = new StringBuffer();
        StringBuffer whereSql = new StringBuffer(" where ");

        tableSql.append("update ").append(tableName).append(" set ");

        boolean allFieldNull = true;

        for (String dbFieldName : tableMapper.getFieldMapperCache().keySet()) {
            FieldMapper fieldMapper = tableMapper.getFieldMapperCache().get(dbFieldName);
            Object value = getValue(fieldMapper, dtoFieldMap);
            if (value == null) {
                continue;
            }
            allFieldNull = false;
            tableSql.append(dbFieldName).append("=").append(formatValue(value)).append(",");
        }
        if (allFieldNull) {
            throw new RuntimeException("Are you joking? Object " + object.getClass().getName()
                    + "'s all fields are null, how can i build sql for it?!");
        }

        tableSql.delete(tableSql.lastIndexOf(","), tableSql.lastIndexOf(",") + 1);
        for (int i = 0; i < uniqueKeyNames.length; i++) {
            whereSql.append(uniqueKeyNames[i]);
            FieldMapper fieldMapper = tableMapper.getFieldMapperCache().get(uniqueKeyNames[i]);
            Object value = getValue(fieldMapper, dtoFieldMap);
            if (value == null) {
                throw new RuntimeException("Unique key '" + uniqueKeyNames[i]
                        + "' can't be null, build update sql failed!");
            }
            whereSql.append("=").append(formatValue(value)).append(" and ");
        }
        whereSql.delete(whereSql.lastIndexOf("and"), whereSql.lastIndexOf("and") + 3);
        return tableSql.append(whereSql).toString();
    }

    private static Object formatValue(Object value) {
    	if(value == null) {
    		return "NULL";
    	} else if(value instanceof Number) {
    		return value;
    	} else {
    		return "'" + value.toString() + "'";
    	}
	}

	/**
     * 由传入的对象生成update sql语句
     * 
     * @param object
     * @return sql
     * @throws Exception
     */
    public static String buildDeleteSql(Object object, Class<?> targetClass) throws Exception {
        if (null == object) {
            throw new RuntimeException("Sorry,I refuse to build sql for a null object!");
        }
        Map<String, Object> dtoFieldMap = PropertyUtils.describe(targetClass);
        TableMapper tableMapper = buildTableMapper(targetClass);
        TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
        String tableName = tma.tableName();
        String[] uniqueKeyNames = buildUniqueKey(tableMapper);

        StringBuffer sql = new StringBuffer();

        // delete from tableName where primaryKeyName=?
        sql.append("delete from ").append(tableName).append(" where ");
        
        if(object instanceof Number || object instanceof String) {
        	if(uniqueKeyNames.length != 1) {
        		throw new RuntimeException("参数为非实体时而主键有多个时不匹配！");
        	}
        	sql.append(uniqueKeyNames[0]).append("=");
        	if(object instanceof Number) {
        		sql.append(object);
        	} else {
        		sql.append("'").append(object).append("'");
        	}
        	
        } else {
        	for (int i = 0; i < uniqueKeyNames.length; i++) {
                sql.append(uniqueKeyNames[i]);
                FieldMapper fieldMapper = tableMapper.getFieldMapperCache().get(uniqueKeyNames[i]);
                Object value = getValue(fieldMapper, dtoFieldMap);
                if (value == null) {
                    throw new RuntimeException("Unique key '" + uniqueKeyNames[i]
                            + "' can't be null, build update sql failed!");
                }
                sql.append("=").append(formatValue(value)).append(" and ");
            }
            sql.delete(sql.lastIndexOf("and"), sql.lastIndexOf("and") + 3);
        }
        

        return sql.toString();
    }

    /**
     * 由传入的对象生成query sql语句
     * 
     * @param object
     * @return sql
     * @throws Exception
     */
    public static String buildSelectSql(Object object, Class<?> targetClass) throws Exception {
        if (null == object) {
            throw new RuntimeException("Sorry,I refuse to build sql for a null object!");
        }
        Map<String, Object> dtoFieldMap = PropertyUtils.describe(object);
        TableMapper tableMapper = buildTableMapper(targetClass); //有可能只传 主键而非Class 所以用targetClass
        TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
        String tableName = tma.tableName();
        String[] uniqueKeyNames = buildUniqueKey(tableMapper);

        StringBuffer selectSql = new StringBuffer();
        selectSql.append("select ");
        for (String dbFieldName : tableMapper.getFieldMapperCache().keySet()) {
            selectSql.append(dbFieldName).append(",");
        }
        selectSql.delete(selectSql.lastIndexOf(","), selectSql.lastIndexOf(",") + 3);
        selectSql.append(" from ").append(tableName);

        StringBuffer whereSql = new StringBuffer(" where ");
        
        if(object instanceof Number || object instanceof String) {
        	if(uniqueKeyNames.length != 1) {
        		throw new RuntimeException("参数为非实体时而主键有多个时不匹配！");
        	}
        	
        	whereSql.append(uniqueKeyNames[0]).append("=");
        	if(object instanceof Number) {
        		whereSql.append(object);
        	} else {
        		whereSql.append("'").append(object).append("'");
        	}
        } else {
			for (int i = 0; i < uniqueKeyNames.length; i++) {
				whereSql.append(uniqueKeyNames[i]);
				FieldMapper fieldMapper = tableMapper.getFieldMapperCache().get(uniqueKeyNames[i]);
				Object value = getValue(fieldMapper, dtoFieldMap);
				if (value == null) {
					throw new RuntimeException("Unique key '"
							+ uniqueKeyNames[i]
							+ "' can't be null, build query sql failed!");
				}
				whereSql.append("=").append(formatValue(value)).append(" and ");
			}
			whereSql.delete(whereSql.lastIndexOf("and"), whereSql.lastIndexOf("and") + 3);
        }
        

        return selectSql.append(whereSql).toString();
    }

}
