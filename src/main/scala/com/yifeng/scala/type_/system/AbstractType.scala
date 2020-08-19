package com.yifeng.scala.type_.system

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

object AbstractTypeByGenerics {

  abstract class Person[T] {
    def getIdentityNo(): T
  }

  class Student extends Person[String] {
    override def getIdentityNo(): String = "123"
  }

  class Teacher extends Person[Int] {
    override def getIdentityNo(): Int = 123
  }

  def main(args: Array[String]): Unit = {
    println(new Student().getIdentityNo())
  }
}

// 如果类型是在子类型中才被确定，则推荐使用抽象类型。
// use case for abstract type in practical situation
trait Closable {
  type in
  type out

  def close(x: in): out
}

class File extends Closable {
  type in = String
  type out = Boolean

  override def close(x: String): Boolean = {
    /**
      * close file io
      */

    true
  }
}
