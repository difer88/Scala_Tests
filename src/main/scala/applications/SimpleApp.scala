package applications

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

class SimpleApp {

}

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "hdfs://localhost:9000/diego/data.csv" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}