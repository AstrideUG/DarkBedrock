import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType
import java.net.URI


plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "2.0.4"
//	id("de.sebastianboegl.shadow.transformer.log4j") version ("2.1.1")
//	id("checkstyle")
//	kotlin("jvm") version "1.3.10"
}

version = "1.0-SNAPSHOT"

extra["libsDirName"] = "libraries"
extra["libsDir"] = project.file(extra["libsDirName"].toString())

sourceSets {
    this["main"].java.srcDirs("src/main/kotlin")
    this["test"].java.srcDirs("src/test/kotlin")
}

repositories {
    maven { url = URI("https://hub.spigotmc.org/nexus/content/groups/public/") }
    maven { url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = URI("https://oss.sonatype.org/content/groups/public/") }
    maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { url = URI("http://repo.dmulloy2.net/content/groups/public/") }
}

dependencies {
    compileKotlin()
//    compile(project(":Darkness"))
    compile("org.spigotmc", "spigot-api", extra["versions.spigot"].toString())
    compile("net.md-5", "bungeecord-api", extra["versions.bungee"].toString())
    compile("net.md-5", "bungeecord-protocol", extra["versions.bungee"].toString())
    compile("org.mongodb", "mongodb-driver-async", extra["versions.mongodb"].toString())
    compile("com.google.inject", "guice", extra["versions.guice"].toString())
    compile()
}


tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf("Main-Class" to "net.darkdevelopers.darkbedrock.darkframe.general.BootstrapKt")
        )
    }
}

private val shadowJar: ShadowJar by tasks
shadowJar.apply {
    appendix = "with-dependencies"
    classifier = ""
    dependencies {
        include()
    }
}

//
//shadowJar {
////    classifier = "Hello"
////    appendix = "hi"
////    extension = "HAllo"
//    appendix = "with-dependencies"
//    classifier = ""
//    dependencies {
//        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
//        include(dependency("org.jetbrains.kotlin:kotlin-reflect"))
//        include(dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core"))
//        include(dependency("org.jetbrains:annotations:13.0"))
//        include(dependency(files("../Darkness/build/libs/Darkness-1.0-SNAPSHOT.jar")))
//        include(dependency(group: "org.mongodb", name: "mongodb-driver-async", version: "3.8.0"))
//        include(dependency(group: "org.mongodb", name: "mongodb-driver-core", version: "3.8.0"))
//        include(dependency(group: "org.mongodb", name: "bson", version: "3.8.0"))
//        include(dependency("com.google.inject:guice:4.0"))
//    }
//}
////TODO
//
////artifacts {
////    archives withApi
////    archives withDependencies
////}
////
////task withApi(type: Jar) {
////    appendix = "with-api"
////    classifier = ""
////    dependencies {
////        include(dependency(files("../Darkness/build/libs/Darkness-1.0-SNAPSHOT.jar")))
////    }
////}
////task withDependencies(type: Jar) {
////    appendix = "with-dependencies"
////    classifier = ""
////    dependencies {
////        from sourceSets.main.allSource
////        from fileTree(dir: "../Darkness/build/classes/", include: "*.class")
//////        from "../Darkness/build/classes/"
//////        into("org.jetbrains.kotlin:kotlin-stdlib")
//////        include("org.jetbrains.kotlin:kotlin-reflect:1.2.60")
//////        include("org.jetbrains.kotlin:kotlin-stdlib")
//////        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
//////        include(dependency("org.jetbrains.kotlin:kotlin-reflect:1.2.60"))
//////        include(dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.24.0"))
//////        include(dependency("org.jetbrains:annotations:13.0"))
//////        include(dependency(files("../Darkness/build/libs/Darkness-1.0-SNAPSHOT.jar")))
//////        include(dependency(group: "org.mongodb", name: "mongodb-driver-async", version: "3.8.0"))
//////        include(dependency(group: "org.mongodb", name: "mongodb-driver-core", version: "3.8.0"))
//////        include(dependency(group: "org.mongodb", name: "bson", version: "3.8.0"))
////    }
////}
////
////artifact withDependencies {
////    classifier "sources"
////}

fun DependencyHandlerScope.compileKotlin() = (extra["compileKotlin"] as Function1<DependencyHandlerScope, *>)(this)
