package me.keyskull.server

import java.net.InetAddress

import org.bitcoinj.core._
import org.bitcoinj.core.listeners.BlocksDownloadedEventListener
import org.bitcoinj.net.discovery.DnsDiscovery
import org.bitcoinj.params.{MainNetParams, RegTestParams, TestNet3Params}
import org.bitcoinj.store.{BlockStore, MemoryBlockStore}
import org.bitcoinj.utils.BriefLogFormatter

/**
  * Created by Jane on 2016/11/21.
  */

class InitServer(programName: String, version: String) {
  private lazy val location = new me.keyskull.util.Location {
    override def Init() = null

  }.get

  def Init(args: Array[String]) = {
    BriefLogFormatter.init()
    val (params: NetworkParameters, filePrefix) = args.applyOrElse[Int, String](1, _ => "") match {
      case "testnet" =>
        (TestNet3Params.get(), "forwarding-service-testnet")
      case "regtest" =>
        (RegTestParams.get(), "forwarding-service-regtest")
      case _ =>
        (MainNetParams.get(), "forwarding-service-mainnet")
    }
//    Address.fromBase58(params,"1ALdfYANRYzKcmLrh7APH64N44jkoUCxQ4")

    println(filePrefix)
    val blockStore = new MemoryBlockStore(params)
    val blockChain = new BlockChain(params, blockStore)
    val peerGroup = new PeerGroup(params, blockChain)
    peerGroup.addPeerDiscovery(new DnsDiscovery(params))
    peerGroup.setUserAgent(programName,version)
    peerGroup.start()
    peerGroup.addAddress(new PeerAddress(InetAddress.getLocalHost(), params.getPort()))
    peerGroup.addBlocksDownloadedEventListener(new BlocksDownloadedEventListener {
      override def onBlocksDownloaded(peer: Peer, block: Block, filteredBlock: FilteredBlock, blocksLeft: Int) = {
        println(peer.getBestHeight)
        println(peer.getPeerVersionMessage)
        println(block.toString)
        println(blockChain.getBestChainHeight)
      }
    })
//    peerGroup.addChainDownloadStartedEventListener(new ChainDownloadStartedEventListener {
//      override def onChainDownloadStarted(peer: Peer, blocksLeft: Int) = {
//        peer
//      }
//    })

    peerGroup.downloadBlockChain()
    peerGroup.wait()
//    val blockHash = Sha256Hash.wrap("00000000839a8e6886ab5951d76f411475428afc90947ee320161bbf18eb6048")
//    peerGroup.waitForPeers(1).get()
//    println(peerGroup.getDownloadPeer.getBlock(blockHash).get().getMerkleRoot)

    //peerGroup.getConnectedPeers().asScala.map(peer => peer.)

    params
  }
}




