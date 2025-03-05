plugins {
    java
    application
}

group = "gg.stephen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    mainClass.set("gg.stephen.vanisher.Main")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.3") {
        exclude(group = "club.minnced", module = "opus-java")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}