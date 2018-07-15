package bpTask

import akka.http.scaladsl.Http
import bpTask.service.core.Processing
import bpTask.service.http.RestInterface

import scala.concurrent.Future


object Main extends App with RestInterface {
  println("Hello World!")

  if (args.length != 3) {
    println("Please provide 3 parameters: service host, service port, log generator path")
    System.exit(1)
  }

  val restHost = args(0)
  val restPort = args(1).toInt
  val logGenPath = args(2)

  //start stream reading
  //separate thread from http logic
  Future {
    val proc = new Processing(logGenPath)
    proc.start()
  }

  Http().bindAndHandle(handler = routes, interface = restHost, port = restPort) map { binding =>
    println(s"REST interface bound to ${binding.localAddress}")
  } recover { case ex =>
    println(s"REST interface could not bind to $restHost:$restPort", ex.getMessage)
  }


}
