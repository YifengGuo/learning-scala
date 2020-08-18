package com.yifeng.scala.oop.high.order.types

/**
  * @author guoyifeng on 2020-08-18
  *         抽象类型是指在类或特质中利用type关键字定义一个没有确定类型的标识，
  *         该标识在子类中被确定，称这种类型为抽象类型
  */
object AbstractType {

  abstract class Person {
    type identityNo

    def getIdentityNo(): identityNo
  }

  class Student extends Person {
    type identityNo = String

    override def getIdentityNo(): identityNo = "123"
  }

  class Teacher extends Person {
    type identityNo = Int

    override def getIdentityNo(): identityNo = 123
  }

  def main(args: Array[String]): Unit = {
    println(new Student().getIdentityNo())
  }
}
