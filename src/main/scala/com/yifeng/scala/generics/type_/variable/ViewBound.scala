package com.yifeng.scala.generics.type_.variable

/**
  * @author guoyifeng on 2020-08-02
  *         如果希望跨越类继承层次结构时，可以使用视图界定来实现的，
  *         其后面的原理是通过隐式转换来实现。
  *         视图界定利用<%符号来实现
  */
@Deprecated
object ViewBound {
  def main(args: Array[String]): Unit = {
    val s = StudentViewBound("john", "170")
    // 下面这条语句不合法，这是因为
    // Int类型没有实现Comparable接口
//      val s2 = StudentViewBound("john",170)
    val s3 = StudentViewBound1("john", 170)
  }
}

case class StudentViewBound[T, S <: Comparable[S]](var name: T, var height: S)

//利用<%符号对泛型S进行限定
//它的意思是S可以是Comparable类继承层次结构中实现了Comparable接口的类
//也可以是能够经过隐式转换得到的类,该类实现了Comparable接口
// Int类会隐式转换成RichInt类，RichInt并不是直接实现Comparable口，而是通过ScalaNumberProxy类将Comparable中的方法继承过来
case class StudentViewBound1[T, S <% Comparable[S]](var name: T, var height: S)
