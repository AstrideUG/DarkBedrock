import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "2.0.4"
    kotlin("jvm")
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
    compileTest()
//    compile(project(":Darkness"))
    compile("org.spigotmc", "spigot-api", extra["versions.spigot"].toString())
    compile("net.md-5", "bungeecord-api", extra["versions.bungee"].toString())
    compile("net.md-5", "bungeecord-protocol", extra["versions.bungee"].toString())
    compile("org.mongodb", "mongodb-driver-async", extra["versions.mongodb"].toString())
    compile("com.google.inject", "guice", extra["versions.guice"].toString())
}

fun DependencyHandlerScope.compileKotlin() = (extra["compileKotlin"] as Function1<DependencyHandlerScope, *>)(this)
fun DependencyHandlerScope.compileTest() = (extra["compileTest"] as Function1<DependencyHandlerScope, *>)(this)
//fun buildID() = (extra["buildID"] as Function0<*>)()