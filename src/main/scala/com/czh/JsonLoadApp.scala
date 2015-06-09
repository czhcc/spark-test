package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object JsonLoadApp {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Cache Table Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val df = sqlContext.jsonFile("file:////export/usr/hadoop2/spark-1.3.1/people.json")
    df.registerTempTable("myjson")
    sqlContext.cacheTable("myjson");
    sqlContext.sql("select * from myjson").saveAsTable("mytab", "parquet")
  }
}