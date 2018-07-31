/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.manager

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import java.util.*

/**
 * Created by LartyHD on 06.07.2017 04:35.
 * Last edit 06.07.2018
 */
class BlocksManager(private val allowedTypes: Set<Material> = HashSet<Material>()) {
    private val locations: Set<Location> = HashSet()

    fun isBreakable(block: Block): Boolean = isBreakable(block.location)

    private fun isBreakable(location: Location): Boolean {
        location.block ?: return false
        for (types in allowedTypes) if (location.block.type == types) return true
        for (locations in locations)
            if (location.world.name == locations.world.name &&
                    location.blockX == locations.blockX &&
                    location.blockY == locations.blockY &&
                    location.blockZ == locations.blockZ) return true
        return false
    }
}
