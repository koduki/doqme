organization := "cn.orz.pascal"

name := "DoqMe"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-core" % "0.9.30",
  "ch.qos.logback" % "logback-classic" % "0.9.30",
  "org.slf4j" % "slf4j-simple" % "1.6.2"
)

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "org.scala-tools.time" % "time_2.9.1" % "0.5"

libraryDependencies += "org.scalaj" % "scalaj-time_2.10.0-M7" % "0.6"

libraryDependencies += "com.google.code" % "lucene-gosen-ipadic" % "1.2.1"

libraryDependencies += "com.cloudhopper" % "ch-tokyocabinet-java" % "1.24.0"

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Sonatype OSS Public" at "http://oss.sonatype.org/content/groups/public/"

resolvers += "repo.novus rels" at "http://repo.novus.com/releases/"

resolvers += "repo.novus snaps" at "http://repo.novus.com/snapshots/"

resolvers += "T repo" at "http://maven.twttr.com/"

