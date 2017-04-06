name := "monadic-shell"

version := "1.0"

scalaVersion := "2.11.8"

enablePlugins(ScalaNativePlugin)


val scalazVersion = "7.3.0-M10"

libraryDependencies += "org.scalaz" %%% "scalaz-core" % scalazVersion
libraryDependencies += "org.scalaz" %%% "scalaz-effect" % scalazVersion
libraryDependencies += "org.scalaz" %%% "scalaz-iteratee" % scalazVersion