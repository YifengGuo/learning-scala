package com.yifeng.scala.data.structure

import scala.collection.mutable.Stack


/**
  * @author guoyifeng on 2020-07-23
  */
object StackTest {

  // new
  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  stack.push(3)

  // apply
  val stack1 = Stack(1, 2, 3)

  stack.pop

  stack.top

  def main(args: Array[String]): Unit = {

  }
}
