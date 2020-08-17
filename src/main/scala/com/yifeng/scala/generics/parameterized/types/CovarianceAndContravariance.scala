package com.yifeng.scala.generics.parameterized.types

import java.util

/**
  * Created by guoyifeng on 8/14/20
  *
  * 在scala中:函数的参数必须是逆变的，而返回值必须是协变的!!!
  * 从《Effective Java》PECS 原则 (producser-extends, consumer-super)理解
  * 对于trait Function2[-T1, -T2, +R] extends AnyRef
  * 它消费-T1,-T2，即T1与他的超类，T2与它的超类，产生（返回）R及他的子类。
  * 即接受参数范围更大=>处理能力增强,返回R的子类，也不与返回类型声明冲突，保证了类型安全
  * 子类中函数参数的必须是父类中函数参数的超类，这样的话父类能做的子类也能做。因此需要将类中的泛型参数声明为逆变或不变的。
  *
  * 从里氏替换法则理解：
  * 参数逆变：正是因为需要符合里氏替换法则，方法中的参数类型声明时必须符合逆变（或不变），
  * 以让子类方法可以接收更大的范围的参数(处理能力增强)；而不能声明为协变，
  * 子类方法可接收的范围是父类中参数类型的子集(处理能力减弱)。
  * 返回值协变：如果结果类型是逆变的，那子类方法的处理能力是减弱的，不符合里氏替换。
  *
  *
  * covariance, contravariance and invariance between scala and java
  *
  * Scala         Java            Description
  * +T          ? extends T        Covariant (e.g., List[T_sub] is subtype of List[T])
  * -T          ? super T          Contravariant (e.g., X[T_super] is subtype of X[T])
  * T            ---               Invariant (cannot substitute Y[T_super] or Y[T_sub] for Y[T])
  *
  * variance behavior is part of type declaration in Scala while variable declaration in Java
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
    val covarianceList: CovarianceList[Any] = new CovarianceList[String]("xxx", null)
  }
}


// trait List[-T] {} 当类型S是类型A的子类型，则Queue[A]反过来可以认为是Queue[S}的子类型。
// 也就是被参数化类型的泛化方向与参数类型的方向是相反的，所以称为逆变（contravariance）。
// String ----> AnyRef
// List[AnyRef] ----> List[String]
object Contravariance {

  class CSuper {
    def msuper(): Unit = println("CSuper")
  }

  class C extends CSuper {
    def m(): Unit = println("C")
  }

  class CSub extends C {
    def msub: Unit = println("CSub")
  }

  // Liskov Substitute Principle
  // 函数的参数必须是逆变的，而返回值必须是协变的
  var f: C => C = (c: C) => new C
  f = (c: CSuper) => new CSub
  f = (c: CSuper) => new C
  f = (c:C) => new CSub
//  f = (c:CSub) => new CSuper  // Compilation error
}

