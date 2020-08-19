package com.yifeng.scala.type_.system

import scala.reflect.runtime.universe._

/**
  * @author guoyifeng on 2020-08-19
  *
  *         在scala中，类(class)与类型(type)是两个不一样的概念。类是对同一类型数据的抽象，而类型则更具体。
  *         比如定义class List[T] {}, 可以有List[Int] 和 List[String]等具体类型，
  *         称List为class，而List[Int]、List[String]则为type
  *         从这方面看：类型一致的对象它们的类也是一致的；而类一致的，其类型不一定一致
  */
object TypeSpecialization {
  def main(args: Array[String]): Unit = {

    println(classOf[List[String]] == classOf[List[Int]]) // true

    println(typeOf[List[String]] == typeOf[List[Int]]) // false
  }

  // type specialization主要是用来解决泛型的类型擦除和自动装箱拆箱的问题。
  // 在JAVA语言当中，泛型生成字节码文件时会进行泛型类型擦除，类型擦除后利用上界类型（一般是Object）来替代，
  // 但这么做的话有问题，这是因为在Java语言中基本类型与对象类型是不能相互引用的，java中的基本类型不能使用泛型。
  // 解决方案是利用对应的对象类型来进行替代，例如int对应Integer类型，但这种方式并不能解决根本问题。
  // 经过类型擦除后的类称为原始类型，从这点来看，java中的泛型其实是一个伪泛型，它只在编译层次进行实现，在生成字码码这部分泛型信息被擦除

  // auto-boxing与unboxing需要损耗一定的性能，当性能要求较高时需要程序员手动进行转换。Scala中的Type Specialization解决了这些问题。

  // 通过注解进行类型专门化声明
  abstract class List[@specialized T] {
    def apply(x: T)

    def map[S](f: T => S)
  }
  // 上述代码编译后会生成9个list版本，对应9种类型Unit, Boolean, Byte, Short, Char, Int, Long, Float, Double


  // @specialized 还可以更细致，限定某个或几个基本类型
  abstract class SpecializedList[@specialized T]{
    // 指定生成Int类型的版本
    def apply[@specialized (Int) S](x:S):S
    // 指定生成Boolean及Double类型的版本
    def map[@specialized (Boolean,Double) S](f:T=>S)
  }

}
