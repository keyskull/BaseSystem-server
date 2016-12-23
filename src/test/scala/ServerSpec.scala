/**
  * Created by keyskull on 12/4/16.
  */

import java.sql.DriverManager

import me.keyskull.core.Initialization
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.store.{BlockStore, MySQLFullPrunedBlockStore}

import collection.mutable.Stack
import org.scalatest._

import scala.util.Try

class ServerSpec extends FlatSpec with Matchers {

  "A Initialization" should "finish" in {
      new me.keyskull.server.InitServer("test","0.1").Init(Array[String]())
    "" should be("")
  }

}