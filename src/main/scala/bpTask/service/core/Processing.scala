package bpTask.service.core

import com.redis._
import org.json4s.DefaultFormats

class Processing /*(cmdPath: String)*/ {

  implicit val jsonDefaultFormats = DefaultFormats


  //  import scala.sys.process._

  // neat trick ive learned while working on the project
  //  private val jsonStream: Stream[String] = cmdPath.lineStream_!


  //  def start(): Unit = {
  //    for {
  //      json <- jsonStream
  //    } {
  //      val logEntry = Try(JsonMethods.parse(json).extract[LogEntry])
  //
  //      logEntry match {
  //        case scala.util.Success(entry) => Processing.addWordCount(entry.data)
  //        case scala.util.Failure(_) => //maybe add some logs for errors
  //      }
  //
  //    }
  //  }
}

object Processing {
  type Word = String
  type Occur = Int

  var r: RedisClient = _

  try {
    r = new RedisClient("redis", 6379)
  }
  catch {
    case ex: Exception => println(ex.getMessage)
  }

  try {
    r.set("key", 0)
  }
  catch {
    case ex: Exception => println(ex.getMessage)
  }



  def addOccur(): Unit = {
    val occ = getOccur.toInt
    r.set("key", occ + 1)
    println("added")
  }


  def getOccur: String = {
    r.get("key") match {
      case Some(occ) => occ
      case None => "0"
    }
  }

  //val dataOccur: mutable.HashMap[Word, Occur] = new mutable.HashMap[Word, Occur]()


  //  def addWordCount(word: Word): Unit = {
  //    r.get(word) match {
  //    case Some(occur) => r.set(word,occur +1)
  //    case None => r.set(word, 1)
  //
  //    }

  //    dataOccur.get(word) match {
  //      case Some(occur) => Processing.dataOccur.update(word, occur + 1)
  //      case None => Processing.dataOccur.put(word, 0)
  //    }
}
