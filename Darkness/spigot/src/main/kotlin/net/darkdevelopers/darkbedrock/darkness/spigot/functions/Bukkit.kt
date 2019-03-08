package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.general.minecraft.fetcher.Fetcher
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import java.util.*

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
fun Iterable<String>.toWorlds() = mapNotNull {
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
    val id = toIntOrNull()
    @Suppress("DEPRECATION")
    if (id != null) org.bukkit.GameMode.getByValue(id) else org.bukkit.GameMode.valueOf(toUpperCase())
} catch (ex: Exception) {
    null
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 05:08.
 *
 * @throws IndexOutOfBoundsException
 *
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
fun String.toPlayerUUID() = try {
    UUID.fromString(this)
} catch (ex: Exception) {
    Bukkit.getPlayer(this)?.uniqueId ?: Fetcher.getUUID(this)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 05:09.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
fun UUID.toPlayer(): Player? = Bukkit.getPlayer(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 05:10.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
fun UUID.toOfflinePlayer(): OfflinePlayer? = Bukkit.getOfflinePlayer(this)

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

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.03.2019 17:17.
 *
 * @throws
 *
 * Current Version: 1.0 (08.03.2019 - 08.03.2019)
 */
fun String.toMaterial(): Material? = try {
    @Suppress("DEPRECATION")
    Material.getMaterial(toInt())
} catch (e: Exception) {
    Material.getMaterial(toUpperCase())
}


