package bpTask.service.core

import bpTask.common.LogEntry
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods

import scala.collection.mutable
import scala.util.Try

class Processing(cmdPath: String) {

  implicit val jsonDefaultFormats = DefaultFormats


  import scala.sys.process._

  // neat trick ive learned while working on the project
  private val jsonStream: Stream[String] = cmdPath.lineStream_!

  def start(): Unit = {
    for {
      json <- jsonStream
    } {
      val logEntry = Try(JsonMethods.parse(json).extract[LogEntry])

      logEntry match {
        case scala.util.Success(entry) => Processing.addWordCount(entry.data)
        case scala.util.Failure(_) => //maybe add some logs for errors
      }

    }
  }
}

object Processing {
  type Word = String
  type Occur = Int

  val dataOccur: mutable.HashMap[Word, Occur] = new mutable.HashMap[Word, Occur]()

  def addWordCount(word: Word): Unit = {
    dataOccur.get(word) match {
      case Some(occur) => Processing.dataOccur.update(word, occur + 1)
      case None => Processing.dataOccur.put(word, 0)
    }
  }
}
