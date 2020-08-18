package com.yifeng.scala.oop.basics

import scala.beans.BeanProperty

/**
  * @author guoyifeng on 2020-07-23
  *
  * 1. class definition, object initialization
  * 2. primary constructor
  * 3. secondary constructor
  */
object OOPBasics {
  def main(args: Array[String]): Unit = {

    // 直接new
    val p = new Person()
    p.name_=("john") // setter
    println(p.name) // getter

    val person3 = new Person3
    person3.setName("haha")
    person3.getName

    val p4 = new Person4("john", 29)

    val p5 = new Person5
    println(p5)

  }

  // 采用关键字class定义
  // 无参主构建器
  class Person {
    //类成员必须初始化，否则会报错
    //这里定义的是一个公有成员
    // 如果类的成员域是val类型的变量，则只会生成getter方法 => 字节码中为java final 修饰
    var name: String = null

    // 反编译后可知：
    // 虽然我们只在Person类中定义了一个类成员（域）name，类型为String，
    // 但Scala会默认帮我们生成name()与name_=（）及构造函数Person()。
    // 其中name()对应java中的getter方法，name_=()对应java中的setter方法


  }

  class Person2 {

    //定义私有成员
    private var privateName: String = null;

    // 自定义 getter and setter
    //getter方法
    def name = privateName

    //setter方法
    def name_=(name: String) {
      this.privateName = name
    }
  }

  class Person3 {
    // now can use java style getter and setter
    @BeanProperty var name: String = _
  }


  /** **************************** primary constructor ***************************/
  // 主构造器的定义与类的定义交织在一直，将构造器参数直接放在类名称之后
  class Person4(val name: String, val age: Int) {
    override def toString() = name + ":" + age
  }

  // 默认参数的主构建器
  class Person5(val name: String = "default_name", val age: Int = 18) {
    override def toString() = name + ":" + age
  }

  // 主构造器中的参数还可以加访问控制符
  // will not generate setter and getter for field age
  class Person6(val name: String = "", private val age: Int = 18) {
    override def toString() = name + ":" + age
  }

  // 当主构造器的参数不用var或val修饰的时候，参数会生成类的私有val成员，并且不会产生getter和setter方法
  class Person7(name: String, age: Int) {
    override def toString() = name + ":" + age
  }

  // 在某些情况下，可能需要禁用主构建器, java字节码中无带参数的constructor，且fields为private，所以不能用new去新建对象
  class Person8 private(name: String, age: Int) {
    override def toString() = name + ":" + age
  }

  /** **************************** secondary constructor ***************************/
  // 如果禁用掉了主构建器，则必须使用辅助构造函数来创建对象
  // (1）辅助构建器的名称为this，java中的辅助构造函数与类名相同，这常常会导致修改类名时出现不少问题，scala语言避免了这样的问题；
  //（2）调用辅助构造函数时，必须先调用主构造函数或其它已经定义好的构造函数。

  //只有辅助构造函数的类
  class Person9 {
    //类成员
    private var name: String = null
    private var age: Int = 18
    private var sex: Int = 0

    //辅助构造器
    def this(name: String) {
      this()
      this.name = name
    }

    def this(name: String, age: Int) {
      this(name)
      this.age = age
    }

    def this(name: String, age: Int, sex: Int) {
      this(name, age)
      this.sex = sex
    }
  }

  //具有主构建函数和辅助构建函数的Person类
  class Person10(var name: String, var age: Int) {
    //类成员
    private var sex: Int = 0

    //辅助构造器
    def this(name: String, age: Int, sex: Int) {
      this(name, age)
      this.sex = sex
    }
  }

  //禁用主构造函数
  class Person11 private(name:String, age:Int){
    //类成员
    private var sex:Int=0

    //辅助构造器
    def this(name:String,age:Int,sex:Int){
      this(name,age)
      this.sex=sex
    }
    // can only created by Person11 p11 = new Person11(name, age, sex)
  }
}
