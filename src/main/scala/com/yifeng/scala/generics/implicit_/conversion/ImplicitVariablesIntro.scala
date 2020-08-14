package com.yifeng.scala.generics.implicit_.conversion

/**
 * Created by guoyifeng on 8/14/20
 * 函数中如果存在隐式参数，在使用该函数的时候如果不给定对应的参数，则编译器会自动帮我们搜索相应的隐式值
 * 要想使用implicit只作用于某个函数参数，则需要将函数进行柯里化
 */
object ImplicitVariablesIntro {
  def main(args: Array[String]): Unit = {
    // 程序中定义的变量outputFormat被称隐式值
    implicit val format: OutputFormat = new OutputFormat("<<", ">>")
    // 在.formatStudent()方法时，编译器会查找类型
    // 为OutputFormat的隐式值,本程序中定义的隐式值 为outputFormat
    println(new Student("john").formatStudent())
  }

  class Student(name: String) {
    // 利用柯里化函数的定义方式，将函数的参数利用implicit关键字标识
    // 这样的话，在使用的时候可以不给出implicit对应的参数
    def formatStudent()(implicit outputFormat: OutputFormat): String = {
      outputFormat.first + " " + this.name + " " + outputFormat.second
    }
  }

  class OutputFormat(var first: String, val second: String)

}
