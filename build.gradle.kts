plugins {
    kotlin("jvm") version "1.9.25"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.lfester"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.minecraftforge.net") {
        metadataSources { artifact() }
    }
}

dependencies {
    compileOnly("net.minecraftforge:forge:1.7.10-10.13.4.1614-1.7.10:universal") {
        isTransitive = false
    }
    testImplementation(kotlin("test"))
    testImplementation("com.google.guava:guava:17.0")
    testImplementation("net.minecraftforge:forge:1.7.10-10.13.4.1614-1.7.10:universal") {
        isTransitive = false
    }
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
}

tasks.shadowJar {
    archiveClassifier.set("")
    relocate("kotlin", "com.lfester.fastbreak.internal.kotlin")
    exclude("META-INF/*.SF", "META-INF/*.RSA", "META-INF/*.DSA")
}

tasks.jar {
    archiveClassifier.set("plain")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
