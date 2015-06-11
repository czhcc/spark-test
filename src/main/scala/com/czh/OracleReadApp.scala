package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
object OracleReadApp extends Serializable {
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Oracle Read Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.udf.register("datalength", (arg1:String)=>arg1.length())
    val d1 = sqlContext.sql("select SHENG,SHI,XB,WHCD,RS,datalength(shi) as t from test_fxt")
    d1.show()
    
    sc.stop()
  }
}