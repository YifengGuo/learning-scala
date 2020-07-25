package com.yifeng.scala.trait_

/**
  * @author guoyifeng on 2020-07-24
  *
  * 1. initialization order of trait
  * 2. difference between class and trait
  * 3. predef and lazy load
  * 4. trait extension
  * 5. self type
  */
object TraitAdvance {

}

/** ***************** initialization order of trait ***********************/
// 对于不存在具体实现及字段的trait，它最终生成的字节码文件反编译后是等同于java中的接口，
// 而对于存在具体实现及字段的trait，其字节码文件反编译后得到的java中的抽象类，它有着scala语言自己的实现方式。
// 因此，对于trait它也有自己的构造器，trait的构造器由字段的初始化和其它trait体中的语句构成
import java.io.{IOException, PrintWriter}

trait Logger {
  println("Logger")

  def log(msg: String): Unit
}

trait FileLogger extends Logger {
  println("FileLogger")
  val fileOutput = new PrintWriter("/Users/guoyifeng/Documents/myJava/learning-scala/src/main/scala/com/yifeng/scala/trait_/file.log")
  fileOutput.println("#")

  def log(msg: String): Unit = {
    fileOutput.print(msg)
    fileOutput.flush()
  }
}

object TraitDemo {
  def main(args: Array[String]): Unit = {
    //匿名类
    new FileLogger {}.log("trait demo")
  }
}

// output
// Logger
// FileLogger
// create file.log

// 在创建匿名类对象时，先调用的是Logger类的构造器，然后调用的是FileLogger的构造器。实际上构造器是按以下顺序执行的：
// 1. 如果有超类，则先调用超类的构造器
// 2. 如果有父trait，它会按照继承层次先调用父trait的构造器
// 3. 如果有多个父trait，则按顺序从左到右执行
// 4. 所有父类构造器和父trait被构造完之后，才会构造本类

// class Person
// class Student extends Person with FileLogger with Cloneable
// 上述构造器的执行顺序为:
// 1 首先调用父类Person的构造器
// 2 调用父trait Logger的构造器
// 3 再调用trait FileLogger构造器，再然后调用Cloneable的构造器
// 4 最后才调用Student的构造器

/** ***************** difference between class and trait ***********************/
// trait有自己的构造器，它是无参构造器，不能定义trait带参数的构造器
// 除此之外 ，trait与普通的scala类并没有其它区别，在前一讲中我们提到，trait中可以有具体的、抽象的字段，
// 也可以有具体的、抽象的方法，即使trait中没有抽象的方法也是合理的
//FileLogger里面没有抽象的方法
trait FileLogger1 extends Logger {
  println("FileLogger")
  val fileOutput = new PrintWriter("/Users/guoyifeng/Documents/myJava/learning-scala/src/main/scala/com/yifeng/scala/trait_/file.log")
  fileOutput.println("#")

  def log(msg: String): Unit = {
    fileOutput.print(msg)
    fileOutput.flush()
  }
}


/** ***************** predef and lazy load ***********************/
// 如果上述例子中将log文件路径设为变量，则在runtime会报npe
// 如下：
trait FileLogger2 extends Logger {

  //增加了抽象成员变量
  val fileName: String
  //将抽象成员变量作为PrintWriter参数
  val fileOutput = new PrintWriter(fileName: String)
  fileOutput.println("#")

  def log(msg: String): Unit = {
    fileOutput.print(msg)
    fileOutput.flush()
  }
}

class Person

class Student extends Person with FileLogger2 {
  //Student类对FileLogger中的抽象字段进行重写
  val fileName = "file.log"
}

object TraitDemoNPE {
  def main(args: Array[String]): Unit = {
    new Student().log("trait demo")
  }
}

// 报npe的原因是：
// 在对Student类进行new操作的时候，它首先会
// 调用Person构造器，这没有问题，然后再调用
// Logger构造器，这也没问题，但它最后调用FileLogger构造器的时候
// 会调用val fileOutput = new PrintWriter(fileName:String)
// 但因为Student后于FileLogger初始化，此时fileName = null, 所以会报npe

