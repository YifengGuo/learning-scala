package com.yifeng.scala.data.structure

/**
  * @author guoyifeng on 2020-07-23
  *
  *         List一但创建，其值不能被改变
  *         List具有递归结构（Recursive Structure),例如链表结构
  *         List类型和其它类型集合一样，它具有协变性（Covariant)，即对于类型S和T，如果S是T的子类型，则List[S]也是List[T]的子类型
  */
object ListTest {
  // ways init List
  val fruit1 = List("Apple", "Banana", "Orange")
  val fruit = List.apply("Apple", "Banana", "Orange")
  //采用::及Nil进行列表构建 由于::操作符的优先级是从右往左的，因此上一条语句等同于下面这条语句
  val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
  val nums1 = 1 :: 2 :: 3 :: 4 :: Nil

  def main(args: Array[String]): Unit = {
//    listApis
    companionObjectFunctions
  }

  def iterateList(): Unit = {
    for (i <- fruit) println("List Element: " + i)
  }

  def listApis(): Unit = {

    val empty = nums.isEmpty

    val head = nums.head

    val tail = nums.last

    // reverse list
    val reverse = nums.reverse

    // drop first n elements
    val x = nums drop 3

    // obtain first n elements
    val y = nums take 3

    // split the list, return tuple of two list split from original list
    // (List(1, 2),List(3, 4))
    val unit = nums.splitAt(2)
    //    print(unit)


    // 取除第一个元素外剩余的元素，返回的是列表
    val subList = nums.tail
    //    print(subList)

    // 取除最后一个元素外的元素，返回的是列表
    val init = nums.init

    val str = nums.mkString(",") // return string made up by elements split by given delimiter
  }

  def concatList(): Unit = {
    val total = List(1, 2, 3) ::: List(4, 5, 6)
  }

  /**
    * zip is to return list of tuple which created by merging two elements from two collections
    */
  def zipList(): List[(Int, Char)] = {
    val nums = List(1, 2, 3, 4)
    val chars = List('1', '2', '3', '4')
    val tuples = nums zip chars //  List((1,1), (2,2), (3,3), (4,4))
    tuples
  }

  /**
    * List 伴生对象方法
    */
  def companionObjectFunctions(): Unit = {
    // create list by apply
    List.apply(1, 2, 3)

    // create int list in a given range
    List.range(2, 7)

    // step is 2
    List.range(2, 7, 2)

    // step is -1
    List.range(6, 2, -1)  // List(6, 5, 4, 3)

    // create list with duplicate elements
    List.fill(5)("ele")

    // concatenate two lists
    List.concat(nums, nums1)

    val xss = List(List('a', 'b'), List('c'), List('d', 'e'))
    val flatten = xss.flatten  //  List(a, b, c, d, e)
  }

}
