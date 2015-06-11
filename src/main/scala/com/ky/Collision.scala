package com.ky

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

/**
 * @author czhcc
 */
class Collision extends Serializable {
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName(this.getClass.getName)
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    
    
    sc.stop()
  }
}