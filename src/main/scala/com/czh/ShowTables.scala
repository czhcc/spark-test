package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object ShowTables {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Cache Table Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.tableNames().foreach( x => println )
  }
}