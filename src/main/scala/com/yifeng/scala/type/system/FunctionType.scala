package com.yifeng.scala.`type`.system

/**
  * @author guoyifeng on 2020-08-18
  */
object FunctionType {

  val max = (x: Int, y: Int) => if (x < y) y else x
  val anonfun2 = new Function2[Int, Int, Int] {
    def apply(x: Int, y: Int): Int = if (x < y) y else x
  }

  val succ = (x: Int) => x + 1
  val anonfun1 = new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
  }

  def main(args: Array[String]): Unit = {
    // max与anonfun2是等价的，它们定义的都是输入参数是两个Int类型
    // 返回值也是Int类型的函数。
    assert(max(0, 1) == anonfun2(0, 1)) // true
    assert(succ(0) == anonfun1(0)) // true
  }
}
