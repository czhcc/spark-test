package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object OracleReadApp {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Oracle Read Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.table("test_fxt").show();
  }
}