package me.keyskull.server.dns

import akka.actor._
import akka.io.IO
import akka.util.Timeout
import com.github.mkroli.dns4s.akka._
import com.github.mkroli.dns4s.dsl._

import scala.concurrent.duration._

/**
  * Created by keyskull on 12/17/16.
  */
class DnsHandlerActor extends Actor {
  override def receive = {
    case Query(q) ~ Questions(QName(host) ~ TypeA() :: Nil) =>
      sender ! Response(q) ~ Answers(RRName(host) ~ ARecord("1.2.3.4"))
  }
}

object DnsServer extends App {
  implicit val system = ActorSystem("DnsServer")
  implicit val timeout = Timeout(5 seconds)
  IO(Dns) ! Dns.Bind(system.actorOf(Props[DnsHandlerActor]), 53)
}

