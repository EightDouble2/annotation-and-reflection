# 注解 annotation

## 什么是注解

Annotation是从JDK5开始引入的新技术。

- Annotation的作用：
  - 不是程序本身，可以对程序作出解释。(这一点和注释(comment)没什么区别)
  - 可以被其他程序(比如：编译器等)读取。
- Annotation的格式：
  - 注解是以`@注释名`在代码中存在的，还可以添加一些参数值，例如：`@SuppressWarnings(value="unchecked")`。
- Annotation在哪里使用?
  - 可以附加在package、class、method、field等上面，相当于给他们添加了额外的辅助信息，我们可以通过反射机制编程实现对这些元数据的访问。
  
## 内置注解

- `@Override`：定义在`java.lang.Override`中，此注释只适用于修辞方法，表示一个方法声明打算重写超类中的另一个方法声明。
- `@Deprecated`：定义在`java.lang.Deprecated`中，此注释可以用于修辞方法、属性、类，表示不鼓励程序员使用这样的元素，通常是因为它很危险或者存在更好的选择。
- `@SuppressWarnings`：定义在`java.lang.SuppressWanings`中，用来抑制编译时的警告信息，与前两个注释有所不同，你需要添加一个参数才能正确使用，这些参数都是已经定义好了的，我们选择性的使用就好了(`@SuppressWarnings("all")`、`@SuppressWarnings("unchecked")`、`@SuppressWarnings(value={"unchecked"，"deprecation"})`等等)。

## 元注解

元注解的作用就是负责注解其他注解，Java定义了4个标准的meta-annotation类型，他们被用来提供对其他annotation类型作说明。

这些类型和它们所支持的类在`java.lang.annotation`包中可以找到(`@Target`，`@Retention`，`@Documented`，`@Inherited`)。
- `@Target`：用于描述注解的使用范围(即：被描述的注解可以用在什么地方)。
- `@Retention`：表示需要在什么级别保存该注释信息，用于描述注解的生命周期(SOURCE < CLASS < RUNTIME)。
- `@Document`：说明该注解将被包含在javadoc中。
- `@Inherited`：说明子类可以继承父类中的该注解。

## 自定义注解

使用`@interface`自定义注解时，自动继承了`java.lang.annotation.Annotation`接口。

- `@interface`用来声明一个注解，格式：`public @ interface 注解名{定义内容}`。
- 其中的每一个方法实际上是声明了一个配置参数。
- 方法的名称就是参数的名称。
- 返回值类型就是参数的类型(返回值只能是基本类型，Class，String，enum)。
- 可以通过default来声明参数的默认值。
- 如果只有一个参数成员，一般参数名为value。
- 注解元素必须要有值，我们定义注解元素时，经常使用空字符串，0作为默认值。

# 反射 reflection

## 概述

### 静态 VS 动态语言

**动态语言**

- 是一类在运行时可以改变其结构的语言：例如新的函数、对象、甚至代码可以被引进，已有的函数可以被删除或是其他结构上的变化。通俗点说就是在运行时代码可以根据某些条件改变自身结构。
- 主要动态语言：Object-C、C#、JavaScript、PHP、Python等。

**静态语言**

- 与动态语言相对应的，运行时结构不可变的语言就是静态语言。如Java、C、C++。
- Java不是动态语言，但Java可以称之为"准动态语言"。即Java有一定的动态性，我们可以利用反射机制获得类似动态语言的特性。Java的动态性让编程的时候更加灵活!

### Java Reflection

Reflection(反射)是Java被视为动态语言的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的内部信息，并能殖接操作任意对象的内部属性及方法。

`Class c = Class.forName("java.lang.String")`

加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象(一个类只有一个Class对象)，这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为：反射。

- 正常方式：引入需要的"包类"名称 --> 通过new实例化 --> 取得实例化对象
- 反射方式：实例化对象 --> `getClass()`方法 --> 得到完整的"包类"名称

### Java反射优点和缺点

优点：
- 可以实现动态创建对象和编译，体现出很大的灵活性。

缺点：
- 对性能有影响。使用反射基本上是一种解释操作，我们可以告诉JVM，我们希望做什么并且它满足我们的要求。这类操作总是慢于直接执行相同的操作。

## Class类

### 获取Class类

在Object类中定义了以下的方法，此方法将被所有子类继承。

`public final Class getClass()`

以上方法返回值的类型是一个Class类，此类是Java反射的源头，实际上所谓反射从程序的运行结果来看也很好理解，即：可以通过对象反射求出类的名称。

Class类可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接口。对于每个类而言，JRE 都为其保留一个不变的Class类型的对象。一个Class对象包含了特定某个结构(class/interface/enum/annotation/primitive type/void/[])的有关信息。
- Class本身也是一个类。
- Class对象只能由系统建立对象。
- 一个加载的类在JVM中只会有一个Class实例。
- 一个Class对象对应的是一个加载到JVM中的一个，.class文件。
- 每个类的实例都会记得自己是由哪个Class实例所生成。
- 通过Class可以完整地得到一个类中的所有被加载的结构。
- Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的Class对象。

### Class类常用方法

| 方法名 | 功能说明 |
| --- | --- |
| `static ClassforName(String name)` | 返回指定类名name的Class对象 |
| `Object newlnstance()` | 调用缺省构造函数，返回Class对象的一个实例 |
| `getName()` | 返回此Class对象所表示的实体(类，接口，数组类或void)的名称 |
| `Class getSuperClass()` | 返回当前Class对象的父类的Class对象 |
| `Class[] getinterfaces()` | 获取当前Class对象的接口 |
| `ClassLoader getClassLoader()` | 返回该类的类加载器 |
| `Constructor[] getConstructors()` | 返回一个包含某些Constructor对像的数组 |
| `Method getMothed(String name, Class.. T)` | 返回一个Method对象，此对象的形参类型为paramType |
| `Field[] getDeclaredFields()` | 返回Field对象的一个数组 |

### 获取Class类实例

- 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高。
  `Class clazz = Person.class;`
- 已知某个类的实例，调用该实例的getClass()方法获取Class对象。
  `Class clazz = person.getClass();`
- 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法`forName()`获取，可能抛出`ClassNotFoundException`。
  `Class clazz = Class.forName("demo01.Student");`
- 内置基本数据类型可以直接用类名.Type。
- 利用ClassLoader。