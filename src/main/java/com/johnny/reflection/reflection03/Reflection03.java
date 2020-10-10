package com.johnny.reflection.reflection03;

import java.util.Arrays;

/**
 * 获取类的信息
 *
 * @author johnnyhao
 */
public class Reflection03 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> aClass = Class.forName("com.johnny.reflection.reflection03.Person");

        // 获取类名
        // 包名+类名
        System.out.println(aClass.getName());
        // 类名
        System.out.println(aClass.getSimpleName());

        // 获取类所有属性
        // 所有public属性
        System.out.println(Arrays.toString(aClass.getFields()));
        // 所有属性
        System.out.println(Arrays.toString(aClass.getDeclaredFields()));

        // 获取指定属性
        // 所有public属性
         System.out.println(aClass.getField("name"));
        // 所有属性
        System.out.println(aClass.getDeclaredField("id"));

        // 获取所有方法
        // 所有public方法
        System.out.println(Arrays.toString(aClass.getMethods()));
        // 所有方法
        System.out.println(Arrays.toString(aClass.getDeclaredMethods()));

        // 获取方法
        // 所有public方法
        System.out.println(aClass.getMethod("getName"));
        System.out.println(aClass.getMethod("setName", String.class));
        // 所有方法
        System.out.println(aClass.getDeclaredMethod("getId"));
        System.out.println(aClass.getDeclaredMethod("setId", Long.class));

        // 获取所有构造器
        // 所有public构造器
        System.out.println(Arrays.toString(aClass.getConstructors()));
        // 所有构造器
        System.out.println(Arrays.toString(aClass.getDeclaredConstructors()));

        // 获取构造器
        // 所有public构造器
        System.out.println(aClass.getConstructor(Long.class, String.class));
        // 所有构造器
        System.out.println(aClass.getDeclaredConstructor(Long.class));
    }
}

class Person {

    private Long id;
    public String name;

    private Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}