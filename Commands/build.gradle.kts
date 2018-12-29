/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType

plugins {
    id("java")
    kotlin("jvm")
}

group = ""
version = "1.0"


dependencies {
    compileKotlin()
    compileTest()
    compile(project(":DarkFrame"))
    compile(project(":APIs:Modules:Modules-Api"))
    compile(project(":APIs:AnnotatedCommands:AnnotatedCommands-Api"))
    compile("org.spigotmc", "spigot-api", extra["versions.spigot"].toString())
    // https://mvnrepository.com/artifact/net.milkbowl.vault/VaultAPI
    compile("net.milkbowl.vault", "VaultAPI", "1.6")
}

fun DependencyHandlerScope.compileKotlin() = (extra["compileKotlin"] as Function1<DependencyHandlerScope, *>)(this)
fun DependencyHandlerScope.compileTest() = (extra["compileTest"] as Function1<DependencyHandlerScope, *>)(this)