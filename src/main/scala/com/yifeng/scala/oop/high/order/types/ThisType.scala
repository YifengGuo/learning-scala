package com.yifeng.scala.oop.high.order.types

/**
  * @author guoyifeng on 2020-08-18
  *
  *
  * this.type
  * 3. 结构类型(structural types)
  * 4. 复合类型(compound types)
  */

/*************************************** this.type ***********************************/
class Person {
  private var name: String = null
  private var age: Int = 0

  def setName(name: String): Person = {
    this.name = name
    //返回对象本身
    this
  }

  def setAge(age: Int): Person = {
    this.age = age
    //返回对象本身
    this
  }

  override def toString() = "name:" + name + " age:" + age
}

class Student extends Person {
  private var studentNo: String = null

  def setStudentNo(no: String): Student = {
    this.studentNo = no
    this
  }

  override def toString() = super.toString() + " studetNo:" + studentNo
}

class PersonThisType {
  private var name: String = null
  private var age: Int = 0

  def setName(name: String): this.type = {
    this.name = name
    //返回对象本身
    this
  }

  def setAge(age: Int): this.type = {
    this.age = age
    //返回对象本身
    this
  }
}

class StudentThisType extends PersonThisType {
  private var studentNo: String = null

  def setStudentNo(no: String): this.type = {
    this.studentNo = no
    this
  }

  override def toString() = super.toString() + " studetNo:" + studentNo
}

object ThisType extends App {
  //链式调用
  println(new Person().setAge(18).setName("test"))
  // Student对象在调用setName、setAge方法时，返回的对象类型实质上仍然是Person类型，而Person类型并没有setStudentNo方法，从而编译出错。
  // 为解决该问题，可以将setName、setAge方法的返回值设置为：this.type
  //  println(new Student().setName("john").setAge(22).setStudentNo("2014"))
  println(new StudentThisType().setName("john").setAge(22).setStudentNo("2014"))
}