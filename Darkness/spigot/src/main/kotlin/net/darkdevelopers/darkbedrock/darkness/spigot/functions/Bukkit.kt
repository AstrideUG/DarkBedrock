package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.Cancellable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 13:59.
 * Current Version: 1.0 (22.12.2018 - 15.02.2019)
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
fun String.toGameMode(): GameMode? = try {
    val id = this.toIntOrNull()
    @Suppress("DEPRECATION")
    if (id != null) org.bukkit.GameMode.getByValue(id) else org.bukkit.GameMode.valueOf(this.toUpperCase())
} catch (ex: Exception) {
    null
}


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:06.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
fun Cancellable.cancel(): Unit = cancel(true)


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:07.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
fun Cancellable.cancel(value: Boolean) {
    isCancelled = value
}


