/*
 * © Copyright - MineWar.net | Lars Artmann aka. LartyHD 2017
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.region

import org.bukkit.Location
import org.bukkit.World

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 07.08.2017 03:10.
 * Last edit 09.07.2018
 */
class Region(pos1: Location, pos2: Location) {
    private val min: Location
    private val max: Location

    init {
        val world = pos1.world ?: throw NullPointerException("world can not be null")
        if (world != pos2.world) throw IllegalArgumentException("pos1 world and pos2 world must be the same")
        min = getMinLocation(world, pos1, pos2)
        max = getMaxLocation(world, pos1, pos2)
    }

    fun isInside(location: Location): Boolean = location.world == min.world &&
            location.x >= min.x &&
            location.x <= max.x &&
            location.y >= min.y &&
            location.y <= max.y &&
            location.z >= min.z &&
            location.z <= max.z

    private fun getMinLocation(world: World, pos1: Location, pos2: Location): Location {
        val minX = if (pos1.x < pos2.x) pos1.x else pos2.x
        val minY = if (pos1.y < pos2.y) pos1.y else pos2.y
        val minZ = if (pos1.z < pos2.z) pos1.z else pos2.z
        return Location(world, minX, minY, minZ)
    }

    private fun getMaxLocation(world: World, pos1: Location, pos2: Location): Location {
        val maxX = if (pos1.x > pos2.x) pos1.x else pos2.x
        val maxY = if (pos1.y > pos2.y) pos1.y else pos2.y
        val maxZ = if (pos1.z > pos2.z) pos1.z else pos2.z
        return Location(world, maxX, maxY, maxZ)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Region

        if (min != other.min) return false
        if (max != other.max) return false

        return true
    }

    override fun hashCode(): Int {
        var result = min.hashCode()
        result = 31 * result + max.hashCode()
        return result
    }

    override fun toString(): String {
        return "Region(min=$min, max=$max)"
    }
}
