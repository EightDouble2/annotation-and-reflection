package com.johnny.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解
 *
 * @author johnnyhao
 */
public class Annotation02 {

}

/**
 * 自定义注解
 *
 * @author johnnyhao
 */
// @Target 用于描述注解的使用范围
@Target({ElementType.METHOD, ElementType.TYPE})
// @Retention 用于描述注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// @Documented 说明该注解将被包含在javadoc中
@Documented
// @Inherited 说明子类可以继承父类中的该注解
@Inherited
@interface MyAnnotation02 {

}