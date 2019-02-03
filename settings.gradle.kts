/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

rootProject.name = "DarkBedrock"
rootProject.buildFileName = "build.gradle"

val base = listOf("api", "common")

include("Darkness", withBase("spigot", "bungee"/*, "velocity", "sponge"*/) - "api" + "universal")
include("DarkFrame", withBase("spigot", "bungee", "velocity"/*, "sponge"*/) - "api")


//findProject(":DarkFrame:ClassModules:velocity")?.name = "moduleplugin-velocity"
//
//
//includeApi("AnnotatedCommands", withBase("bukkit", "bungee", "velocity"))
includeApi("Modules", base)
includeApi("Events", base)

fun withBase(vararg args: String) = base + args

fun includeApi(name: String, list: List<String>) = include("APIs:$name", list)

fun include(name: String, list: List<String>) = list.forEach {
    include(":$name:$it")
    findProject(":$name:$it")?.name = "${name.split(":").last()}-${it.capitalize()}"
}