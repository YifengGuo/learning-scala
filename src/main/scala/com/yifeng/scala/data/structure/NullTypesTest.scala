package com.yifeng.scala.data.structure

import scala.collection.mutable

/**
  * @author guoyifeng on 2020-07-29
  *  Scala Null, null, Nil, Nothing, None, and Unit
  */
object NullTypesTest {

  def main(args: Array[String]): Unit = {
//    nullAndNull("haha") // compile error
    nullAndNull(null)
    testNil // return List()
    val map = new mutable.HashMap[String, Int]()
    map.put("spark", 1)
    testNone(map)
  }

  // null is simply the same in java
  // Null is a trait, its singleton instance is null
  // The reference types can be assigned null but the value types cannot be assigned null
  def nullAndNull(thing: Null): Unit = {
    println("the parameter can only accept null")
  }

  // Nothing and Unit
  // Nothing is also a trait which has NO instance
  // goal to provide this trait is to supply a return type for the methods which consistently throws an exception
  // Unit: similar a void in java, which is a return type for methods which do not return anything
  def error(msg: String): Nothing = {
    throw new RuntimeException(msg)
  }

  // Nil
  // Nil is Considered as a List which has zero elements in it.
  def testNil(): Unit = {
    println(Nil) // List()
  }

  // None  designed to avoid NullPointerException
  // It is one of the children of Scala’s Option class which is utilized to avoid assignment
  // of null to the reference types
  def testNone(map: mutable.HashMap[String, Int]): Unit = {
    val option = map.get("spark")
    val unit = show(option)
    println(unit)
  }
  def show(option: Option[Int]) = option match {
    case Some(x) => x
    case None => "None"
  }
}
