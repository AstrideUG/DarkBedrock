package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 04:55.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 04:56.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
fun String.toPlayer(): Player? = try {
    Bukkit.getPlayer(UUID.fromString(this))
} catch (ex: IllegalArgumentException) {
    Bukkit.getPlayer(this)
}