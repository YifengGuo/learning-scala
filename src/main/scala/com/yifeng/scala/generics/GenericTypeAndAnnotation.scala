package com.yifeng.scala.generics

/**
  * @author guoyifeng on 2020-07-31
  *         泛型用于指定方法或类可以接受任意类型参数，参数在实际使用时才被确定，
  *         泛型可以有效地增强程序的适用性，使用泛型可以使得类或方法具有更强的通用性
  */
object GenericTypeAndAnnotation {

}

// 泛型类
class Person[T](var name: T)

class Student[T, S](name: T, var age: S) extends Person(name)


// annotation
/**
  * usage:
  * 1. doc generation
  * 2. syntax check (@deprecated)
  * 3. stipulate program logic: @BeanProperty
  */