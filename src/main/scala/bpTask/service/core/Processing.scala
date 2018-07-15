package bpTask.service.core

import bpTask.common.LogEntry

import scala.collection.mutable


class Processing {


  def start() = {
    while (true) {
      //val json = Processing.jsonStream.head
      //transform json to LogEntry case class

      //example entry
      val logEntry = LogEntry("event_type", "data", Long.MinValue)

      //println(logEntry)

      Processing.lol(logEntry.data)

      //println(Processing.dataOccur.getOrElse(logEntry.data, 0))
      Thread.sleep(3000)

    }
  }


}

object Processing {
  type Word = String
  type Occur = Int
  val jsonStream: Stream[String] = Stream.Empty
  val dataOccur: mutable.HashMap[Word, Occur] = new mutable.HashMap[Word, Occur]()

  def lol(word: Word) = {
    dataOccur.get(word) match {
      case Some(occur) => Processing.dataOccur.update(word, occur + 1)
      case None => Processing.dataOccur.put(word, 0)
    }
  }
}
