package com.johnny.reflection.reflection02;

/**
 * 类加载器
 *
 * @author johnnyhao
 */
public class Reflection02 {

    public static void main(String[] args) throws ClassNotFoundException {

        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父加载器 --> 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        // 获取扩展类加载器的父加载器 --> 引导类加载器
        // C/C++编写，无法直接获取
        ClassLoader oldParent = parent.getParent();
        System.out.println(oldParent);

        // 获取当前类的加载器
        ClassLoader classLoader = Class.forName("com.johnny.reflection.reflection02.Reflection02").getClassLoader();
        System.out.println(classLoader);

        // 获取JDK内置类的加载器
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 获取系统类加载器可以加载的路径
        String property = System.getProperty("java.class.path");
        System.out.println(property);

        // 双亲委派机制
        // 类加载器从引导类加载器开始执行，保证系统安全性
        // 如自定义java.lang.String无法生效
    }
}
