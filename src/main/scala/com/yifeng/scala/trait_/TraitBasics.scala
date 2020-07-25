package com.yifeng.scala.trait_

/**
  * @author guoyifeng on 2020-07-24
  *
  *         scala的trait中，它不但可以包括抽象方法还可以包含字段和具体方法
  */
object TraitBasics {
  def main(args: Array[String]): Unit = {

  }


}

trait DAO {
  //定义一个抽象方法，注意不需要加abstract
  //加了abstract反而会报错
  def delete(id: String): Boolean

  def add(o: Any): Boolean

  def update(o: Any): Int

  def query(id: String): List[Any]
}


// extends from single trait
class DaoImpl extends MysqlDAO {
  def add(o: Any): Boolean = true

  def update(o: Any): Int = 1

  def query(id: String): List[Any] = List(1, 2, 3)
}

// extends from multiple traits
class DaoImpl2 extends MysqlDAO with Cloneable {
  def add(o: Any): Boolean = true

  def update(o: Any): Int = 1

  def query(id: String): List[Any] = List(1, 2, 3)

  override def clone(): AnyRef = super.clone()
}

// 1. normal trait
trait MysqlDAO {
  def add(o: Any): Boolean

  def update(o: Any): Int

  def query(id: String): List[Any]
}

// 2. trait with default method implementation
trait MysqlDAO1 {

  // 从字节码文件可以看出，带有具体实现的trait是通过java中的抽象类来实现的。
  def delete(id: String): Boolean = {
    println("delete implementation")
    true
  }

  def add(o: Any): Boolean

  def update(o: Any): Int

  def query(id: String): List[Any]
}


// 3. trait with abstract fields
trait DAOWithAbstractFields {

  var recodeMount:Long

  def delete(id:String):Boolean={
    println("delete implementation")
    true
  }
  def add(o:Any):Boolean
  def update(o:Any):Int
  def query(id:String):List[Any]
}

// 4. trait with initialized fields
trait DAOWithInitializedFields {

  var recodeMount = System.currentTimeMillis()

  def delete(id:String):Boolean={
    println("delete implementation")
    true
  }
  def add(o:Any):Boolean
  def update(o:Any):Int
  def query(id:String):List[Any]
}