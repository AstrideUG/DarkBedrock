package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.setWorldBorder
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toBukkitWorld
import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.toLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toVector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import net.darkdevelopers.darkbedrock.darkness.spigot.region.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.region.toRegion
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.WorldBorder
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.toWorldBorder

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 19:37.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
interface GameMap {
    val name: String
    val spawn: Location
    val hologram: Location?
    val region: Region?
    val worldBorder: WorldBorder?
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toGameMap(
    defaultName: String = "GameMap",
    defaultSpawn: Location = DataLocation(defaultName, 0.0.toVector3D()),
    defaultHologram: Location? = null,
    defaultRegion: Region? = null,
    defaultWorldBorder: WorldBorder? = null
): GameMap {
    val world = this["world"]?.toString()
    return DataGameMap(
        this["name"]?.toString() ?: defaultName,
        (this["spawn"] as? Map<String, Any?>)?.toLocation(world = world) ?: defaultSpawn,
        (this["hologram"] as? Map<String, Any?>)?.toLocation(world = world) ?: defaultHologram,
        (this["region"] as? Map<String, Any?>)?.toRegion(defaultWorld = world) ?: defaultRegion,
        (this["worldborder"] as? Map<String, Any?>)?.toWorldBorder() ?: defaultWorldBorder
    )
}

fun GameMap.toMap(
    defaultName: String = "GameMap",
    defaultSpawn: Location = DataLocation(defaultName, 0.0.toVector3D()),
    defaultHologram: Location? = null,
    defaultRegion: Region? = null,
    defaultWorldBorder: WorldBorder? = null
): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    if (name != defaultName) this["name"] = this@toMap.name
    if (spawn != defaultSpawn) this["spawn"] = this@toMap.spawn.toMap()
    if (hologram != defaultHologram) this["hologram"] = this@toMap.hologram?.toMap() ?: return@apply
    if (region != defaultRegion) this["region"] = region?.toMap() ?: return@apply
    if (worldBorder != defaultWorldBorder) this["worldborder"] = worldBorder?.toMap() ?: return@apply
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 22:34.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun GameMap.setupWorldBorder() {
    val worldBorder = worldBorder ?: return
    val world = spawn.world.toBukkitWorld() ?: return
    world.setWorldBorder(
        worldBorder.size,
        worldBorder.center,
        worldBorder.damageBuffer,
        worldBorder.damageAmount,
        worldBorder.warningDistance,
        worldBorder.warningTime
    )
}
