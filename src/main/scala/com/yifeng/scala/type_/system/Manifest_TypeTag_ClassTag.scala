package com.yifeng.scala.type_.system

import scala.reflect.runtime.universe._


/**
 * @author guoyifeng on 2020-08-19
 *
 *         由于类型擦除的影响，编译期存在的类型信息在编译后不存在了，
 *         在程序运行时不能获取该信息，但某些场景下可能需要得到编译期的类型信息，scala能够做到这一点
 *         它通过Manifest和TypeTag来保存类型信息并在运行时使用该信息
 *         TypeTag可以用来替代Manifest，功能更强大一点，Manifest不能识别路径依赖类型
 *         例如对于class Outter{ class Inner}，假设分别创建了两个不同的外部类，outter.Inner, outter2.Inner，
 *         Manifest就会识别为同一类型，而TypeTag不会，另外TypeTag可以使用typeOf[T] 来检查类型参数。
 */
object Manifest_TypeTag_ClassTag {

}

// https://stackoverflow.com/questions/3427345/what-do-and-mean-in-scala-2-8-and-where-are-they-documented
// what is <:< =:= and >:>
object ManifestType {
  // 隐式参数m由编译器根据上下文自动传入，例如print1(List(“one”, “two”)) ，
  // 编译器会根据”one”,”two” 实际类型推断出 T 的类型是 String，
  // 再隐式地传入了Manifest[String]类型的对象参数，使得运行时可以根据这个参数做更多的事情。
  // 这种写法和下面的TypeTag实际是一个意思，TypeTag隐藏了具体的隐式参数写法
  def print1[T](x: List[T])(implicit m: Manifest[T]) = {
    if (m <:< manifest[String]) // m is subtype of manifest[String]
      println("字符串类型的List")
    else
      println("非字符串类型的List")
  }

  def main(args: Array[String]): Unit = {
    print1(List("one", "two"))
    print1(List(1, 2))
    print1(List("one", 2))
  }
}

// typeTag返回的是具体的类型，而不是类型擦除之后的类型any，即TypeTag保存所有具体的类型。
// 在运行时可以通过模式匹配来精确地对类型进行判断
object TypeTag extends App {

  def getTypeTag[T: TypeTag](a: T): TypeTag[T] = typeTag[T]

  //下列语句返回TypeTag[List[Int]]
  println(getTypeTag(List(1, 2, 3)))
}

object TypeTagPatternMatching {
  // typeOf[A]接受一个类型为TypeTag[a]的隐式参数，编译器生成的TypeTag隐式参数会被传给typeOf[A]
  def typeTagMatch[A: TypeTag](list: List[A]) = typeOf[A] match {
    case t if t =:= typeOf[String] => "List of Strings"
    case t if t <:< typeOf[Int] => "List of Integers"
  }

  def main(args: Array[String]): Unit = {
    val l = List(1, 2, 3)
    val l2 = List("1", "2")
    println(typeTagMatch(l))
    println(typeTagMatch(l2))
  }
}

// scala有4种TypeTag
// 1 scala.reflect.api.TypeTags#TypeTag. A full type descriptor of a Scala type.
//   For example, a TypeTag[List[String]] contains all type information, in this case, of typescala.List[String].
// 2 scala.reflect.ClassTag. A partial type descriptor of a Scala type.
//   For example, a ClassTag[List[String]] contains only the erased class type information, in this case, of type
// 3 scala.collection.immutable.List.ClassTags provide access only to the runtime class of a type.
//   Analogous to scala.reflect.ClassManifest.
// 4 scala.reflect.api.TypeTags#WeakTypeTag. A type descriptor for abstract types.