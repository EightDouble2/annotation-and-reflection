package com.johnny.reflection.reflection04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射动态创建对象
 *
 * @author johnnyhao
 */
public class Reflection04 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取Class对象
        Class<?> aClass = Class.forName("com.johnny.reflection.reflection04.Person");

        // 构造一个对象
        // newInstance调用的是无参构造器
        Person person = (Person) aClass.newInstance();
        System.out.println(person);

        // 通过构造器创建对象
        Constructor<?> constructor = aClass.getDeclaredConstructor(Long.class, String.class);
        person = (Person) constructor.newInstance(1L, "张三");
        System.out.println(person);

        // 通过反射调用普通方法
        Method setName = aClass.getDeclaredMethod("setName", String.class);
        setName.invoke(person, "李四");
        System.out.println(person);

        // 通过反射操作属性
        Field name = aClass.getDeclaredField("name");
        name.set(person, "王五");
        System.out.println(person);

        // 通过反射操作私有属性，需要关闭程序的访问检测，属性或方法.setAccessible(true)
        // 关闭访问检测可以提高反射程序执行效率
        Field id = aClass.getDeclaredField("id");
        id.setAccessible(true);
        id.set(person, 2L);
        System.out.println(person);
    }
}

class Person {

    private Long id;
    public String name;

    public Person() {
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}