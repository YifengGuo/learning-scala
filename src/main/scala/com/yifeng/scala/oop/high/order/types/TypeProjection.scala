package com.yifeng.scala.oop.high.order.types

import scala.reflect.runtime.universe.typeOf

/**
  * @author guoyifeng on 2020-08-18
  *         类型投影(type projection)
  */
object TypeProjection {

  class Outer {
    private var x: Int = 0

    // 此Inner类依赖于外部类，整体的话构成了一路径，因为也称为路径依赖类型。
    def print(i: Outer.this.Inner): Outer.this.Inner = i

    class Inner {
      def test() = x
    }

  }

  def main(args: Array[String]): Unit = {

    val outer = new Outer
    val inner = new outer.Inner

    val outer2 = new Outer
    val inner2 = new outer2.Inner

    //下面的代码编译会失败
    //outer.print(inner2)
    //这是因为不同outer对象对应的内部类成员类型是不一样的
    //这就跟两个类成员的实例它们内存地址不一样类似


    //下面的类型判断会输出false
    //这也进一步说明了它们类型是不一样的
    println(typeOf[outer.Inner] == typeOf[outer2.Inner])
  }
}


object InnerClassUseCases {

  // 外部类调用内部类
  class Outer {
    private val x: Int = 0
    //内部使用，相当于
    //private var i:Inner = new Outer.this.Inner
    private var i: Inner = new Inner

    def print(i: Inner) = i

    class Inner {
      def test() = x
    }

  }

  // 在其它类或对象中使用
  object TypeProject extends App {
    val outer = new Outer
    val inner = new outer.Inner

    val outer2 = new Outer
    val inner2 = new outer2.Inner
  }

}


// type projection:
// 类型投影的目的是将外部类Outer中定义的方法def print(i:Inner) = i，它可以接受做任意外部类对象中的Inner类
class Outer {
  private var x: Int = 0
  private var i: Inner = new Outer.this.Inner

  // Outer#Inner类型投影的写法
  // 可以接受任何outer对象中的Inner类型对象
  def print(i: Outer#Inner) = i

  class Inner {
    def test() = x
  }

}

class A extends Outer {
  private val i = new A.super.Inner
}

object TypeProject extends App {
  val outer = new Outer
  val inner = new outer.Inner


  val outer2 = new Outer
  val inner2 = new outer2.Inner
  //下面的这个语句可以成功执行
  outer.print(inner2)
  //注意，下面的这条语句返回的仍然是false，我们只是对print方法中的
  //参数进行类型投影，并没有改变outer.Inner与outer2.Inner
  //是不同类的事实
  println(typeOf[outer.Inner] == typeOf[outer2.Inner])
}
