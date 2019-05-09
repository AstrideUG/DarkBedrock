package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.WorldBorder

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 19:41.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
data class DataGameMap(
    override val name: String,
    override val spawn: Location,
    override val hologram: Location?,
    override val region: Region?,
    override val worldBorder: WorldBorder?
) : GameMap