/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType

plugins {
    java
    kotlin("jvm")
}

dependencies {
    compileKotlin()
    compileTest()
    compile("org.apache.logging.log4j", "log4j-slf4j-impl", "2.1")
}

fun DependencyHandlerScope.compileKotlin() = (extra["compileKotlin"] as Function1<DependencyHandlerScope, *>)(this)
fun DependencyHandlerScope.compileTest() = (extra["compileTest"] as Function1<DependencyHandlerScope, *>)(this)
