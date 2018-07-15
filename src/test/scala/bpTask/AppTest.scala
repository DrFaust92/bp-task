package bpTask

import bpTask.common.LogEntry
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import org.junit._

@Test
class AppTest {

  @Test
  def testOK() = {
    implicit val jsonDefaultFormats = DefaultFormats
    val json = """{"name":"joe","age":15}"""
    try {
      JsonMethods.parse(json).extract[LogEntry]
    }
    catch {
      case ex: Exception => println("lol error")
    }

    assert(true)
  }

  //    @Test
  //    def testKO() = assertTrue(false)

}


