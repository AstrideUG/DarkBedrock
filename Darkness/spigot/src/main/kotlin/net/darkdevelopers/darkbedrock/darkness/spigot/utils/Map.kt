/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.08.2018 20:49.
 * Last edit 11.05.2019
 */
@Deprecated("Use GameMap")
data class Map(
    val name: String,
    val spawn: Location,
    val hologram: Location,
    val region: Region,
    @Suppress("DEPRECATION")
    val sendHolograms: (Player, MutableMap<UUID, Holograms>, map: Map) -> Unit
) {

    private val holograms = mutableMapOf<UUID, Holograms>()

    fun sendHologram(player: Player) {
        removeHologram(player)
        sendHolograms(player, holograms, this)
    }

    fun removeHologram(player: Player) {
        holograms[player.uniqueId]?.hide(player)
        holograms.remove(player.uniqueId)
    }

}