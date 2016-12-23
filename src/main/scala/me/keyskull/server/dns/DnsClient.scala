package me.keyskull.server.dns

import java.net.InetSocketAddress

import akka.actor._
import akka.io.IO
import akka.util.Timeout
import com.github.mkroli.dns4s.akka._
import com.github.mkroli.dns4s.dsl._
import scala.concurrent.duration._

/**
  * Created by keyskull on 12/17/16.
  */
class DnsClient {
  implicit val system = ActorSystem("DnsServer")
  implicit val timeout = Timeout(5 seconds)

  IO(Dns) ! Dns.DnsPacket(Query ~ Questions(QName("google.de")), new InetSocketAddress("8.8.8.8", 53)) onSuccess {
    case Response(Answers(answers)) =>
      answers.collect {
        case ARecord(arecord) => println(arecord.address.getHostAddress)
      }
  }
}
