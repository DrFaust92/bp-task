package bpTask.service.http

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.{Directives, ExceptionHandler, Route}
import bpTask.service.core.Processing
import org.json4s._
import org.json4s.native.Serialization.write


trait Resource extends Directives {

  implicit val formats = DefaultFormats


  val myExceptionHandler = ExceptionHandler {
    case e =>
      extractUri { uri =>
        println(s"Request to $uri could not be handled normally. Exception ${e.getMessage} thrown")
        complete(HttpResponse(500, entity = s"Exception ${e.getMessage} thrown"))
      }
  }


  def routes: Route =
    handleExceptions(myExceptionHandler) {
      pathPrefix("bp-task") {
        get {
          pathSingleSlash {
            //print entire map
            //probably should outout as json
            complete(HttpEntity(ContentTypes.`application/json`,
              {
              try {
                Processing.addOccur()
                write(Processing.getOccur)
              }
                catch {
                  case ex: Exception => write(ex.getMessage)
                }
            }
            ))
          }
        }
//        ~
//          pathPrefix(Segment) { word =>
//            pathEnd {
//              get {
//                complete(HttpEntity(ContentTypes.`application/json`, Processing.dataOccur.getOrElse(word, 0).toString))
//              }
//            }
//          }
      }
    }
}





