package com.ky

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.SaveMode

/**
 * t_wbxx_wbswry_fqtest 81672169
 * @author czhcc
 */
object WbxxWbswryLoad extends Serializable {
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName(this.getClass.getName)
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val jdbcDF = sqlContext.load("jdbc", 
        Map("driver"->"oracle.jdbc.driver.OracleDriver",
            "url" -> "jdbc:oracle:thin:@192.168.0.204:1521:orcl",
            "user"->"win",
            "password"->"win",
            "dbtable" -> "v_t_wbxx_wbswry_fqtest"))//(select sheng,shi,xb,whcd,to_char(rs) rs from test_fxt)
    jdbcDF.saveAsTable("t_wbxx_wbswry_fqtest", "parquet", SaveMode.Overwrite)
    
    sc.stop()
  }
}