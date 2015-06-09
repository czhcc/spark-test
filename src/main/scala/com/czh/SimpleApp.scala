package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
 * @author czhcc
 */
object SimpleApp {
  def main(args: Array[String])
  {
//    val logFile = "file:///D:/opentools/apache/spark-1.3.1-bin-hadoop2.4/README.md" // Should be some file on your system
    val logFile = "file:////export/usr/hadoop2/spark-1.3.1/derby.log" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
//    val conf = new SparkConf().setMaster("local").setAppName("Simple Application")  
//    val sc = new SparkContext("local", "My App")  
    val logData = sc.textFile(logFile, 2).cache()
    val cou = logData.count()
    println("cou=" + cou)
//    val numAs = logData.filter(line => line.contains("a")).count()
//    val numBs = logData.filter(line => line.contains("b")).count()
//    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
  
}