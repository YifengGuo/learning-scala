package com.yifeng.scala.generics.parameterized.types

/**
  * @author guoyifeng on 2020-08-02
  */
class TypeVariableBound {
  //采用<:进行类型变量界定
  //该语法的意思是泛型T必须是实现了Comparable
  //接口的类型
  def compare[T <: Comparable[T]](first: T, second: T) = {
    if (first.compareTo(second) > 0)
      first
    else
      second
  }
}

//定义Student类为case class，且泛型T的类型变量界定为AnyVal
//在创建类时，所有处于AnyVal类继承层次结构的类都是合法的
//如Int、Double等值类型
case class Student[S, T <: AnyVal](var name: S, var age: T)

object TypeVariableBound {
  def main(args: Array[String]): Unit = {
    val tvb = new TypeVariableBound
    //由于String类型实现了Comparable接口
    //下面这种使用方式是合法的
    println(tvb.compare("A", "B"))
    println(tvb.compare(Person("stephen", 19), Person("john", 20)))
//    val will0 = new Student("Will", "17")  // runtime error because "17" String is not subclass of AnyVal
    val will = new Student("Will", 17)
  }
}

case class Person(var name: String, var age: Int) extends Comparable[Person] {
  override def compareTo(o: Person): Int = {
    if (this.age > o.age) 1
    else if (this.age == o.age) 0
    else -1
  }
}