package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.SaveMode

/**
 * @author czhcc
 */
object KkLoadApp {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Oracle Load Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val jdbcDF = sqlContext.load("jdbc", 
        Map("driver"->"oracle.jdbc.driver.OracleDriver",
            "url" -> "jdbc:oracle:thin:@10.1.11.4:1521:orcl",
            "user"->"ky_data",
            "password"->"ky_data",
            "dbtable" -> "(select * from V_KK where rownum<1000)"
            ))//"partitionColumn" -> "PASSTIME_PART"
    jdbcDF.saveAsTable("BAYONET_VEHICLEPASS_NEW", "parquet", SaveMode.Overwrite)
    
    /*val ddf = sqlContext.load("jdbc",Map("driver"->"oracle.jdbc.driver.OracleDriver",
            "url" -> "jdbc:oracle:thin:@10.1.11.4:1521:orcl",
            "user"->"ky_data",
            "password"->"ky_data",
            "dbtable" -> "v_kk_in",
            "partitionColumn" -> "PASSTIME_PART"))
    ddf.saveAsTable("v_kk_in", "parquet", SaveMode.Overwrite)*/
  }
}