ThisBuild / version      := "0.1"
ThisBuild / organization := "io.github.oybek"

val settings = Compiler.settings ++ Seq()

lazy val evmsell = (project in file("."))
  .settings(name := "evmsell")
  .settings(libraryDependencies ++= Dependencies.common)
