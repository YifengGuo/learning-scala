package com.yifeng.scala.generics.implicit_.conversion

import java.io.File

import scala.io.Source

/**
 * Created by guoyifeng on 8/5/20
 * 在scala语言中，隐式转换是无处不在的，只不过scala语言为我们隐藏了相应的细节，例如scala中的类继承层次结构中
 * 视图界定可以跨越类层次结构进行，它背后的实现原理就是隐式转换，例如Int类型会视图界定中会自动转换成RichInt,而RichInt实现了Comparable接口，
 * 当然这里面的隐式转换也是scala语言为我们设计好的
 */
object ImplicitConversionBasics {

  def main(args: Array[String]): Unit = {
    /** ******************** implicit conversion method ******************************/
    // 定义了一个隐式函数double2Int，将输入的参数
    // 从Double类型转换到Int类型
    // 隐式函数的名称对结构没有影响，即implicit def double2Int(x:Double)=x.toInt函数可以是任何名字，
    // 只是采用source2Target这种方式函数的意思比较明确，阅读代码的人可以见名知义，增加代码的可读性。
    implicit def double2Int(x: Double) = x.toInt

    val x: Int = 3.5
    println(x) // x = 3

    // 隐式转换功能十分强大，可以快速地扩展现有类库的功能
    class RichFile(val file: File) {
      def read = Source.fromFile(file).getLines().mkString(" ")
    }
    //隐式函数将java.io.File隐式转换为RichFile类
    implicit def file2RichFile(file: File) = new RichFile(file)

    val f = new File("src/main/scala/com/yifeng/scala/generics/implicit_/conversion/file.log").read
    println(f)
  }


}


