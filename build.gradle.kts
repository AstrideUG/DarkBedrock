/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import jdk.nashorn.internal.objects.NativeFunction.function
import org.codehaus.groovy.control.io.FileReaderSource
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.resolve.scopes.utils.chainImportingScopes
import java.io.*
import java.nio.file.Files
import java.util.*
import java.net.URI

plugins {
    java
    kotlin("jvm") version "1.3.10"
}

subprojects {

    apply {
        plugin("java")
        plugin("kotlin")
    }

    group = "net.darkdevelopers.darkbedrock"
    version = "1.0-SNAPSHOT"

    extra["libsDirName"] = "libraries"
    extra["libsDir"] = project.file(extra["libsDirName"].toString())

    extra["KotlinDependencies"] = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib",
        "org.jetbrains.kotlin:kotlin-reflect",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1"
    )
    extra["TestDependencies"] = listOf(
        "org.jetbrains.kotlin:kotlin-test",
        "org.jetbrains.kotlin:kotlin-test-junit5",
        "org.mockito:mockito-all:2.0.2-beta",
        "org.junit.jupiter:junit-jupiter-api:5.3.1"
    )
    extra["compileKotlin"] = fun(p0: DependencyHandlerScope) {
        val dependencies = extra["KotlinDependencies"] as List<String>
        dependencies.forEach { p0.compile(it) }
    }
    extra["compileTest"] = fun(p0: DependencyHandlerScope) {
        val dependencies = extra["TestDependencies"] as List<String>
        dependencies.forEach { p0.testCompile(it) }
    }
//    extra["buildID"] = fun() = Properties().run {
//        val file = File(projectDir, "build.properties")
//        file.createNewFile()
//        load(FileInputStream(file))
//        val id = (getProperty("build")?.toIntOrNull() ?: 0) + 1
//        this["build"] = id.toString()
//        store(FileOutputStream(file), null)
//        id
//    }

    sourceSets["main"].java.srcDirs("src/main/kotlin")
    sourceSets["test"].java.srcDirs("src/test/kotlin")

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        google()
        maven { url = URI("https://hub.spigotmc.org/nexus/content/groups/public/") }
        maven { url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = URI("https://oss.sonatype.org/content/groups/public/") }
        maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = URI("http://repo.dmulloy2.net/content/groups/public/") }
    }

    dependencies {
        compile(fileTree(extra["libsDirName"].toString()))
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_6
        targetCompatibility = JavaVersion.VERSION_1_6
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.6"
        kotlinOptions.suppressWarnings = true
    }

}
