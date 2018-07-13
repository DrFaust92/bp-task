package bpTask

import akka.http.scaladsl.Http
import bpTask.service.http.RestInterface


object Main extends App with RestInterface {
  println("Hello World!")

  if (args.length != 2) {
    println("Please provide 2 parameters: service host, service port")
    System.exit(1)
  }

  val restHost = args(0)
  val restPort = args(1).toInt


  Http().bindAndHandle(handler = routes, interface = restHost, port = restPort) map { binding =>
    println(s"REST interface bound to ${binding.localAddress}")
  } recover { case ex =>
    println(s"REST interface could not bind to $restHost:$restPort", ex.getMessage)
  }

}
