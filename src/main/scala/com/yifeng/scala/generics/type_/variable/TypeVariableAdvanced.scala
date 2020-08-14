package com.yifeng.scala.generics.type_.variable

/**
 * Created by guoyifeng on 8/14/20
 */
object TypeVariableAdvanced {
  // 1. Ordered vs Ordering ===> Comparable vs Comparator
}

// 2. ContextBound
object ContextBound {

  case class Person(val name: String) {
    println("正在构造对象:" + name)
  }

  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if (x.name > y.name)
        1
      else
        -1
    }
  }

  // 下面的代码定义了一个上下文界定
  // 它的意思是在对应作用域中，必须存在一个类型为Ordering[T]的隐式值，该隐式值可以作用于内部的方法
  class Pair[T: Ordering](val first: T, val second: T) {
    //smaller方法中有一个隐式参数，该隐式参数类型为Ordering[T]
    def smaller(implicit ord: Ordering[T]): T = {
      if (ord.compare(first, second) > 0)
        first
      else
        second
    }
  }

  def main(args: Array[String]): Unit = {
    //定义一个隐式值，它的类型为Ordering[Person]
    implicit val p1 = new PersonOrdering
    val p = new Pair(Person("123"), Person("456"))
    //不给函数指定参数，此时会查找一个隐式值，该隐式值类型为Ordering[Person],根据上下文界定的要求，该类型正好满足要求
    //因此它会作为smaller的隐式参数传入，从而调用ord.compare(first, second)方法进行比较
    println(p.smaller)
  }

}



