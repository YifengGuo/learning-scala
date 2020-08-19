package com.yifeng.scala.type_.system

/**
  * @author guoyifeng on 2020-08-18
  *
  *         a syntactic sugar which is simply similar to java wildcard
  *         shorthand             full                             desc
  *         Seq[_]            Seq[T] forSome {type T}              T can be any subtype of Any
  *         Seq[_ <: A]       Seq[T] forSome {type T <: A}         T can be any subtype of A
  *         Seq[_ :> Z <: A]  Seq[T] forSome {type T >: Z <: A}    T can be any subtype of A and any supertype of Z
  */
object ExistentialType extends App {

  //下面的Array[_]是一种存在类型，虽然用的是类型通配
  //符，但它本质上等同于
  //def print2(x:Array[T] forSome {type T})=println(x)
  //即Array[_]中的类型通匹符也是一种语法糖，用于简化设计
  def print1(x: Array[_]) = println(x)

  def print2(x: Array[T] forSome {type T}) = println(x)

  //Map[_,_]相当于Map[T,U] forSome {type T;type U}
  def print3(x: Map[_, _]) = println(x)

  printArr(Array("xxx", "123"))
  printArr(Array("yyy", "456"))
  print3(Map("zzz" -> "789"))

  def printArr(arr: Array[_]): Unit = {
    for (e <- arr) {
      print(e + " ")
    }
    println()
  }
}
