package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.SaveMode

/**
 * @author czhcc
 */
object OracleLoadUserApp {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Oracle Load Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val jdbcDF = sqlContext.load("jdbc", 
        Map("driver"->"oracle.jdbc.driver.OracleDriver",
            "url" -> "jdbc:oracle:thin:@10.1.11.4:1521:orcl",
            "user"->"win2_res",
            "password"->"win2_res",
            "dbtable" -> "(select username,password from acl_user)"))
    jdbcDF.saveAsTable("acl_user", "parquet", SaveMode.Overwrite)
  }
}