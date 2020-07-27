package com.yifeng.scala.regex

/**
  * @author guoyifeng on 2020-07-27
  */
object RegExBasics {
  def main(args: Array[String]): Unit = {
    simpleRegEx
    extractor
  }

  def simpleRegEx(): Unit = {
    val sparkRegex = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$".r
    for (matchString <- sparkRegex.findAllIn("zhouzhihubeyond@sina.com")) {
      println(matchString)
    }
  }

  // Scala Extractor
  def extractor(): Unit = {
    val ipRegex = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)".r
    for (ipRegex(one, two, three, four) <- ipRegex.findAllIn("192.168.1.1")) {
      println("IP子段1:" + one)
      println("IP子段2:" + two)
      println("IP子段3:" + three)
      println("IP子段4:" + four)
    }
  }

}
