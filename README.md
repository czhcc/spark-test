# spark-test
1.Spark编译
编译Spark时的命令，先设置虚拟机内存：
export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"
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




参考：
http://www.cnblogs.com/hseagle/p/3673147.html