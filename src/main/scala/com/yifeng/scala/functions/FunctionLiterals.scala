package com.yifeng.scala.functions

/**
  * @author guoyifeng on 2020-07-23
  *         函数字面量（function literal），也称值函数（function values），指的是函数可以赋值给变量。
  *
  *
  *         函数(functions) 与 方法(method)的区别:
  *   1. 定义方式不同：
  *         functions:
  *         val func = (a: Int, b: Int) => a + b
  *         method:
  *         def method(a: Int, b: Int) = a + b
  *
  *     1.1 参数列表对于method是可选的，即：参数列表可以没有，也可以为空。
  *         def me() = 10
  *         def me = 10
  *         但是对于函数是强制的，即：参数列表可以空，但不能没有。
  *         val fu = () => 10    ()不能省略
  *
  *   2. 用法不同:
  *     2.1 作为参数传递
  *         function是可以作为参数传递, 因为function literal 可以赋值给变量
  *         method本身不能作为参数传递，但是手动传入方法时，会被scala转为function
  */
object FunctionLiterals {
  def main(args: Array[String]): Unit = {
    var a = 1
    println(increase(a))
    println(increase2(a))

    println(Array(1, 2, 3, 4).map(increase).mkString(","))

    // anonymous function
    println(Array(1, 2, 3, 4).map(x => x + 1).mkString(","))

    // further simplification
    println(Array(1, 2, 3, 4).map(_ + 1).mkString(","))

    println(myFunc(999)) // 1000.0

    println(convertIntToString((x: Int) => x + "hahaha")) // 4hahaha

    val x = multiplyBy(10)
    val res = x(50)  // res = 500
  }

  val increase = (x: Int) => x + 1

  val increase2 = (x: Int) => { // with blocks
    println()
    println()
    println()
    println()
    x + 1
  }

  // function literal initialization simplified way
  val myFunc = 1 + (_: Double) // shall declare type otherwise scala compiler cannot recognize it and will throw an error

  // more simplified way (Double) means parameter list, second double is type of underscore
  val myFunc2: (Double) => Double = 1 + _


  /****************** high level functions: function as parameter ************/
  var a = 4
  def convertIntToString(f: Int => String) = f(a)

  // 高阶函数可以产生新的函数
  // (Double)=>((Double)=>Double)
  // multiplyBy(d) returns a function which receives a parameter x and multiply it with former factor
  // 下列函数也是一种闭包，因为在运行时其值才得以确定, for inner function, factor is a free variable
  def multiplyBy(factor: Double) = (x: Double) => factor * x

}
