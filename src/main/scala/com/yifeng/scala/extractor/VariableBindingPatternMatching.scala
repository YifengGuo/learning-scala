package com.yifeng.scala.extractor

/**
 * @author guoyifeng on 8/19/20
 */
object VariableBoundPatternMatching {
}

// Twice用于匹配重复出现的字符串，它绑定的是一个变量
// 即返回的类型是Option[String]
object Twice {
  def apply(x : String) = x

  def unapply(str: String) = {
    val length = str.length / 2
    val half = str.substring(0, length)
    if (half == str.substring(length)) Some(half) else None
  }
}

// 未绑定任何变量，仅仅返回Boolean类型
object UpperCase {
  def unapply(x : String): Boolean = x.toUpperCase() == x
}

object Test extends App {
  def userTwiceUpper(s: String) = s match {
    //下面的代码相当于执行了下面这条语句
    //UpperCase.unapply(Twice.unapply(EMail.unapply(s)))
    case Email(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }
  val email = Email("XX","sina.com")
  println(userTwiceUpper(email))
}
