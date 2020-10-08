package com.johnny.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author johnnyhao
 */
public class Annotation03 {

    /**
     * 注解可以显式赋值，如果没有默认值，就必须给注解赋值
     */
    @MyAnnotation0301(value = "A", name = {"A", "B"})
    public void test01() {

    }

    /**
     * 注解只有一个参数，参数名一般为value
     */
    @MyAnnotation0302("A")
    public void test02() {

    }
}

/**
 * 自定义注解
 *
 * @author johnnyhao
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation0301 {
    /**
     * 注解参数：参数类型 + 参数名 ();
     */
    String[] name() default {"A", "B"};
    String value();
}

/**
 * 自定义注解
 *
 * @author johnnyhao
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation0302 {
    String value();
}