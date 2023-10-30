plugins {
    kotlin("jvm") version "1.9.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:1.6.1")
    implementation("io.github.microutils:kotlin-logging:1.6.22")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("no.tornado:tornadofx:1.7.18")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

application {
    mainClass.set("Main.kt")
    applicationDefaultJvmArgs = listOf("--add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED")
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml", "javafx.graphics")
}

