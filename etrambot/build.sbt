name := "etrambot"

version := "0.1"

scalaVersion := "2.12.8"

lazy val api = ProjectRef(uri("https://github.com/apimorphism/telegramium.git#master"), "telegramium-core")
lazy val root = project.in(file(".")).dependsOn(api)
