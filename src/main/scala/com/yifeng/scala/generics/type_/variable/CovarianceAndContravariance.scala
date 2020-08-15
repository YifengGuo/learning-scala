package com.yifeng.scala.generics.type_.variable

import java.util

/**
 * Created by guoyifeng on 8/14/20
 */
object CovarianceAndContravariance {

}

// trait List[+T] {} 。当类型S是类型A的子类型时，则List[S]也可以认为是List[A}的子类型，即List[S]可以泛化为List[A]。
// 被参数化类型的泛化方向与参数类型的方向是一致的，所以称为协变（covariance）
// String ---> AnyRef
// List[String] ---> List[AnyRef]
object Covariance {

  // java由于类型安全不支持covariance
  //    java.util.List<String> s1=new util.LinkedList<String>();
  //    java.util.List<Object> s2=new util.LinkedList<Object>();
  //    //下面这条语句会报错
  //    // Type mismatch: cannot convert from
  //    // List<String> to List<Object>
  //    s2=s1;

  // scala要利用协变必须显式声明协变类，不声明则不生效
  class List[T](val head: T, val tail: List[T])

  //用+标识泛型T，表示CovarianceList类具有协变性
  class CovarianceList[+T](val head: T, val tail: CovarianceList[T]) {
    // 将函数也用泛型表示
    // 泛型T定义为协变之后，泛型便不能直接应用于成员方法当中，即不能写成 prepend(newHead:T):CovarianceList[T]
    // 因为是协变的，输入的类型必须是T的超类
    def prepend[U >: T](newHead: U): CovarianceList[U] = new CovarianceList(newHead, this)

    override def toString() = "" + head
  }

  def main(args: Array[String]): Unit = {
    // 此时遵从类型安全，无法编译通过
    //    val list:List[Any]= new List[String]("xxx",null)
    // 此时String是Any的子类型，则CovarianceList[String]是CovarianceList[Any]子类
    val covarianceList:CovarianceList[Any] = new CovarianceList[String]("xxx", null)
  }
}


// trait List[-T] {} 当类型S是类型A的子类型，则Queue[A]反过来可以认为是Queue[S}的子类型。
// 也就是被参数化类型的泛化方向与参数类型的方向是相反的，所以称为逆变（contravariance）。
// String ----> AnyRef
// List[AnyRef] ----> List[String]
object Contravariance {

}

