/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.fetcher.Fetcher
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.cancel
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.random.Random

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 13:59.
 * Current Version: 1.0 (22.12.2018 - 05.06.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:05.
 * Current Version: 1.0 (05.06.2019 - 05.06.2019)
 */
val <P : JavaPlugin> Class<P>.instance: JavaPlugin? get() = JavaPlugin.getPlugin(this)

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
    if (id != null) GameMode.getByValue(id) else GameMode.valueOf(toUpperCase())
} catch (ex: Exception) {
    null
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 05:08.
 *
 * @throws IndexOutOfBoundsException
 *
 * Current Version: 1.0 (15.02.2019 - 27.03.2019)
 */
fun String.toPlayerUUID(): UUID = try {
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

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 01:43.
 * Current Version: 1.0 (21.03.2019 - 30.03.2019)
 */
fun Location.randomLook(a: Int = 3, b: Int = 90): Location {
    val random = Random.nextInt(a + 1)
    val i = random * b
    val i1 = 180
    val yaw = if (i == i1) i else i % i1 * if (i / i1 > 0) -1 else 1
    return Location(world, x, y, z, yaw.toFloat(), pitch)
}


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:06.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
@Deprecated("", ReplaceWith("this.cancel()", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.cancel"))
fun Cancellable.cancel(): Unit = cancel(true)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:07.
 * Current Version: 1.0 (15.02.2019 - 15.02.2019)
 */
@Deprecated(
    "", ReplaceWith("this.cancel(value)", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.cancel")
)
fun Cancellable.cancel(value: Boolean) {
    isCancelled = value
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 15:12.
 * Current Version: 1.0 (29.05.2019 - 29.05.2019)
 */
inline fun <reified S> ServicesManager.register(
    provider: S,
    plugin: Plugin,
    priority: ServicePriority = ServicePriority.Normal
): Unit = this.register(S::class.java, provider, plugin, priority)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 15:24.
 * Current Version: 1.0 (29.05.2019 - 29.05.2019)
 */
inline fun <reified S> ServicesManager.provider(): S? = getRegistration(S::class.java)?.provider

