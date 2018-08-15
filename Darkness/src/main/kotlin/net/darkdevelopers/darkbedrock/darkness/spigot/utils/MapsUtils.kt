/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import java.io.File
import java.util.*
import kotlin.concurrent.thread

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 04.01.2018 20:31.
 * Last edit 14.07.2018
 */
object MapsUtils {
    fun loadMapNames(folder: File): List<String> = GsonConfig(ConfigData(folder, "data.json")).load().getAs<List<String>>("maps")?.apply { if (isEmpty()) throw IndexOutOfBoundsException("No maps in the Config") }
            ?: throw IndexOutOfBoundsException("No maps in the Config")

    fun getRandomMap(mapNames: List<String>): String = if (mapNames.isEmpty())
        throw IndexOutOfBoundsException("No maps in the List")
    else mapNames[Random().nextInt(mapNames.size)]

    fun loadMap(mapName: String) {
        var world: World? = Bukkit.getWorld(mapName)
        if (world == null) world = Bukkit.createWorld(WorldCreator(mapName))
        world ?: return
        world.apply {
            weatherDuration = -1
            time = 6000
            monsterSpawnLimit = 0
            difficulty = Difficulty.EASY
            keepSpawnInMemory = false
            isAutoSave = false
            setGameRuleValue("spawnRadius", "0")
            setGameRuleValue("doDaylightCycle", "false")
            setGameRuleValue("doMobSpawning", "false")
            setGameRuleValue("doFireTick", "false")
        }
        world.entities.forEach { it.remove() }
        fixBowBug(world)
    }

    fun equalsBlock(pos1: Location, pos2: Location) = pos1.world === pos2.world && pos1.blockX == pos2.blockX && pos1.blockY == pos2.blockY && pos1.blockZ == pos2.blockZ

    private fun fixBowBug(world: World) {
        val entity = world.spawnEntity(Location(world, 0.0, 100.0, 0.0), EntityType.VILLAGER) as LivingEntity
        entity.maxHealth = 1000.0
        entity.health = entity.maxHealth
        thread {
            try {
                Thread.sleep(1000)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
            entity.remove()
        }
    }
}
