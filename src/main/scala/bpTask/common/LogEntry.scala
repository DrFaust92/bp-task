package bpTask.common

//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
//import spray.json.{DefaultJsonProtocol, RootJsonFormat}


case class LogEntry(event_type: String, data: String, timestamp: Long)

//case object LogEntry extends SprayJsonSupport with DefaultJsonProtocol {
//  implicit val logEntryFormat: RootJsonFormat[LogEntry] = jsonFormat3(LogEntry.apply)
//}