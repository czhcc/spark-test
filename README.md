# spark-test
1.Spark编译
编译Spark时的命令，先设置虚拟机内存：
Linux:
export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"
Windows:
set MAVEN_OPTS=-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m
然后执行
mvn -Pyarn -Phadoop-2.4 -Dhadoop.version=2.5.0 -Phive -Phive-0.13.1 -Phive-thriftserver -DskipTests clean package

2.启动本地Spark环境
在Windows下，先设置Hadoop路径
set HADOOP_HOME=D:\opentools\apache\hadoop-2.2.0
否则会出现
java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.  
详细解决方案：
http://zy19982004.iteye.com/blog/2024467

启动Master：
spark-class org.apache.spark.deploy.master.Master --ip localhost --port 7077 --webui-port 8080
启动Worker：
spark-class org.apache.spark.deploy.worker.Worker spark://localhost:7077

这个时候就可以启动spark-shell：
spark-shell --master spark://localhost:7077

提交Spark程序执行
spark-submit --master spark://localhost:7077 --class com.czh.App abc.jar

--driver-class-path D:\oracle\client\ojdbc7.jar
--driver-library-path D:\oracle\client\ojdbc7.jar

3.和Hive有关的启动
启动metastore       命令  ./hive --service metastore
在公司里是238

再239启动hiveserver2   命令  ./hive --service hiveserver2
239的这个启动是单纯的Hive启动，和Spark没关

启动thriftserver时需要查看hive-site.xml文件，看看是在那台机器上启动thriftserver
在hive-site.xml里的hive.server2.thrift.bind.host参数
然后
start-thriftserver.sh --master spark://home7:7077

4.在Eclipse里运行
http://f.dataguru.cn/thread-322668-1-1.html
http://blog.csdn.net/xiao_jun_0820/article/details/43230225
http://my.oschina.net/132722/blog/196027
http://felixcui-blog.readthedocs.org/en/latest/spark/spark-yarn-internal.html

5.环境查看
http://h238:8088/cluster/apps
http://h238:18080/

6.MySql问题
FAILED: Error in metadata: javax.jdo.JDOException: Couldnt obtain a new sequence (unique id) : Binary logging not possible. Message: Transaction level 'READ-COMMITTED' in InnoDB is not safe for binlog mode 'STATEMENT'

因为，READ-COMMITTED需要把bin-log以mixed方式来记录，用以下命令来修改：

set global binlog_format='MIXED';


参考：
http://www.cnblogs.com/hseagle/p/3673147.html