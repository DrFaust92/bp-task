package bpTask

import bpTask.common.LogEntry
import bpTask.service.core.Processing
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import org.junit._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Test
class AppTest {


  implicit val jsonDefaultFormats = DefaultFormats
  val genPath = "C:\\Users\\ilial\\Downloads\\generator-windows-amd64.exe"

  @Test
  def test_parsing_fail(): Unit = {

    val json = """{"name":"joe","age":15}"""
    try {
      JsonMethods.parse(json).extract[LogEntry]
    }
    catch {
      case ex: Exception => assert(true)
    }

    assert(false)

  }

  @Test
  def test_check_common_map(): Unit = {
    Future {
      val proc = new Processing(genPath)
      proc.start()
    }

    Thread.sleep(10000)

    val commonMap = Processing.dataOccur
    assert(commonMap.nonEmpty)

  }


  //    @Test
  //    def testKO() = assertTrue(false)

}


