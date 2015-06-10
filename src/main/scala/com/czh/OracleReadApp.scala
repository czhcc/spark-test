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
    val conf = new SparkConf().setMaster("spark://localhost:7077").setAppName("Oracle Read Application")
    conf.set("spark.driver.extraClassPath", "D:\\oracle\\client\\ojdbc7.jar")
    conf.setSparkHome("D:\\opentools\\apache\\spark-1.3.1-bin-hadoop2.4")
    val sc = new SparkContext(conf)
    sc.addJar("D:\\opentools\\apache\\spark-1.3.1-bin-hadoop2.4\\spark-test-0.0.1-SNAPSHOT.jar")
    val sqlContext = new HiveContext(sc)
//    sqlContext.udf.register("datalength", (arg1:String)=>arg1.length())
    val d1 = sqlContext.sql("select * from test_fxt")
    d1.show()
    
    sc.stop()
  }
}