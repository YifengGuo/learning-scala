package com.yifeng.scala.pattern.matching

/**
  * @author guoyifeng on 2020-07-28
  *
  *        1. 模式匹配的类型
  *        2. for控制结构中的模式匹配
  *        3. option类型模式匹配
  */
object CaseClassAdvance {

  def main(args: Array[String]): Unit = {
    println(patternShow(5))
    println(patternShowVariable("not five"))
    val p = new Person("摇摆少年梦", 27)
    println(constructorPattern(p))
    println(parameterTypePattern(5.0))
    println(parameterTypePattern((1, 2, 3)))
    println(variableBindingPattern(list)) // List(2, 3, 4)
    println(mapPattern("hive"))
  }

  /** ************** types of pattern matching ********************/
  // 1. constant type
  // 注意，下面定义的是一个函数
  // 函数的返回值利用的是模式匹配后的结果作为其返回值
  // 也可以定义在main()中，因为scala中方法内还可定义方法
  def patternShow(x: Any) = x match {
    case 5 => "five"
    case true => "true"
    case "test" => "String"
    case null => "null"
    case Nil => "empty list"
    case _ => "Other constant"
  }

  // 2. variable type
  def patternShowVariable(x: Any) = x match {
    case 5 => "five"
    //所有不是值为5的都会匹配变量y
    //例如"xxx"，则函数的返回结果就是"xxx"
    case y => y
  }

  // 3. constructor type (case class)
  case class Person(name: String, age: Int)

  def constructorPattern(p: Person): String = p match {
    case Person(name, age) => "Person"
    case _ => "Other"
  }

  // 4. sequence type
  val p = List("spark", "hive", "SparkSQL")

  def sequencePattern(p: List[String]): String = p match {
    //只需要匹配第二个元素
    case List(_, second, _*) => second
    case _ => "Other"
  }

  // 5. tuple type
  val t = ("1", "2", "3")

  def tuplePattern(t: (String, Any, Any)): String = t match {
    case (one, _, _) => one
    case _ => "Nothing"
  }

  // 6. parameter type pattern matching
  def parameterTypePattern(a: Any): String = a match {
    case a: String => "String"
    case a: Int => "Int"
    case a: Double => "Double"
    case _ => "Any"
  }

  // 7. variable binding pattern
  var list = List(List(1, 2, 3), List(2, 3, 4))

  def variableBindingPattern(t: Any) = t match {
    //变量绑定，采用变量名（这里是e)
    //与@符号，如果后面的模式匹配成功，则将
    //整体匹配结果作为返回
    case List(_, e@List(_, _, _)) => e
    case _ => Nil
  }


  /** ************** pattern matching in for loop ********************/
  val m = Map("China" -> "Beijing", "Japan" -> "Tokyo", "America" -> "DC Washington")
  //利用for循环对Map进行模式匹配输出，
  for ((nation, capital) <- m)
    println(nation + ": " + capital)

  /** ************** pattern matching in regular expression ********************/
  val ipRegex = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)".r

  def regexPattern(): Unit = {
    for (ipRegex(one, two, three, four) <- ipRegex.findAllIn("192.168.1.1")) {
      println("IP子段1:" + one)
      println("IP子段2:" + two)
      println("IP子段3:" + three)
      println("IP子段4:" + four)
    }
  }

  /** ************** Option pattern matching ********************/
  // Option is a sealed class
  // Some and None are sub-class of Option
  // Some is case class and None is case object
  val m1 = Map("hive" -> 2, "spark" -> 3, "Spark MLlib" -> 4)

  def mapPattern(key: String) = m1.get(key) match {
    case Some(x) => x
    case None =>
  }

  // Differences between None and Some
  // Some is case class
  // None is case object
  // decompile from two classes bytecode we can know:
  // Some has apply and unapply while None does not have
  // because it is not necessary to create None instance and get value from it

}

