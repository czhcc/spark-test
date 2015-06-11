package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object OracleLoadApp {
  def main(args: Array[String])
  {
//    val conf = new SparkConf().setAppName("Oracle Load Application")
    
    val conf = new SparkConf().setAppName("Oracle Read Application")
    
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val jdbcDF = sqlContext.load("jdbc", 
        Map("driver"->"oracle.jdbc.driver.OracleDriver",
            "url" -> "jdbc:oracle:thin:@192.168.0.204:1521:orcl",
            "user"->"win2_test",
            "password"->"win2_test",
            "dbtable" -> "(select sheng,shi,xb,whcd,to_char(rs) rs from test_fxt)"))//(select sheng,shi,xb,whcd,to_char(rs) rs from test_fxt)
    jdbcDF.saveAsTable("test_fxt", "parquet", SaveMode.Overwrite)
    
    sc.stop()
  }
}