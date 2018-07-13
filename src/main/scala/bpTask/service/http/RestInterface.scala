package bpTask.service.http

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent._
import scala.concurrent.duration._

trait RestInterface extends Resource {

  val restHost: String
  val restPort: Int

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()


  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val timeout: Timeout = Timeout(1 seconds)


}
