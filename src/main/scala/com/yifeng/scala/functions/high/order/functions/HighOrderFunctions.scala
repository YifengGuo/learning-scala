package com.yifeng.scala.functions.high.order.functions

/**
  * @author guoyifeng on 2020-07-27
  *
  * 1. intro to high order functions
  * 2. common high order functions
  * 3. SAM (simple abstract method) conversion
  * 4. function currying
  * 5. partial function
  */
object HighOrderFunctions {
  def main(args: Array[String]): Unit = {
    foldTest
  }

  /** ***************** intro to high order functions *******************/
  // there are two kinds of high order functions:
  // 1. functions as method parameters
  def convertIntToString(f: Int => String) = {
    f(4)
  }

  // 2. functions as return result
  def multiplyBy(factor: Double) = (x: Double) => factor * x

  /** ***************** intro to high order functions *******************/
  // fold
  def foldTest(): Unit = {
    Array(1, 2, 4, 3, 5).foldLeft(0)((x: Int, y: Int) => {
      println(x, y);
      x + y
    })
    // (0,1)
    // (1,2) 0 + 1 = 1 => (1 ,2)
    // (3,4)
    // (7,3)
    // (10,5)
    // 15

    Array(1, 2, 4, 3, 5).foldRight(0)((x: Int, y: Int) => {
      println(x, y);
      x + y
    })
    // iterate from array right to left
    // (5,0)
    // (3,5)
    // (4,8)
    // (2,12)
    // (1,14)
    // 15
  }

  // scan
  def scanTest(): Unit = {
    // 从左扫描，每步的结果都保存起来，执行完成后生成数组
    // (0,1)
    // (1,2)
    // (3,4)
    // (7,3)
    // (10,5)
    // res Array[Int] = Array(0, 1, 3, 7, 10, 15)
    Array(1, 2, 4, 3, 5).scanLeft(0)((x: Int, y: Int) => {
      println(x, y)
      x + y
    })

    //从右扫描，每步的结果都保存起来，执行完成后生成数组
    // (5,0)
    // (3,5)
    // (4,8)
    // (2,12)
    // (1,14)
    // res74: Array[Int] = Array(15, 14, 12, 8, 5, 0)
    Array(1, 2, 4, 3, 5).scanRight(0)((x: Int, y: Int) => {
      println(x, y)
      x + y
    })
  }

  /** ***************** SAM (simple abstract method) conversion *******************/
  // interface with only one method which needs overriding
  // 作用：省略非常多的样板代码，使代码更简洁


  /** ***************** function currying *******************/
  // 柯里化在工程中有什么好处? - 华天清的回答 - 知乎
  //https://www.zhihu.com/question/37774367/answer/1102347536

  // normal style function as return result
  def multiplyBy1(factor: Double) = (x: Double) => x * factor

  // currying style to decouple implementation of each function
  def multiplyByCurrying(factor: Double)(x: Double) = x * factor

  //multiplyByCurrying(50)(10) => 500
  // or multiplyByCurrying(50)_     res is : Double => Double = <function1> and this is a partial function


  /** ***************** partial function *******************/
  // 所谓部分应用函数就是指，当函数有多个参数，
  // 而在我们使用该函数时我们不想提供所有参数（假设函数有3个函数），只提供0~2个参数，此时得到的函数便是部分应用函数
  def sum(x: Int, y: Int, z: Int) = x + y + z

  // 不指定任何参数的部分应用函数
  // s1: (Int, Int, Int) => Int = <function3>
  // s1(1,2,3) = 6
  val s1 = sum _

  //指定两个参数的部分应用函数
  // 下划线_并不是占位符的作用，而是作为部分应用函数的定义符。
  // s2: Int => Int = <function1>
  // s2(2) = 6
  val s2 = sum(1, _: Int, 3)

  //指定一个参数的部分应用函数
  // s3: (Int, Int) => Int = <function2>
  // s3(2,3) = 6
  val s3 = sum(1, _: Int, _: Int)
}
