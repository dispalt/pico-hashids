import Build.{scalacheck, specs2_core, specs2_scalacheck}

import scala.sys.process._
import Build._

name := "pico-hashids"

organization := "com.dispalt"

lazy val `pico-hashids` = Project(id = "pico-hashids", base = file("pico-hashids"))
  .standard
  .settings(publishTo := sonatypePublishToBundle.value)
  .settings(crossScalaVersions := Seq("2.11.8", "2.12.12", "2.13.3"))
  .testLibs(scalacheck, specs2_core, specs2_scalacheck)

lazy val root = Project(id = "all", base = file("."))
  .notPublished
  .aggregate(`pico-hashids`)

crossScalaVersions := Seq("2.11.8", "2.12.12", "2.13.3")

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

version in ThisBuild := "4.5.153"

// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.dispalt"

publishTo := {
  val v = version.value
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/pico-works/pico-hashids</url>
  <licenses>
    <license>
      <name>BSD</name>
      <url>https://opensource.org/licenses/BSD-2-Clause</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/pico-works/pico-hashids</connection>
    <developerConnection>scm:git:git@github.com:pico-works/pico-hashids</developerConnection>
    <url>github.com/pico-works/pico-hashids</url>
  </scm>
  <developers>
    <developer>
      <id>picoworks</id>
      <name>picoworks</name>
      <url>http://picoworks.org</url>
    </developer>
  </developers>
}
