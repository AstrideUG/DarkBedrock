/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

rootProject.name = "DarkBedrock"
rootProject.buildFileName = "build.gradle"

private val api = "api"
private val common = "common"
private val spigot = "spigot"
//private val sponge = "sponge"
private val bungee = "bungee"
private val velocity = "velocity"

includeProject("Darkness", listOf(common, spigot, bungee, "universal"))
includeProject("DarkFrame", listOf(common, spigot, bungee, velocity))
//includeProject("Commands", listOf(spigot))

//findProject(":DarkFrame:ClassModules:velocity")?.name = "moduleplugin-velocity"
//
//includeProjectApi("AnnotatedCommands", withBase("bukkit", "bungee", "velocity"))
includeProjectApi("Modules", listOf(api, common))
includeProjectApi("Events", listOf(api, common))

fun includeProjectApi(name: String, list: List<String>) = includeProject("APIs:$name", list)

fun includeProject(name: String, list: List<String>) = list.forEach {
    include(":$name:$it")
    findProject(":$name:$it")?.name = "${name.split(":").last()}-${it.capitalize()}"
}