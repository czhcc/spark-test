package com.czh

import java.util.Calendar
import java.util.Date

/**
 * @author czhcc
 */
class Util extends Serializable {
  def diff(d:java.sql.Timestamp,df:Integer) : java.sql.Timestamp ={
    val c = Calendar.getInstance()
    c.setTime(d);
    c.add(Calendar.MINUTE, df);
    return new java.sql.Timestamp(c.getTimeInMillis);
  }
}