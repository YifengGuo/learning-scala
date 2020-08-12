package com.yifeng.scala.generics.type_.variable

/**
 * Created by guoyifeng on 8/5/20
 * upper bound <:
 * lower bound >:
 */
object UpperBoundAndLowerBound {

}

class Pair1[T](val first: T, val second: T) {
  //下界通过[R >: T]的意思是
  //泛型R的类型必须是T的超类
  def replaceFirst[R >: T](newFirst: R) = new Pair1[R](newFirst, second)

  override def toString() = first + "---" + second
}

//Book类
class Book(val name: String) {
  override def toString() = "name--" + name
}

//Book子类Ebook
class Ebook(name: String) extends Book(name)

//Book子类Pbook
class Pbook(name: String) extends Book(name)

//Pbook子类,WeridBook
class WeirdBook(name: String) extends Pbook(name)
