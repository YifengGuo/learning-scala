package com.yifeng.scala.extractor

/**
 * @author guoyifeng on 8/19/20
 *         apply方法帮助我们无需new操作就可以创建对象，
 *         而unapply方法则用于析构出对象，
 *         在模式匹配中特别提到，如果一个类要能够应用于模式匹配当中，必须将类声明为case class，
 *         因为一旦被定义为case class，scala会自动帮我们生成相应的方法，这些方法中就包括apply方法及unapply方法。
 */
object Unapply {

}

object Email {
  def apply(name: String, domain: String): String = name + "@" + domain

  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}


object ExtractorTest {
  val email = Email("xxx", "sina.com")

  def patternMatching(x: String) = x match {
    // 下面的匹配会导致调用Email.unapply(email)
    case Email(name, domain) => println("name:" + name + " domain:" + domain)
    case _ => println("illegal email")
  }

  def main(args: Array[String]): Unit = {
    println(email) // xxx@sina.com
    patternMatching(email)
    patternMatching("xxx")
  }
}