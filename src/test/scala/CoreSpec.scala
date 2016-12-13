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

class CoreSpec extends FlatSpec with Matchers {

  "A Initialization" should "finish" in {
    Try {
      DriverManager.getConnection("jdbc:apache:commons:dbcp:test")
    }
    val init = new Initialization("root", "0.1"){
      override def getBlockStore(params: NetworkParameters): BlockStore = new MySQLFullPrunedBlockStore(params,1000,"mysql.clvltzbfniai.us-west-1.rds.amazonaws.com","bitcoin_blockchain","keyskull","410615903")
    }.Init(Array[String]())
    println(init.getGenesisBlock)
    init.getGenesisBlock should be("")
  }

}