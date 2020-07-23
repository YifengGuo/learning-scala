package com.yifeng.scala.data.structure

import scala.collection.mutable.ArrayBuffer

/**
  * @author guoyifeng on 2020-07-23
  */
object ArrayTest {
  val strArrayVar = ArrayBuffer[String]()
  strArrayVar += "Hello"
  strArrayVar += "World, Programmer"
  strArrayVar ++= Array("Welcome", "To", "Chengdu") // ++= is to append collection to the array
  strArrayVar ++= List("Welcome", "To", "Chongqing")

  def main(args: Array[String]): Unit = {
    //    initArray()

    initNonFixableArray()
  }

  // fixable size array
  def initArray(): Unit = {
    // 复杂对象类型在数组定义时被初始化为null，数值型被初始化为0
    val arr1 = new Array[Int](10)
    val arr2 = new Array[String](10)
    for (i <- arr1) {
      print(i + " ")
    }
    println()
    for (i <- arr2) {
      print(i + " ")
    }
  }

  // non-fixable size array using ArrayBuffer
  def initNonFixableArray(): Unit = {
    println()

    // delete n elements from tail
    strArrayVar.trimEnd(3)

    strArrayVar.insert(0, "haha") // insert element at certain position, original elements will be moved to its next positions
    println(strArrayVar)

    strArrayVar.remove(0, 4) // remove elements from 0 to 4 (4 is exclusive)

    // non-fixable to fixable
    val array = strArrayVar.toArray

    // vice versa
    val buffer = array.toBuffer
  }

  // iteration on array
  def iterateArray(): Unit = {
    println()
    // to
    for (i <- 0 to strArrayVar.length) println(i)

    // until, last position is exclusive
    for (i <- 0 until strArrayVar.length) println(i)

    // iteration
    for (i <- strArrayVar) println(i)

    // step
    for (i <- 0 until(strArrayVar.length, 2)) println(i)

    // iterate reversely
    for (i <- strArrayVar.indices.reverse) println(i)
  }

  // array conversion
  def arrayConversion(): Unit = {
    // yield to convert buffer to a new buffer
    val newBuffer = for (i <- 0 until strArrayVar.length) yield i * 2

    // with filter
    val newBuffer2 = for (i <- 0 until strArrayVar.length if i >= 2) yield i * 2
  }

  // multi-dimension array
  def initMultiDimensionArray(): Unit = {
    var multiDimArr = Array(Array(1, 2, 3), Array(2, 3, 4))
    val unit = multiDimArr(0)(1)

    // iteration on multi-dimension array
    for (i <- multiDimArr) { // get inner collection
      print(i.mkString(","))
    }
  }
}
