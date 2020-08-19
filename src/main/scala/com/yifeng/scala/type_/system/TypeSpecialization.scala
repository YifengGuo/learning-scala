package com.yifeng.scala.type_.system

import scala.reflect.runtime.universe._
/**
  * @author guoyifeng on 2020-08-19
  *
  *   在scala中，类(class)与类型(type)是两个不一样的概念。类是对同一类型数据的抽象，而类型则更具体。
  *   比如定义class List[T] {}, 可以有List[Int] 和 List[String]等具体类型，称List为class，而List[Int]、List[String]则为type
  *   从这方面看：类型一致的对象它们的类也是一致的；而类一致的，其类型不一定一致
  */
object TypeSpecialization {
  def main(args: Array[String]): Unit = {

    println(classOf[List[String]] == classOf[List[Int]])  // true

    println(typeOf[List[String]] == typeOf[List[Int]])  // false
  }
}
