package com.johnny.reflection.reflection01;

/**
 * 获取Class类对象
 *
 * @author johnnyhao
 */
public class Reflection01 {

    public static void main(String[] args) throws ClassNotFoundException {

        Person student = new Student("student");
        System.out.println(student.getName());

        // 方式一：通过对象获取
        Class<? extends Person> s1 = student.getClass();
        System.out.println(s1.hashCode());

        // 方式二：forName方法获得
        Class<?> s2 = Class.forName("com.johnny.reflection.reflection01.Student");
        System.out.println(s2.hashCode());

        // 方式三：通过类名.class获得
        Class<?> s3 = Student.class;
        System.out.println(s3.hashCode());

        // 方式四：基本内置类型的包装类都有一个Type属性
        Class<Integer> s4 = Integer.TYPE;
        System.out.println(s4.hashCode());

        // 方式五：获得父类类型
        Class<?> s5 = s1.getSuperclass();
        System.out.println(s5.hashCode());
    }
}

class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student extends Person {

    public Student(String name) {
        super(name);
    }
}