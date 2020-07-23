package com.yifeng.scala.data.structure

/**
  * @author guoyifeng on 2020-07-23
  */
object MapTest {

  // immutable map initialization
  val studentInfo = Map("john" -> 21, "stephen" -> 22, "lucy" -> 20)

  // mutable map
  val studentInfoMutable = scala.collection.mutable.Map("john" -> 21, "stephen" -> 22, "lucy" -> 20)

  def main(args: Array[String]): Unit = {
    patternMatchTest
  }

  def iterationOnMap(): Unit = {

    // for loop
    for (i <- studentInfoMutable) println(i)

    // foreach
    studentInfoMutable.foreach(e => {
      val (k, v) = e
      println(k + ":" + v)
    })

    // foreach tuple
    studentInfoMutable.foreach(e => println(e._1 + ":" + e._2))
  }

  /**
    * Option、None、Some是scala中定义的类型，它们在scala语言中十分常用
    * None、Some是Option的子类，它主要解决值为null的问题，
    * 在java语言中，对于定义好的HashMap，如果get方法中传入的键不存在，方法会返回null，
    * 在编写代码的时候对于null的这种情况通常需要特殊处理，然而在实际中经常会忘记，
    * 因此它很容易引起 NullPointerException异常。
    *
    * 在Scala语言中通过Option、None、Some这三个类来避免这样的问题，
    * 这样做有几个好处:
    *   1. 代码可读性更强，当看到Option时，我们自然而然就知道它的值是可选的，
    *   2. 变量是Option，比如Option[String]的时候，直接使用String的话，编译直接通不过。
    *
    * 从Option[Object] 中获取结果需要用到模式匹配
    */
  def mapApis(): Unit = {
    // define empty map
    val xMap = new scala.collection.mutable.HashMap[String, Int]()

    xMap.put("spark", 1) // return Option[Int] = None

    xMap.contains("spark")

    xMap.get("spark") // Option[Int] = Some(1)

    xMap.get("nonExistedKey") //  Option[Int] = None
  }

  def patternMatchTest(): Unit = {
    val xMap = new scala.collection.mutable.HashMap[String, Int]()
    xMap.put("spark", 1)
    val option = xMap.get("spark")
    println(option)
    println(show(option))
  }

  def show(option: Option[Int]) = option match {
    case Some(x) => x
    case None => "None"
  }
}
