plugins {
    application
    kotlin("jvm") version "2.1.20-Beta1"
    kotlin("plugin.serialization") version "2.1.10"
    id("com.gradleup.shadow") version "latest.release"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.3") {
        exclude(group = "club.minnced", module = "opus-java")
    }
}

tasks {
    compileJava { options.release.set(17) }
    jar {
        manifest { attributes["Main-Class"] = application.mainClass }
    }
    build { dependsOn(shadowJar) }
//    shadowJar {
//        archiveFileName.set("bot.jar") // remove this for native file naming
//    }
}

application { mainClass.set("gg.stephen.vanisher.Main") }