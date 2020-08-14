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
    implicit def double2Int(x: Double): Int = x.toInt

    val x: Int = 3.5
    println(x) // x = 3

    // 隐式转换功能十分强大，可以快速地扩展现有类库的功能
    class RichFile(val file: File) {
      def read: String = Source.fromFile(file).getLines().mkString(" ")
    }
    //隐式函数将java.io.File隐式转换为RichFile类
    implicit def file2RichFile(file: File): RichFile = new RichFile(file)

    val f = new File("src/main/scala/com/yifeng/scala/generics/implicit_/conversion/file.log").read
    println(f)

    // 隐式转换发生的场景：
    // 1. 方法中输入的参数类型与实际类型不一致，此时会发生隐式转换  eg: val x: Int = 3.5
    // 2. 当调用类中不存在的方法或成员时，会自动将对象进行隐式转换
    //     eg: file2RichFile(file: File), 调用new File("path").read, File类的对象并不存在read方法，此时便会发生隐式转换 将File类转换成RichFile

    // 不会发生隐式转换的场景：
    // 1. 编译器可以不在隐式转换的编译通过，则不进行隐式转换 eg: val a = 3.0 * 2
    // 2. 存在二义性，比如两个file2RichFile(file: File), file2RichFile1(file: File), 参数相同，则不会进行转换，使用时会报错
    // 3. 隐式转换不会嵌套进行
    //    class RichFile(val file:File){
    //      def read=Source.fromFile(file).getLines().mkString
    //    }
    //
    //    //RichFileAnother类，里面定义了read2方法
    //    class RichFileAnother(val file:RichFile){
    //      def read2=file.read
    //    }
    // 隐式转换不会多次进行，下面的语句会报错
    // 不能期望会发生File到RichFile，然后RifchFile到
    // RichFileAnother的转换
    //    val f=new File("file.log").read2
    //    println(f)
  }
}


