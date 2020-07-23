package com.yifeng.scala.functions

/**
  * @author guoyifeng on 2020-07-23
  *
  *
  * (x:Int) => x + more,这里面的more是一个自由变量（Free Variable）,more是一个没有给定含义的不定变量
  * 而x的类型确定、值在函数调用的时候被赋值，称这种变量为绑定变量（Bound Variable）
  */
object Closure {

  // 像这种运行时确定more类型及值的函数称为闭包,more是个自由变量，在运行时其值和类型得以确定
  // 这是一个由开放(free)到封闭的过程，因此称为闭包
  def main(args: Array[String]): Unit = {
    var more = 1
    val closure = (x : Int) => x + more

    println(closure(1))
    println(closure(10))
    println(closure(20))

    // another example
    println
    var sum = 0
    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    someNumbers.foreach(sum += _)  // sum is a free variable
    println(sum)
    someNumbers.foreach(sum += _)
    println(sum)

    println

    // 高阶函数可以产生新的函数
    // (Double)=>((Double)=>Double)
    // multiplyBy(d) returns a function which receives a parameter x and multiply it with former factor
    // 下列函数也是一种闭包，因为在运行时其值才得以确定, for inner function, factor is a free variable
    def multiplyBy(factor: Double) = (x: Double) => factor * x
    val x = multiplyBy(10)
    val res = x(50)  // res = 500
    print(res)

  }

}
