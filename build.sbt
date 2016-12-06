lazy val commonSettings = Seq(
  organization := "me.keyskull",
  scalaVersion := "2.11.8"
)

lazy val core = project in file("core")
lazy val server = (project in file(".") dependsOn (core)).
  settings(commonSettings: _*).settings(libraryDependencies ++=
  "org.apache.spark" %% "spark-core" % "2.0.2"
    :: Nil)


