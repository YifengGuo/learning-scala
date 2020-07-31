package com.yifeng.scala.pattern.matching

/**
  * @author guoyifeng on 2020-07-27
  *         Case Class一般被翻译成样例类，它是一种特殊的类，能够被优化以用于模式匹配
  */
object CaseClassBasics {
}


/**
  * 当一个类被声名为case class的时候，scala会帮助我们做下面几件事情：
  * 1 构造器中的参数如果不被声明为var的话，它默认的话是val类型的，但一般不推荐将构造器中的参数声明为var
  * 2 自动创建伴生对象，同时在里面给我们实现子apply方法，使得我们在使用的时候可以不直接显示地new对象
  * 3 伴生对象中同样会帮我们实现unapply方法，从而可以将case class应用于模式匹配，关于unapply方法我们在后面的extractor那一节会重点讲解
  * 4 实现自己的toString、hashCode、copy、equals方法
  * 除此之此，case class与其它普通的scala类没有区别
  *
  */
//抽象类Person
abstract class Person

//case class Student
case class Student(name: String, age: Int, studentNo: Int) extends Person

//case class Teacher
case class Teacher(name: String, age: Int, teacherNo: Int) extends Person

//case class Nobody
case class Nobody(name: String) extends Person

//SchoolClass为接受多个Person类型参数的类
case class SchoolClass(classDescription: String, persons: Person*)

object CaseClassDemo {
  def main(args: Array[String]): Unit = {
    //case class 会自动生成apply方法，从而省去new操作
    val p: Person = Student("john", 18, 1024)

    val sc = SchoolClass("学途无忧网Scala培训班", Teacher("摇摆少年梦", 27, 2015), Student("摇摆少年梦", 27, 2015))
    //match case 匹配语法
    p match {
      case Student(name, age, studentNo) => println(name + ":" + age + ":" + studentNo)
      case Teacher(name, age, teacherNo) => println(name + ":" + age + ":" + teacherNo)
      case Nobody(name) => println(name)
    }
    sc match {
      case SchoolClass(_, _, Student(name, age, studentNo)) => println(name)
      case _ => println("Nobody")
    }
  }
}

// sealed case class
// 在进行模式匹配的时候，有些时候需要确保所有的可能情况都被列出，此时常常会将case class的超类定义为sealed（密封的) case class
sealed abstract class Person1

case class Student1(name: String, age: Int, studentNo: Int) extends Person1

case class Teacher1(name: String, age: Int, teacherNo: Int) extends Person1

case class Nobody1(name: String) extends Person1

case class SchoolClass1(classDescription: String, persons: Person1*)

object CaseClassDemo1 {
  def main(args: Array[String]): Unit = {
    val s: Person1 = Student1("john", 18, 1024)
    //这边仅仅给出了匹配Student的情况，在编译时
    //编译器会提示Warning, 并不影响运行
    //match may not be exhaustive. It would fail on the following inputs: Nobody(_), Teacher(_, _, _)
    s match {
      case Student1(name, age, studentNo) => println("Student")
    }
  }
}