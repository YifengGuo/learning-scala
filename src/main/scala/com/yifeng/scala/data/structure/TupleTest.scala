package com.yifeng.scala.data.structure

/**
  * @author guoyifeng on 2020-07-23
  *
  *         Tuple contains elements of different types
  */
object TupleTest {

  var tuple = ("Hello", "China", 1)

  def main(args: Array[String]): Unit = {
    accessTupleField
  }

  def accessTupleField(): Unit = {
    val t1 = tuple._1
    val t2 = tuple._2
    val t3 = tuple._3

    // get tuple values via pattern match
    val (x1, x2, x3) = tuple
    print(x1 + "," + x2 + "," + x3)
  }
}
