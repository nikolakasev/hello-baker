name := "hello-baker"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "com.ing.baker" %% "recipe-dsl" % "1.1.12"
libraryDependencies += "com.ing.baker" %% "runtime" % "1.1.12"
libraryDependencies += "com.ing.baker" %% "compiler" % "1.1.12"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.6"