import sbt._

object Dependencies {

  object V {
    val monix      = "3.0.0-RC2"
    val monixNio   = "0.0.3"
    val catsCore   = "1.4.0"
    val catsEffect = "1.2.0"
    val catsMtl    = "0.4.0"
    val circe      = "0.12.1"
    val atto       = "0.6.4"
    val scalaTest  = "3.0.5"
    val fs2Core    = "1.0.0"
    val fs2IO      = "1.0.0"
    val scalaFmt   = "2.0.0-RC4"
    val http4s     = "0.20.0"
    val slf4j      = "1.7.26"
    val logback    = "1.2.3"
    val uPickle    = "0.7.1"
    val javaFmt    = "1.7"
    val junit      = "4.12"
    val pureConfig = "0.10.2"
    val flyway     = "5.2.4"
    val doobie     = "0.5.4"
  }

  val monix      = "io.monix" %% "monix" % V.monix
  val monixNio   = "io.monix" %% "monix-nio" % V.monixNio
  val fs2Core    = "co.fs2" %% "fs2-core" % V.fs2Core
  val fs2IO      = "co.fs2" %% "fs2-io" % V.fs2IO
  val catsCore   = "org.typelevel" %% "cats-core" % V.catsCore
  val catsEffect = "org.typelevel" %% "cats-effect" % V.catsEffect
  val catsMtl    = "org.typelevel" %% "cats-mtl-core" % V.catsMtl
  val scalaTest  = "org.scalatest" %% "scalatest" % V.scalaTest % Test
  val pureConfig = "com.github.pureconfig" %% "pureconfig" % V.pureConfig
  val flyway     = "org.flywaydb" % "flyway-core" % V.flyway

  val doobie = Seq(
    "org.tpolecat"          %% "doobie-core"          % V.doobie,
    "org.tpolecat"          %% "doobie-postgres"      % V.doobie,
    "org.tpolecat"          %% "doobie-hikari"        % V.doobie,
    "org.tpolecat"          %% "doobie-h2"            % V.doobie
  )

  val circe = Seq(
    "io.circe" %% "circe-core"           % V.circe,
    "io.circe" %% "circe-parser"         % V.circe,
    "io.circe" %% "circe-generic"        % V.circe,
    "io.circe" %% "circe-generic-extras" % V.circe
  )

  val http4s = Seq(
    "org.http4s" %% "http4s-dsl"          % V.http4s,
    "org.http4s" %% "http4s-circe"        % V.http4s,
    "org.http4s" %% "http4s-blaze-server" % V.http4s,
    "org.http4s" %% "http4s-blaze-client" % V.http4s
  )

  val logger = Seq(
    "org.slf4j"      % "slf4j-api"       % V.slf4j,
    "ch.qos.logback" % "logback-classic" % V.logback
  )

  val uPickle = Seq(
    "com.lihaoyi" %% "upickle" % V.uPickle,
    "com.lihaoyi" %% "upack" % V.uPickle
  )

  val common = Seq(
    monix,
    monixNio,
    fs2Core,
    fs2IO,
    catsCore,
    catsEffect,
    catsMtl,
    scalaTest,
    pureConfig,
    flyway
  ) ++ circe ++ http4s ++ logger ++ uPickle ++ doobie
}
