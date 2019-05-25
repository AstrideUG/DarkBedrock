/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.setWorldBorder
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toBukkitWorld
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultEntityLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultLivingLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.deserialization.toLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.deserialization.toLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.serialization.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.to.toLookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector3
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import net.darkdevelopers.darkbedrock.darkness.spigot.region.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.region.toRegion
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.WorldBorder
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.toWorldBorder

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 19:37.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
interface GameMap {
    val name: String
    val spawn: DefaultLivingLocation
    val hologram: DefaultEntityLocation?
    val region: Region?
    val worldBorder: WorldBorder?
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toGameMap(
    defaultName: String = "GameMap",
    defaultSpawn: DefaultLivingLocation = lookableLocationOf(defaultName, 0.0.toVector3(), 0f.toLookable()),
    defaultHologram: DefaultEntityLocation? = null,
    defaultRegion: Region? = null,
    defaultWorldBorder: WorldBorder? = null
): GameMap {
    val world = this["world"]?.toString()
    return DataGameMap(
        this["name"]?.toString() ?: defaultName,
        (this["spawn"] as? Map<String, Any?>)?.toLookableLocation(world = world) ?: defaultSpawn,
        (this["hologram"] as? Map<String, Any?>)?.toLocation(world = world) ?: defaultHologram,
        (this["region"] as? Map<String, Any?>)?.toRegion(defaultWorld = world) ?: defaultRegion,
        (this["worldborder"] as? Map<String, Any?>)?.toWorldBorder() ?: defaultWorldBorder
    )
}

fun GameMap.toMap(
    defaultName: String = "GameMap",
    defaultSpawn: DefaultLivingLocation = lookableLocationOf(defaultName, 0.0.toVector3(), 0f.toLookable()),
    defaultHologram: DefaultEntityLocation? = null,
    defaultRegion: Region? = null,
    defaultWorldBorder: WorldBorder? = null
): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    if (name != defaultName) this["name"] = this@toMap.name
    if (spawn != defaultSpawn) this["spawn"] = this@toMap.spawn.toMap(defaultSpawn)
    if (hologram != defaultHologram) this["hologram"] =
        this@toMap.hologram?.toMap(defaultHologram ?: lookableLocationOf(defaultName, 0.0.toVector3(), 0f.toLookable()))
            ?: return@apply
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
