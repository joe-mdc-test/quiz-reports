import mdc.QuizReporting
import org.scalatest._

import scala.io.Source
class QuizReportingSpec extends FlatSpec with Matchers {

  val reportGenerator = new QuizReporting


  "A Report Generator" should "Generate a report" in {
    reportGenerator.quizReport

    val expectedReport = Source.fromFile("SampleReport")

    val generatedFile = Source.fromFile("Quiz-Report")

    expectedReport.toSeq should equal (generatedFile.toSeq)
  }
}


