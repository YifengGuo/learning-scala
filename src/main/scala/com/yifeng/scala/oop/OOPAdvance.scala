package com.yifeng.scala.oop

/**
  * @author guoyifeng on 2020-07-24
  *
  * 1. singleton object
  * 2. companion object and companion class
  * 3. apply()
  * 4. App object
  * 5. abstract class
  */
object OOPAdvance {
  def main(args: Array[String]): Unit = {

  }
}

/** ********************************* singleton object **************************/
// 在某些应用场景下，我们可能不需要创建对象，而是想直接调用方法，但是Scala语言并不支持静态成员，Scala通过单例对象来解决该问题。
// uniqueStudentNo()在java字节码中为static function
object Student {
  private var studentNo: Int = 0;

  def uniqueStudentNo() = {
    studentNo += 1
    studentNo
  }

  def main(args: Array[String]): Unit = {
    println(Student.uniqueStudentNo())
  }
}


/** ********************************* companion object and companion class **************************/
// 在前面单例对象的基础之上，我们在object Student所在的文件内定义了一个class Student，
// 此时object Student被称为class Student的伴生对象，而class Student被称为object Student的伴生类
// 伴生对象与伴生类本质上是不同的两个类，只不过伴生类与伴生对象之间可以相互访问到对主的成员包括私有的成员变量或方法
class Student1(var name: String, var age: Int) {
  private var sex: Int = 0

  //直接访问伴生对象的私有成员
  def printCompanionObject() = println(Student1.studentNo)
}

object Student1 {
  private var studentNo: Int = 0;

  def uniqueStudentNo() = {
    studentNo += 1
    studentNo
  }

  def main(args: Array[String]): Unit = {
    println(Student1.uniqueStudentNo())
    val s = new Student1("john", 29)
    //直接访问伴生类Student中的私有成员
    println(s.sex)
  }
}


/** ********************************* apply() **************************/
// 利用apply方法可以直接利用类名创建对象,如前面在讲集合的时候，可以通过val intList = List(1,2,3)这种方式创建初始化一个列表对象
// 其实它相当于调用val intList = List.apply(1,2,3), 但前者背后仍然是new的方式
// 自定义apply：

//定义Student类，该类称为伴生类，因为在同一个源文件里面，我们还定义了object Student
class Student2(var name: String, var age: Int) {
  private var sex: Int = 0

  //直接访问伴生对象的私有成员
  def printCompanionObject() = println(Student2.studentNo)

}

//伴生对象
object Student2 {
  private var studentNo: Int = 0

  def uniqueStudentNo() = {
    studentNo += 1
    studentNo
  }

  //定义自己的apply方法
  def apply(name: String, age: Int) = new Student2(name, age)

  def main(args: Array[String]): Unit = {
    println(Student2.uniqueStudentNo())
    val s = new Student2("john", 29)
    //直接访问伴生类Student中的私有成员
    println(s.sex)

    //直接利用类名进行对象的创建，这种方式实际上是调用前面的apply方法进行实现，这种方式的好处是避免了自己手动new去创建对象
    val s1 = Student2("john", 29)
    //    val s2 = Student2.apply("john", 29)
    println(s1.name)
    println(s1.age)
  }
}

/** ********************************* App object **************************/
// 利用IDE开发scala应用程序时，在运行程序时必须在object中指定main方法作为程序的入口
// scala还提供了一种机制，即通过扩展App
//扩展App后，程序可以直接运行，而不需要自己定义main方法，代码更简洁
// App其实是一种trait，它帮助我们定义了main方法。
object AppDemo extends App {
  println("App Demo")
}


/** ********************************* abstract class **************************/
// 抽象类是一种不能被实例化的类，抽象类中包括了若干不能完整定义的方法，这些方法由子类去扩展定义自己的实现。
//scala中的抽象类定义
abstract class Animal {
  def eat: Unit // 对应java字节码 public abstract void eat();
}

// 除抽象方法外，抽象类中还可以有抽象字段：
abstract class Animal2 {
  //抽象字段(域）
  //前面我们提到，一般类中定义字段的话必须初始化，而抽象类中则没有这要求
  var height: Int

  //抽象方法
  def eat: Unit
}

//Person继承Animal，对eat方法进行了实现
//通过主构造器对height参数进行了初始化
class PersonA(var height: Int) extends Animal {
  //对父类中的方法进行实现，注意这里面可以不加override关键字
  def eat() = {
    println("eat by mouth")
  }

}

//通过扩展App创建程序的入口
object Person extends App {
  new PersonA(10).eat()
}