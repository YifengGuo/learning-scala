package com.yifeng.scala.data.structure

/**
  * @author guoyifeng on 2020-07-23
  *
  */
object QueueTest {

  // immutable
  var queue = scala.collection.immutable.Queue(1, 2, 3)

  // mutable
  var varQueue = scala.collection.mutable.Queue(1, 2, 3, 4, 5)

  def main(args: Array[String]): Unit = {
    addValue
  }

  def addValue(): Unit = {
    // add single element
    varQueue += 6
    varQueue.enqueue(7)
    println(varQueue)

    // add collection
    varQueue ++= List.apply(8, 9)
    println(varQueue)

    // dequeue
    val dequeue = varQueue.dequeue // return dequeued element  (1)
    println(dequeue)
  }
}
