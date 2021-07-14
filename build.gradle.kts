import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
}

group = "fuzuki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()

    // Kord snapshots.
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

@Suppress("SpellCheckingInspection")
dependencies {
    implementation("dev.kord:kord-core:0.7.x-SNAPSHOT")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    implementation("com.andreapivetta.kolor:kolor:1.0.0")

    // Seriously... Why Kord? Why? (http://www.slf4j.org/codes.html#StaticLoggerBinder, section's last paragraph)
    implementation("org.slf4j:slf4j-nop:1.7.30")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}