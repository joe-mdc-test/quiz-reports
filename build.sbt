name := "QuizzReports"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.4.0",
    "org.apache.spark" % "spark-sql_2.11" % "2.4.0",
    "org.scalactic" %% "scalactic" % "3.0.0",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
}
