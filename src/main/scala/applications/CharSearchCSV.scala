package applications

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

class CharSearchCSV {

  val logFile = "hdfs://localhost:9000/diego/data.csv" // Should be some file on your system

  val sparkConf = new SparkConf().setAppName("Simple Application").setMaster("local")

  val sparkContext = new SparkContext(sparkConf)

  // Setting the log level
  sparkContext.setLogLevel("WARN")

  val logData = sparkContext.textFile(logFile, 2).cache()

}

object CharSearchCSV {

  def main(args: Array[String]) {

    val charSearchCSV = new CharSearchCSV

    val numAs = charSearchCSV.logData.filter(line => line.contains("a")).count()
    val numBs = charSearchCSV.logData.filter(line => line.contains("b")).count()

    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))

  }

}
