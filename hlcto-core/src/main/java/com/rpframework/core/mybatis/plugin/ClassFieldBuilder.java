package com.rpframework.core.mybatis.plugin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.TableMapper;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.builder.SqlBuilder;

public class ClassFieldBuilder {

	public Domain domain;
	Map<String, Object> map;
	Map<String, Object> whereMap;
	String tableName;
	
	public ClassFieldBuilder setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	public ClassFieldBuilder(Domain domain) {
		this.domain = domain;
		
		map = new HashMap<String, Object>();
		whereMap = new HashMap<String, Object>();
	}
	
	public ClassFieldBuilder addWhere(String fieldName, Object value) {
		whereMap.put(fieldName, value);
		return this;
	}
	
	public Object getWhereByKey(String key) {
		return whereMap.get(key);
	}
	
	public ClassFieldBuilder addField(String fieldName, Object value) {
		map.put(fieldName, value);
		
		return this;
	}
	
	public static Object formatValue(Object value) {
    	if(value == null) {
    		return "NULL";
    	} else if(value instanceof Number) {
    		return value;
    	} else if (value instanceof String && (StringUtils.indexOf(((String) value), "+") > -1
    			|| StringUtils.indexOf(((String) value), ">") > -1
    			|| StringUtils.indexOf(((String) value), ">=") > -1
    			|| StringUtils.indexOf(((String) value), "<=") > -1
    			|| StringUtils.indexOf(((String) value), "<") > -1
    			|| StringUtils.indexOf(((String) value), "-") > -1
    			 )) {
    		return StringEscapeUtils.escapeSql(value.toString());
    	}else {
    		return "'" + StringEscapeUtils.escapeSql(value.toString()) + "'";
    	}
	}
	
	public String buildUpdateSql() {
		
		if(StringUtils.isBlank(tableName)) {
			TableMapper tableMapper = SqlBuilder.buildTableMapper(domain.getClass());
			TableMapperAnnotation tma = (TableMapperAnnotation) tableMapper.getTableMapperAnnotation();
	        tableName = tma.tableName();
		}
        
        StringBuffer updateSql = new StringBuffer();
        updateSql.append("update ").append(tableName);
        
        updateSql.append(" set ");
        int size = map.size() - 1;
        int idx = 0;
        for(Map.Entry<String, Object> entry : map.entrySet()) {
        	
        	updateSql.append(entry.getKey()).append("=").append(formatValue(entry.getValue()));
        	if(size != idx) {
        		updateSql.append(",");
        	}
        	idx ++;
        }
        
        size = whereMap.size() - 1;
        idx = 0;
        updateSql.append(" where ");
        String operCharacter = "=";
        
        for(Map.Entry<String, Object> entry : whereMap.entrySet()) {
        	Object value = entry.getValue();
        	if (value instanceof String ) {
        		String valueString = (String) value;
        		
        		if(StringUtils.indexOf(valueString, ">") > -1
        			|| StringUtils.indexOf(valueString, ">=") > -1
        			|| StringUtils.indexOf(valueString, "<=") > -1
        			|| StringUtils.indexOf(valueString, "<") > -1
        			|| StringUtils.indexOf(valueString, "-") > -1
        			) {
        			operCharacter = "";
        		 }
        	}
        		
        	updateSql.append(entry.getKey()).append(operCharacter).append(formatValue(value));
        	if(size != idx) {
        		updateSql.append(" and ");
        	}
        	idx ++;
        }
        
		return updateSql.toString();
	}
}
