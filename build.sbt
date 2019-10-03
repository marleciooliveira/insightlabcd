import com.typesafe.sbt.packager.docker.Cmd
import com.typesafe.sbt.packager.docker.DockerPermissionStrategy

name := """ci-project-test"""
organization := "br.ufc.insightlab"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "br.ufc.insightlab.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "br.ufc.insightlab.binders._"

maintainer in Docker := "Equipe Insight Lab Projeto SINESP"
//
dockerBaseImage := " openjdk:8u222-jre-stretch"
//
dockerRepository := Some("registry.gitlab.com/projeto-senasp-mj/ci-project-test")
//
dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("FROM", _) => List(
    cmd,
    Cmd("RUN", "mkdir -p /sbin/logs")
  )
  case other => List(other)
}
