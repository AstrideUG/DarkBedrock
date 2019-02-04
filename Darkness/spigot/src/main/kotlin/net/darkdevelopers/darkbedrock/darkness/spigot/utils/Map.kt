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
 * Last edit 15.08.2018
 */
data class Map(
    val name: String,
    val spawn: Location,
    val hologram: Location,
    val region: Region,
    val sendHolograms: (Player, MutableMap<UUID, Holograms>) -> Unit
) {

    private val holograms = mutableMapOf<UUID, Holograms>()

    fun sendHologram(player: Player) {
        removeHologram(player)
        sendHolograms(player, holograms)
//        KnockIT.instance.stats.get(player.uniqueId, "kills") { kills ->
//            KnockIT.instance.stats.get(player.uniqueId, "deaths") { deaths ->
//                holograms[player.uniqueId] = Holograms(arrayOf(
//                        "§7-= ${ChatColor.BLUE}KnockIT Stats §7=-",
//                        "",
//                        "§7Dein Platz§8: §cLoading...", //TODO: ADD RANG
//                        "§7Kills§8: $kills",
//                        "§7Tode§8: $deaths",
//                        "§7K/D§8: ${(kills.toDouble() / deaths.toDouble()).format(2)}"
//                ), hologram)
//                holograms[player.uniqueId]?.show(player)
//            }
//        }
    }

    private fun removeHologram(player: Player) {
        holograms[player.uniqueId]?.hide(player)
        holograms.remove(player.uniqueId)
    }

}