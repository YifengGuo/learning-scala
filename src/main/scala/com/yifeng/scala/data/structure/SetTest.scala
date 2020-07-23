package com.yifeng.scala.data.structure

import scala.collection.mutable

/**
  * @author guoyifeng on 2020-07-23
  */
object SetTest {
  // init set
  val set = Set(3, 5)

  def main(args: Array[String]): Unit = {
    addAndIterate
    linkedHashSet
  }

  def addAndIterate(): Unit = {
    val ints = set + 6

    val res = for (i <- ints) yield i
    print(res.mkString(","))
  }

  // if insertion order is essential, use LinkedHashSet
  def linkedHashSet(): Unit = {
    println()
    val linkedHashSet = mutable.LinkedHashSet[Integer](3, 5)
    val integers = linkedHashSet + 6
    val res = for (i <- integers) yield i
    print(res.mkString(","))
  }
}
