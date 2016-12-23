package me.keyskull.server

import java.sql.DriverManager

import org.bitcoinj.core._
import org.bitcoinj.store.{BlockStore, MemoryBlockStore, MySQLFullPrunedBlockStore}

import scala.util.Try


/**
  * Created by Jane on 2016/11/21.
  */

object Launch {
  def main(args: Array[String]): Unit = {
    Try {
      DriverManager.getConnection("jdbc:apache:commons:dbcp:test")
    }
    new me.keyskull.core.Initialization("root", "0.1"){
      override def getBlockStore(params: NetworkParameters): BlockStore =
        new MySQLFullPrunedBlockStore(params,1000,"mysql.clvltzbfniai.us-west-1.rds.amazonaws.com","bitcoin_blockchain","keyskull","410615903")
    }.Init(Array[String]())
  }
}




