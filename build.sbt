import sbtassembly.AssemblyPlugin.autoImport.assemblyJarName

lazy val commonSettings = Seq(
  organization := "me.keyskull",
  scalaVersion := "2.11.8"
)

lazy val core = project in file("core")
lazy val server = (project in file(".") dependsOn(core)).
  settings(commonSettings: _*).settings(
  resolvers += "bintray" at "http://jcenter.bintray.com",
  libraryDependencies ++=
  "org.apache.spark" %% "spark-core" % "2.0.2" % "provided"::
//    "org.bitcoinj" % "bitcoinj-tools" % "0.14.3" ::
//    "org.bitcoinj" % "bitcoinj-parent" % "0.14.3" ::
    "com.github.mkroli" %% "dns4s-akka" % "0.10"

    :: Nil).settings(mainClass in assembly := Some("me.keyskull.server.Launch"),
  assemblyJarName in assembly := "server.jar")


