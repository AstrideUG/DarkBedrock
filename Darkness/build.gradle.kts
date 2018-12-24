import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType

plugins {
    id("java")
    id("org.spongepowered.plugin") version "0.9.0"
    id("com.github.johnrengelman.shadow") version "2.0.4"
    kotlin("jvm")
}

dependencies {
    compileKotlin()
    compileTest()
//    compile(project(":Darkness"))
    compile("org.spigotmc", "spigot-api", extra["versions.spigot"].toString())
    compile("net.md-5", "bungeecord-api", extra["versions.bungee"].toString())
    compile("com.velocitypowered", "velocity-api", extra["versions.velocity"].toString())
    compile("org.mongodb", "mongodb-driver-async", extra["versions.mongodb"].toString())
    compile("com.google.inject", "guice", extra["versions.guice"].toString())
    compile("org.spongepowered:spongeapi:7.1.0")
}

fun DependencyHandlerScope.compileKotlin() = (extra["compileKotlin"] as Function1<DependencyHandlerScope, *>)(this)
fun DependencyHandlerScope.compileTest() = (extra["compileTest"] as Function1<DependencyHandlerScope, *>)(this)
//fun buildID() = (extra["buildID"] as Function0<*>)()