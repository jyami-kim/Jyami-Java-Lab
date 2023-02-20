import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.apache.thrift:libthrift:0.18.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")
    implementation("de.undercouch:bson4jackson:2.13.1")
    implementation("org.slf4j:slf4j-api:1.7.25")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


sourceSets {
    this.getByName("main"){
        this.java.srcDir("src/thrift/gen-java")
    }
}
