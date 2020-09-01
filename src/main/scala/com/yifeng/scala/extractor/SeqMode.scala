package com.yifeng.scala.extractor

/**
 * @author guoyifeng on 8/21/20
 *
 *         object List {
 *         def apply[T](elems: T*) = elems.toList
 *         def unapplySeq[T](x: List[T]): Option[Seq[T]] = Some(x)
 *         ...
 *         }
 */
object SeqMode {
  def main(args: Array[String]): Unit = {
    val list = List(List(1, 2, 3), List(4, 5, 6))
    println(list)
    list match {
      case List(List(one, two, three), _*) => println("one: " + one + " two: " + two + " three: " + three)
      case _ => println("no match")
    }

    list match {
      // _表示匹配列表中的第一个元素
      // _*表示匹配List中的其它多个元素
      // 这里采用的变量绑定的方式
      // the @ is used to perform a recursive pattern match via
      // the unapply method of the thing on the right-hand side.
      case List(_, x@List(_*), _*) => println(x)
      case _ => println("other list")
    }
  }
}
