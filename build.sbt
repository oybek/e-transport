ThisBuild / version      := "0.1"
ThisBuild / organization := "io.github.oybek"

val settings = Compiler.settings ++ Seq()

lazy val catpc = (project in file("."))
  .settings(name := "catpc")
  .settings(libraryDependencies ++= Dependencies.common)
