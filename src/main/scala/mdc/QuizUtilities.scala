package mdc

import org.apache.spark.sql.functions._

object QuizUtilities {
  def percentage = (part: Int, whole: Int) =>  (BigDecimal(part) / BigDecimal(whole) * 100).toFloat min 100
  def roundUp = (num: Float) => (Math.round(num / 10.0) * 10) min 100
  def numberOfRightAnswers =  (questions: Seq[Boolean]) => questions.count(_ == true)

  val percentageUdf = udf(percentage)
  val roundUpUdf = udf(roundUp)
  val numberOfRightAnswersUdf = udf(numberOfRightAnswers)

}