// 解决上述问题的方法：
// 1. 提前定义 PreDef 提前定义是指在常规构造之前将变量初始化
trait LoggerPreDef {
  def log(msg: String): Unit
}

trait FileLoggerPreDef extends LoggerPreDef {

  val fileName: String
  val fileOutput = new PrintWriter(fileName: String)
  fileOutput.println("#")

  def log(msg: String): Unit = {
    fileOutput.print(msg)
    fileOutput.flush()
  }
}

class PersonPreDef

class StudentPreDef extends PersonPreDef with FileLogger {
  val fileName = ""
}

object TraitDemoPreDef {
  def main(args: Array[String]): Unit = {
    val s = new {
      //提前定义
      override val fileName = "file.log"
    } with StudentPreDef
    s.log("predefined variable ")
  }
}

// lazy load
// 显然，这种方式编写的代码很不优雅，也比较难理解。此时可以通过在第一讲中提到的lazy即懒加载的方式
trait LoggerLazy {
  def log(msg: String): Unit
}

trait FileLoggerLazy extends LoggerLazy {

  val fileName: String
  // 将方法定义为lazy方式， fileOutput只有当真正被使用时才被初始化
  // 当调用 s.log("predefine variable by lazy load")时，fileOutput才被初始化，此时fileName已经被赋值了
  lazy val fileOutput = new PrintWriter(fileName: String)
  // 下面这条语句不能出现，否则同样会报错
  // 因为它是FileLogger构造器里面的方法
  // 在构造FileLogger的时候便会执行
  // fileOutput.println("#")

  def log(msg: String): Unit = {
    fileOutput.print(msg)
    fileOutput.flush()
  }
}

class PersonLazy

class StudentLazy extends PersonLazy with FileLoggerLazy {
  val fileName = ""
}

object TraitDemoLazy {
  def main(args: Array[String]): Unit = {
    val s = new StudentLazy
    s.log("predefine variable by lazy load")
  }
}


/** ***************** trait extension ***********************/
//trait除了不具有带参数的构造函数之外，与普通类没有任何区别，这意味着trait也可以扩展其它类
trait LoggerExtension {
  def log(msg: String): Unit
}

//trait扩展类Exception
trait ExceptionLogger extends Exception with LoggerExtension {
  def log(msg: String): Unit = {
    println(getMessage())
  }
}

//类UnprintedException扩展自ExceptionLogger
//注意用的是extends
//此时ExceptionLogger父类Exception自动成为
//UnprintedException的父类
class UnprintedException extends ExceptionLogger {
  override def log(msg: String): Unit = {
    println("")
  }
}

//IOException具有父类Exception
//ExceptionLogger2也具有父类Exception
//scala会使UnprintedException只有一个父类Exception
class UnprintedException2 extends IOException with ExceptionLogger {
  override def log(msg: String): Unit = {
    println("")
  }
}

/** ***************** self type ***********************/
// example 1: self type as class itself
class A {
  //下面 self =>  定义了this的别名，它是self type的一种特殊形式
  //这里的self并不是关键字，可以是任何名称
  self =>
  val x = 2

  //可以用self.x作为this.x使用
  def foo = self.x + this.x
}

// example 2: self type in inner class
class OuterClass {
  outer => //定义了一个外部类别名
  val v1 = "here"

  class InnerClass {
    // 用outer表示外部类，相当于OuterClass.this
    println(outer.v1)
  }

}

// example 3:
// 定义了自身类型self type，它不是前面别名的用途
// 自身类型的存在相当于让当前类变得“抽象”了，它假设当前对象(this)也符合指定的类型，
// 因为自身类型 this:X =>的存在，当前类构造实例时需要同时满足X类型
trait X {
  def foo()
}

class B {
  self: X =>
}

//类C扩展B的时候必须混入trait X
//否则的话会报错
class C extends B with X {
  def foo() = println("self type demo")
}

object SelfTypeDemo extends App {
  println(new C().foo)
}
