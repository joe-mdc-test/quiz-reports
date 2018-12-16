package mdc

import java.io.ByteArrayOutputStream

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import mdc.QuizUtilities._

class QuizReporting {
  lazy val spark = SparkSession
    .builder()
    .master("local")
    .appName("Quiz Reporting MDC")
    .getOrCreate()

  import spark.implicits._

  def quizReport = {
    val dataFile = "quiz-data-sample.json"
    val collection = spark.read.json(dataFile).select("quizzes").as[Quizzes]

    val oneRowPerQuiz = collection.flatMap(user => user.quizzes)
    val collectionWithQuizLength = oneRowPerQuiz.withColumn("number_of_questions", size($"questions"))
    val collectionWithRightAnswers = oneRowPerQuiz.withColumn("no_correct", numberOfRightAnswersUdf($"questions.isCorrect"))

    val quizzesWithLengthAndRightAnswers = oneRowPerQuiz
      .join(collectionWithRightAnswers, Seq("quiz_id", "questions"))
      .join(collectionWithQuizLength, Seq("quiz_id", "questions"))

    val collatedWithPercent = quizzesWithLengthAndRightAnswers.withColumn("percentage", percentageUdf($"no_correct", $"number_of_questions"))
    val collatedWithPercentiles = collatedWithPercent.withColumn("rounded", roundUpUdf($"percentage"))
    val idAndPercentile = collatedWithPercentiles.select("quiz_id", "rounded")

    val countOfPercentileById = idAndPercentile.groupBy("quiz_id", "rounded").count

    val printFormat = countOfPercentileById.groupBy("quiz_id").pivot("rounded").agg(expr("coalesce(first(count), \"1\")"))

    val captureStdOut = new ByteArrayOutputStream
    Console.withOut(captureStdOut) {
      printFormat.sort("quiz_id").show()
    }

    val result = new String(captureStdOut.toByteArray)

    println("#" * 500)
    println(result)
    println("#" * 500)

    WriteReport.writeToLocal(result, "Quiz-Report")
  }
}
