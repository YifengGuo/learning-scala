package com.yifeng.scala.type_.system

/**
 * @author guoyifeng on 2020-08-18
 *         https://stackoverflow.com/questions/9737352/what-is-the-apply-function-in-scala
 *         in scala
 *         function can be mathematical way: val f = (x: Int) => x + 1
 *         function can also be Object Oriented way: val f = new Function1[Int, Int] {
 *                                                      def apply(x: Int): Int = ???
 *                                                    }
 *         then use function like f(0), in mathematical way, it is called apply f on argument 0
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
