package bpTask.service.core

import bpTask.common.LogEntry
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods

import scala.collection.mutable
import scala.util.Try

class Processing {

  implicit val jsonDefaultFormats = DefaultFormats

  def start() = {
    for {
      json <- Processing.jsonStream
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
  //val jsonStream: Stream[String] = Stream.Empty
  val dataOccur: mutable.HashMap[Word, Occur] = new mutable.HashMap[Word, Occur]()
  val cmd = "C:\\git\\testproj\\generator-windows-amd64.exe"

  import scala.sys.process._

  val jsonStream = cmd.lineStream_!

  def addWordCount(word: Word) = {
    dataOccur.get(word) match {
      case Some(occur) => Processing.dataOccur.update(word, occur + 1)
      case None => Processing.dataOccur.put(word, 0)
    }
  }
}
