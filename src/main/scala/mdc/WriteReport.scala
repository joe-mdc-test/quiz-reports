package mdc
import java.io._

object WriteReport {


  def writeToLocal(report: String, fileName: String) = {
    val pw = new PrintWriter(new File(fileName))
    pw.write(report)
    pw.close
  }

}
