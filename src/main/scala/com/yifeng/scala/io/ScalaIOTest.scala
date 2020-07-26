package com.yifeng.scala.io

import java.io.FileWriter
import java.net.URL

import scala.io.Source

/**
  * @author guoyifeng on 2020-07-25
  */
object ScalaIOTest {

  val path = "/Users/guoyifeng/Documents/myJava/learning-scala/src/main/scala/com/yifeng/scala/io"

  def main(args: Array[String]): Unit = {
    write()
    read()
    networkIO
  }

  // write
  def write(): Unit = {
    val fileWriter = new FileWriter(path + "/file.txt")
    fileWriter.write("scala file writer")
    fileWriter.flush()
    fileWriter.close()
  }

  // read
  def read(): Unit = {
    //读取文件
    val file = Source.fromFile(path + "/file.txt")
    //返回Iterator[String]
    val lines = file.getLines()
    //打印内容
    for (i <- lines) println(i)
    //关闭文件
    file.close()
  }

  // network IO
  def networkIO(): Unit = {
    val source = Source.fromURL(new URL("http://www.baidu.com"))
    println(source.mkString)
    source.close()
  }
}
