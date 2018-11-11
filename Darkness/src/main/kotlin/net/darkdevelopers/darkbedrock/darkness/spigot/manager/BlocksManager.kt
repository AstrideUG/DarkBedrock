/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.manager

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import java.util.*

/**
 * Created by LartyHD on 06.07.2017 04:35.
 * Last edit 06.09.2018
 */
class BlocksManager(private val allowedTypes: Set<Material> = HashSet()) {

	private val locations: Set<Location> = HashSet()

	fun isBreakable(block: Block): Boolean = isBreakable(block.location)

	private fun isBreakable(location: Location): Boolean {
		location.block ?: return false
		allowedTypes.forEach { if (location.block.type == it) return true }
		return locations.any { location.world.name == it.world.name && location.blockX == it.blockX && location.blockY == it.blockY && location.blockZ == it.blockZ }
	}

}
