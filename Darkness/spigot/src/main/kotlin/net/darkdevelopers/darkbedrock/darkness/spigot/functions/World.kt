package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector2D
import org.bukkit.Difficulty
import org.bukkit.World

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
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:15.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun World.setWorldBorder(
    size: Double,
    center: Vector2D,
    buffer: Double,
    amount: Double,
    distance: Int,
    time: Int
) = setWorldBorder(size, center.x, center.z, buffer, amount, distance, time)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:14.
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
) {
    worldBorder.size = size
    worldBorder.setCenter(x, z)
    worldBorder.damageBuffer = buffer
    worldBorder.damageAmount = amount
    worldBorder.warningDistance = distance
    worldBorder.warningTime = time
}

