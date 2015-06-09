package com.czh

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object JsonTableReadApp {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Cache Table Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.table("mytab").show();
  }
}