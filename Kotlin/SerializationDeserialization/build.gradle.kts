plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.json:json:20140107")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}