import javaslang.Function0
import javaslang.Function1
import jdk.nashorn.internal.objects.NativeFunction.function
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.resolve.scopes.utils.chainImportingScopes

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

plugins {
    id("java")
    kotlin("jvm") version "1.3.10"
}

java.sourceCompatibility = JavaVersion.VERSION_1_6
java.targetCompatibility = JavaVersion.VERSION_1_6

extra["KotlinDependencies"] = listOf(
    "org.jetbrains.kotlin:kotlin-stdlib",
    "org.jetbrains.kotlin:kotlin-reflect",
    "org.jetbrains:annotations",
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
    val dependencies = extra["KotlinDependencies"] as List<String>
    dependencies.forEach { p0.testCompile(it) }
}

allprojects {
    group = "net.darkdevelopers.darkbedrock"

    extra["KotlinDependencies"] = rootProject.extra["KotlinDependencies"]
    extra["TestDependencies"] = rootProject.extra["TestDependencies"]
    extra["compileKotlin"] = rootProject.extra["compileKotlin"]
    extra["compileTest"] = rootProject.extra["compileTest"]

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        google()
    }

}

private val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.6"
    suppressWarnings = true
    allWarningsAsErrors = true
}

private val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.6"
}