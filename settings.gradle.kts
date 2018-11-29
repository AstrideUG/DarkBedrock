/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

rootProject.name = "DarkBedrock"

val platforms = listOf("api", "bukkit", "bungee", "common", "velocity")

include(
    "Darkness",
    "DarkFrame",
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

platforms.forEach {
    include(":APIs:AnnotatedCommands:$it")
    findProject(":APIs:AnnotatedCommands:$it")?.name = "annotatedcommands-$it"
}

findProject(":DarkFrame:ClassModules:velocity")?.name = "moduleplugin-velocity"

enableFeaturePreview("STABLE_PUBLISHING") //Copied by Velocity