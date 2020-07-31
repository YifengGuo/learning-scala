package com.yifeng.scala.pattern.matching

import scala.collection.mutable.ArrayBuffer

/**
  * @author guoyifeng on 2020-07-27
  *
  *         pattern matching
  */
object PatternMatchBasics {
  def main(args: Array[String]): Unit = {
    patternMatching
    PatternMatchingGuardCondition
    PatternMatchingExample
  }

  // java example
  //  for(int i = 0; i < 100; i++) {
  //    switch (i) {
  //      case 10:System.out.println("10");
  //      break;
  //      //在实际编码时，程序员很容易忽略break语句
  //      //这容易导致意外掉入另外一个分支
  //      case 50:System.out.println("50");
  //      case 80:System.out.println("80");
  //      default:
  //        break;
  //    }
  //  }

  /** ******************* scala pattern matching ******************************/

  // 它的实现方式是通过match关键字与 case X=>的方式实现的，
  // 其中case _表示除了 case 10,case 50,case 80的其余匹配，类似于java中的default。
  def patternMatching(): Unit = {
    for (i <- 1 to 100) {
      i match {
        case 10 => println(10)
        case 50 => println(50)
        case 80 => println(80)
        case _ =>
      }
    }
  }

  // _ 在scala语言中提供了更为灵活的匹配方式
  def PatternMatchingGuardCondition(): Unit = {
    for (i <- 1 to 100) {
      i match {
        case 10 => println(10)
        case 50 => println(50)
        case 80 => println(80)
        // 增加守卫条件 (guard conditions)
        case _ if (i % 4 == 0) => println(i + ":能被4整除")
        case _ if (i % 3 == 0) => println(i + ":能被3整除")
        case _ =>
      }
    }
  }

  // case 后可加表达式
  def PatternMatchingExample(): Unit = {
    val list = new ArrayBuffer[Int]()
    var x = 0
    for (i <- 1 to 100) {
      i match {
        //后面可以跟表达式
        case 10 => x = 10
        case 50 => println(50)
        case 80 => println(80)
        case _ if (i % 4 == 0) => list.append(i)
        case _ if (i % 3 == 0) => println(i + ":能被3整除")
        case _ =>
      }
    }
    println(x)
  }

}
