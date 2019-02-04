package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Bukkit

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 13:59.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 13:59.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
fun Iterable<String>.toWorlds() = this.mapNotNull {
    try {
        Bukkit.getWorld(it)
    } catch (ex: Exception) {
        null
    }
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 14:47.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
fun String.toGameMode() = try {
    val id = this.toIntOrNull()
    if (id != null) org.bukkit.GameMode.getByValue(id) else org.bukkit.GameMode.valueOf(this.toUpperCase())
} catch (ex: Exception) {
    null
}
