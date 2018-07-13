package bpTask.service.http

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.{Directives, ExceptionHandler, Route}


trait Resource extends Directives {


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
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
              "<html><body>Hello world!</body></html>"))
          }
        }
      }
    }

}




