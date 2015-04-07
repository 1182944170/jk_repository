package com.rpframework.core.mybatis.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述java对象字段对应的数据库表字段的注解（数据库字段名，字段对应的jdbc类型）
 * 
 * @author david
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapperAnnotation {
    /**
     * 
     * 对应数据库表的字段名称
     */
    String dbFieldName() default "";
    
    FieldType fieldType() default FieldType.G;
}
