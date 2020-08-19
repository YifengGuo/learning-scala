package com.yifeng.scala.type_.system

/**
  * @author guoyifeng on 2020-08-18
  *         在scala中存在着中置操作符，如1+2等，+在这被称为中置操作符，前面我们说过，
  *         scala中的一切操作皆为对象调用，1+2其实是1.+(2)的对象方法调用。
  *         在scala中同样存在着中置类型
  */
case class PersonInfix[T, S](name: T, age: S)

object InfixType extends App {
  //下面的代码是一种中置表达方法，相当于
  //val p:Person[String,Int] = null

  // 如果类的泛型参数是两个的话，则可以使用中置表达式进行变量的定义。
  // 中置类型最常用的场景是模式匹配
  val p: String PersonInfix Int = null

  val person: String PersonInfix Int = PersonInfix("test", 18)

  //中置表达式的模式匹配用法
  //模式匹配时可以直接用常量，也可以直接用变量
  person match {
    case "test" PersonInfix 18 => println("matching is ok")
    case name PersonInfix age => println("name:" + name + "  age=" + age)
  }
}


