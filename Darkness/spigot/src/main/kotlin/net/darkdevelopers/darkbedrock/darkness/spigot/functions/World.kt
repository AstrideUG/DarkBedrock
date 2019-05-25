/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2D
import org.bukkit.*

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:09.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:07.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun World.setup() {
    weatherDuration = -1
    time = 6000
    monsterSpawnLimit = 0
    difficulty = Difficulty.EASY
    keepSpawnInMemory = true
    isAutoSave = false
    setGameRuleValue("spawnRadius", "0")
    setGameRuleValue("doDaylightCycle", "false")
    setGameRuleValue("doMobSpawning", "false")
    setGameRuleValue("doFireTick", "false")
    for (x in -5..4) for (z in -5..4) loadChunk(x, z)
    entities.forEach { it.remove() }
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 22:38.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun World.setWorldBorder(
    size: Double,
    center: Vector2D,
    buffer: Double,
    amount: Double,
    distance: Int,
    time: Int
): Unit = worldBorder.setWorldBorder(size, center, buffer, amount, distance, time)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 22:39.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun World.setWorldBorder(
    size: Double,
    x: Double,
    z: Double,
    buffer: Double,
    amount: Double,
    distance: Int,
    time: Int
): Unit = worldBorder.setWorldBorder(size, x, z, buffer, amount, distance, time)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:15.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun WorldBorder.setWorldBorder(
    size: Double,
    center: Vector2D,
    buffer: Double,
    amount: Double,
    distance: Int,
    time: Int
): Unit = setWorldBorder(size, center.x, center.y, buffer, amount, distance, time)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:14.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun WorldBorder.setWorldBorder(
    size: Double,
    x: Double,
    z: Double,
    buffer: Double,
    amount: Double,
    distance: Int,
    time: Int
) {
    this.size = size
    this.setCenter(x, z)
    this.damageBuffer = buffer
    this.damageAmount = amount
    this.warningDistance = distance
    this.warningTime = time
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 22:29.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun String.toBukkitWorld(): World? = Bukkit.getWorld(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.05.2019 00:53.
 * Current Version: 1.0 (15.05.2019 - 15.05.2019)
 */
fun String.loadBukkitWorld(): World = Bukkit.getWorld(this) ?: Bukkit.createWorld(WorldCreator(this))
