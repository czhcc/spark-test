package com.czh

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import java.util.Date
import org.apache.spark.sql.types.StringType
import java.util.Calendar

/**
 * @author czhcc
 */
object Vehiclepass {
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  
  def diff(d:Date,df:Integer){
    val c = Calendar.getInstance()
    c.setTime(d);
    return c.add(Calendar.MINUTE, df);
  }
  
  def main(args: Array[String])
  {
    val conf = new SparkConf().setAppName("Oracle Read Application")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    
    val cp = "粤X29791"//粤X29791,粤Y0Y731
    val util = new Util()
    val pt = format.parse("2015-04-01")
    sqlContext.udf.register("diff", util.diff _)
//    sqlContext.udf.register("date_format", (arg1:java.util.Date)=>Vehiclepass.format.format(arg1))
//    sqlContext.sql(s"""select PLATEINFO,PLATETYPE,KDBH,PASSTIME,DIRECTINDEX,DEVICEINDEX from BAYONET_VEHICLEPASS_NEW where PLATEINFO='$cp'""").show()
    val d1 = sqlContext.sql("select PLATEINFO,PLATETYPE,KDBH,PASSTIME,DIRECTINDEX,DEVICEINDEX " + 
        "from BAYONET_VEHICLEPASS_NEW " + 
        "where passtime>=cast('2015-04-25' as date) and passtime<cast('2015-04-26' as date)")
//    sqlContext.sql("select PLATEINFO,PLATETYPE,KDBH,PASSTIME,DIRECTINDEX,DEVICEINDEX from BAYONET_VEHICLEPASS_NEW where cast(passtime as string)>='2015-04-01 00:00:00' and cast(passtime as string)<'2015-04-22 00:00:00'").show()
    d1.registerTempTable("t1")
    sqlContext.cacheTable("t1")
    val d2 = sqlContext.sql("select plateinfo,platetype,kdbh,passtime,directindex,deviceindex,diff(passtime,-10) as fg,diff(passtime,10) as bg from t1 where plateinfo='粤X29791' and platetype='2'")
    d2.registerTempTable("t2")
    val d3 = sqlContext.sql("select * from t1 where plateinfo<>'粤X29791' and platetype<>'2'")
    d3.registerTempTable("t3")
    sqlContext.sql("select t3.* from t2 join t3 on (t2.deviceindex=t3.deviceindex and t2.kdbh=t3.kdbh) where t3.passtime>=t2.fg and t3.passtime<t2.bg").show()
  }
}