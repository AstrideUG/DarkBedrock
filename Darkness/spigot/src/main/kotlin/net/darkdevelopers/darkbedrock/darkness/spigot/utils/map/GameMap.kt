package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import net.darkdevelopers.darkbedrock.darkness.spigot.region.toMap

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
}

fun Map<String, Any?>.toGameMap(
    world: String? = null,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    yaw: Float = 0f,
    pitch: Float = 0f
): Location = DataLocation(
    this["world"]?.toString() ?: world.toString(),
    this["x"].toString().toDoubleOrNull() ?: x,
    this["y"].toString().toDoubleOrNull() ?: y,
    this["z"].toString().toDoubleOrNull() ?: z,
    this["yaw"].toString().toFloatOrNull() ?: yaw,
    this["pitch"].toString().toFloatOrNull() ?: pitch
)

fun GameMap.toMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    this["name"] = this@toMap.name
    this["spawn"] = this@toMap.spawn.toMap()
    if (hologram != null) this["hologram"] = this@toMap.hologram?.toMap()
    val region = region
    if (region != null) this["region"] = region.toMap()
}
