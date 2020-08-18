package com.yifeng.scala.oop.high.order.types

/**
  * @author guoyifeng on 2020-08-18
  *
  *         结构类型(structural types)
  *         通过利用反射机制为静态语言添加动态特性，从面使得参数类型不受限于某个已命名的的类型
  *
  *         结构体类型其实可以看作是一个类，在函数调用时，直接通过new操作来创建一个结构体类型对象，当然它是匿名的。
  *         因此，上述方法也可以传入一个实现了close方法的类或单例对象
  */
object StructuralType {

  //定义一个普通的scala类，其中包含了close成员方法
  class File {
    def close(): Unit = println("File Closed")
  }

  //定义一个单例对象，其中也包含了close成员方法
  object File {
    def close(): Unit = println("object File closed")
  }


  // releaseMemory中的方法是一个结构体类型，它定义了
  // 一个抽象方法，对close方法的规格进行了说明
  def releaseMemory(res: {def close(): Unit}) {
    res.close()
  }

  // 利用type关键字声明结构体
  // 采用关键字进行结构体类型声明
  type X = {def close(): Unit}

  //结构体类型X作为类型参数，定义函数releaseMemory2
  def releaseMemory2(x: X): Unit = x.close()

  def main(args: Array[String]): Unit = {
    //结构体使用方式，new这个结构体并给出抽象方法的具体实现
    releaseMemory(new {
      def close(): Unit = println("closed")
    })

    //函数使用同releaseMemory
    releaseMemory2(new {
      def close(): Unit = println("closed")
    })

    releaseMemory(new File())

    releaseMemory(File)
  }
}