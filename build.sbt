name := """SP_Project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.5"

herokuAppName in Compile := "afternoon-temple-82615"
herokuJdkVersion in Compile := "1.7.0_302"
herokuConfigVars in Compile := Map(
  "DATABASE_URL" -> "postgres://idwhdhjxarjdvb:aa9c68fcf0c3da083f9c8afffe8cd83d7951a35585c1f2c9f6f04e975adb6c74@ec2-34-233-0-64.compute-1.amazonaws.com:5432/d5fcu1g83515ur",
)
libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
