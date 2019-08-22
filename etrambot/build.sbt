name := "etrambot"

version := "0.1"

scalaVersion := "2.12.8"

lazy val api = ProjectRef(uri("https://github.com/apimorphism/telegramium.git#master"), "telegramium-core")
lazy val root = project.in(file(".")).dependsOn(api)

scalacOptions += "-Ypartial-unification"

lazy val doobieVersion = "0.7.0"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"     % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-specs2"   % doobieVersion,
  "org.tpolecat" %% "doobie-hikari"   % doobieVersion
)
