package com.johnny.annotation;

/**
 * 内置注解
 *
 * @author johnnyhao
 */
public class Annotation01 {

    /**
     * @Override 重写注解
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @Deprecated 弃用注解
     * 不推荐程序员使用，但可以使用，通常是因为它很危险或者存在更好的选择
     */
    @Deprecated
    public static void test01() {

    }

    /**
     * @SuppressWarnings 抑制编译时的警告信息
     */
    @SuppressWarnings("all")
    public static void test02() {

    }

    public static void main(String[] args) {
        test01();
    }
}
