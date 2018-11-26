/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

rootProject.name = "DarkBedrock"

include(
    "Darkness",
    "DarkFrame",
//    "APIs",
//    "APIs:AnnotatedCommands",
    "APIs:AnnotatedCommands:api",
    "APIs:AnnotatedCommands:bukkit",
    "APIs:AnnotatedCommands:bungee",
    "APIs:AnnotatedCommands:common",
    "APIs:AnnotatedCommands:velocity"
//    "APIs:Events",
//    "APIs:Modules"/**/
)

enableFeaturePreview("STABLE_PUBLISHING") //Copied by Velocity