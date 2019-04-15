name := "hello-baker"

version := "0.2"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "com.ing.baker" %% "baker-recipe-dsl" % "2.0.3"
libraryDependencies += "com.ing.baker" %% "baker-runtime" % "2.0.3"
libraryDependencies += "com.ing.baker" %% "baker-compiler" % "2.0.3"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.6"