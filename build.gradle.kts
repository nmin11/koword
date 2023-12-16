plugins {
  kotlin("jvm") version "1.8.0"
  id("org.graalvm.buildtools.native") version "0.9.27"
  id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
  application
}

group = "loko"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.6")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
}

tasks.test {
  useJUnitPlatform()
}

application {
  mainClass.set("MainKt")
}

val appName = "koword"

graalvmNative {
  binaries {
    named("main") {
      imageName.set(appName)
      buildArgs("-R:MinHeapSize=500m")
      buildArgs("-R:MaxHeapSize=500m")
      resources.autodetect()
    }
  }
}
