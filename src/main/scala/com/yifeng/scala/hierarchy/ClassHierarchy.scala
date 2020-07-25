package com.yifeng.scala.hierarchy

/**
  * @author guoyifeng on 2020-07-24
  */
object ClassHierarchy {
  def main(args: Array[String]): Unit = {
    compareTest
    instanceOfUnit
  }

  def compareTest(): Unit = {
    // eq, ne, == , !=
    val s1 = new String("123")
    val s2 = new String("123")
    println(s1 == s2) // true  different from Java
    println(s1 != s2) // false
    println(s1 eq s2) // false
    println(s1 ne s2) // true   eq is like == in Java
  }

  def instanceOfUnit(): Unit = {
    // () is instance of Unit
    println(().hashCode()) // hashcode == 0
  }

  // Any is superclass of all classes (AnyVal and AnyRef)
  // AnyRef is superclass of all non-value classes (like Object class in Java which is superclass of all classes)
  // Because scala can run on other platform other than JVM, so implementation of AnyRef can be varied so
  // scala does not use java.Object as AnyRef

  /** ****************** Nothing, Null *************************/
  // Null类型是所有AnyRef类型的子类型，也即它处于AnyRef类的底层，对应java中的null引用。
  // Nothing是scala类中所有类的子类，它处于scala类的最底层。

  // Null is subclass of AnyRef so it can only be used for non-value class
  class Person(var name: String, var age: Int) {

  }

  object Person {
    def main(args: Array[String]): Unit = {
      val p0 = null
      val p = new Person("x", 1)
    }
  }


  // Nothing这个类一般用于指示程序返回非正常结果，利用Nothing作为返回值可以增加程序的灵活性
  def error(msg: String): Nothing = {
    throw new RuntimeException(msg)
  }

  def divide(x: Int, y: Int): Int =
    if (y != 0) x / y
    else error("can't divide by zero")
}
