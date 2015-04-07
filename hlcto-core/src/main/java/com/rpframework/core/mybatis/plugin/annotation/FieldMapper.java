package com.rpframework.core.mybatis.plugin.annotation;


/**
 * 字段映射类，用于描述java对象字段和数据库表字段之间的对应关系
 * 
 * @author david
 * 
 */
public class FieldMapper {
    /**
     * Java对象字段名
     */
    private String fieldName;
    /**
     * 数据库表字段名
     */
    private String dbFieldName;

    private FieldType fieldType;
    
    
    public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
