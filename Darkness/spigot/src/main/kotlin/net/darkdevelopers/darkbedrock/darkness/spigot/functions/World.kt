package net.darkdevelopers.darkbedrock.darkness.spigot.functions

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