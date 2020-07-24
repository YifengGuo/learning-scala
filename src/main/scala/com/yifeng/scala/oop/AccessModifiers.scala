package com.yifeng.scala.oop

/**
  * @author guoyifeng on 2020-07-24
  *
  *         修饰符                     访问范围
  *         无任何修饰符              任何地方都可以使用
  *         private[scala]          在定义的类中可以访问，在scala包及子包中可以访问
  *         private[this]           只能在定义的类中访问，即使伴生对象也不能访问团
  *         private                 在定义的的类及伴生对象中可以访问，其它地方不能访问
  *         protected[scala]        在定义的类及子类中可以访问，在scala包及子包中可以访问
  *         protected[this]         只能在定义的类及子类中访问，即使伴生对象也不能访问
  *         protected               在定义的类及子类中访问，伴生对象可以访问，其它地方不能访问
  *
  */
object AccessModifiers {

  // 1. implicit import
  // scala will import packages blow implicitly
  // scala._
  // Predef._
  // java.lang._

  // 2. package rename
  // import java.util.{ HashMap => JavaHashMap } 将java.util.HashMap重命名为JavaHashMap


  // 3. hide classes
  // 通过HashMap=> _，这样类便被隐藏起来了
  import java.util.{HashMap => _, _}
  import scala.collection.mutable.HashMap
  object RenameUsage {
    def main(args: Array[String]): Unit = {

      //这样的话,HashMap便无歧义地指向scala.collection.mutable.HashMap
      val scalaHashMap = new HashMap[String, String]
      scalaHashMap.put("Spark", "excellent")
      scalaHashMap.put("MapReduce", "good")
      scalaHashMap.foreach(e => {
        val (k, v) = e
        println(k + ":" + v)
      })
    }
  }

}
