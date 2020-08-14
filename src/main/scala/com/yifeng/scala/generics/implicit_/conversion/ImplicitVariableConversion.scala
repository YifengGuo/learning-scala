package com.yifeng.scala.generics.implicit_.conversion

/**
 * Created by guoyifeng on 8/14/20
 * 如何利用隐式参数进行隐式转换
 */
object ImplicitVariableConversion {
  def main(args: Array[String]): Unit = {
    // 下面的代码不能编译通过
    // 这里面泛型T没有具体指定，它不能直接使用 < 符号进行比较
    //    def compare[T](first:T,second:T)={
    //      if (first < second)
    //        first
    //      else
    //        second
    //    }

    println(compareUsingImplicitConversion("A", "B"))

    // solution1: variable bound
    def compareUsingVariableBound[T <: Ordered[T]](first: T, second: T): T = {
      if (first < second)
        first
      else
        second
    }

    // solution2: implicit conversion
    def compareUsingImplicitConversion[T](first: T, second: T)(implicit order: T => Ordered[T]): T = {
      if (first < second)
        first
      else
        second
    }
  }
}
