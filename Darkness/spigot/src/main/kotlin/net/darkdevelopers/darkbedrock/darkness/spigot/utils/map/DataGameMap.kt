/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultEntityLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultLivingLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder.WorldBorder

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 19:41.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
data class DataGameMap(
    override val name: String,
    override val spawn: DefaultLivingLocation,
    override val hologram: DefaultEntityLocation? = null,
    override val region: Region? = null,
    override val worldBorder: WorldBorder? = null
) : GameMap