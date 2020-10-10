package com.johnny.reflection.reflection06;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取注解
 *
 * @author johnnyhao
 */
public class Reflection06 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        // 获取类
        Class<?> aClass = Class.forName("com.johnny.reflection.reflection06.Person");

        // 获取类的注解
        Annotation[] annotations = aClass.getAnnotations();
        System.out.println(Arrays.toString(annotations));

        // 获取注解的value值
        MyAnnotationTable myAnnotationTable = aClass.getAnnotation(MyAnnotationTable.class);
        System.out.println(myAnnotationTable.value());
        
        // 获取属性的注解
        Field id = aClass.getDeclaredField("id");
        MyAnnotationField myAnnotationFieldId = id.getAnnotation(MyAnnotationField.class);
        System.out.println(myAnnotationFieldId.name());
        System.out.println(myAnnotationFieldId.type());
        System.out.println(myAnnotationFieldId.length());
    }
}

@MyAnnotationTable("db_person")
class Person {

    @MyAnnotationField(name = "id", type = "Long", length = 16)
    private Long id;
    @MyAnnotationField(name = "name", type = "String", length = 64)
    private String name;

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 类注解
 *
 * @author johnnyhao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationTable {
    String value();
}

/**
 * 属性注解
 *
 * @author johnnyhao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationField {
    String name();
    String type();
    int length();
}