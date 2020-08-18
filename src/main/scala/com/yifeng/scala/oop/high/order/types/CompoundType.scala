package com.yifeng.scala.oop.high.order.types

/**
  * @author guoyifeng on 2020-08-18
  *
  *         example:
  *         class B extends A with Cloneable
  *         整体 A with Cloneable可以看作是一个复合类型，它也可以通过type关键字来进行声明
  */
class B extends A with Cloneable

object CompoundType {
  //利用关键字type声明一个复合类型
  type X = A with Cloneable

  def test(x: X): Unit = println("test ok")

  def main(args: Array[String]): Unit = {
    test(new B)
  }
}
