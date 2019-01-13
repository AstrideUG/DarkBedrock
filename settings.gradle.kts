/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

rootProject.name = "DarkBedrock"
rootProject.buildFileName = "build.gradle.kts"

val min = listOf("api", "common")

include(
    "Darkness",
    "DarkFrame",
    ":Commands",
    "DarkFrame:ClassModules",
    "DarkFrame:ClassModules:velocity"
//    "APIs",
//    "APIs:AnnotatedCommands",
//    "APIs:AnnotatedCommands:api",
//    "APIs:AnnotatedCommands:bukkit",
//    "APIs:AnnotatedCommands:bungee",
//    "APIs:AnnotatedCommands:common",
//    "APIs:AnnotatedCommands:velocity"
//    "APIs:Events",
//    "APIs:Modules"/**/
)


includeApi("AnnotatedCommands", minPlus("bukkit", "bungee", "velocity"))
includeApi("Modules", min)
includeApi("Events", min)

findProject(":DarkFrame:ClassModules:velocity")?.name = "moduleplugin-velocity"

enableFeaturePreview("STABLE_PUBLISHING") //Copied by Velocity

fun minPlus(vararg args: String) = mutableListOf<String>().apply {
    addAll(listOf("api", "common"))
    addAll(args)
}

fun includeApi(name: String, list: List<String>) = list.forEach {
    include(":APIs:$name:$it")
    findProject(":APIs:$name:$it")?.name = "$name-${it.capitalize()}"
}