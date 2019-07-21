/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.schedule
import org.bukkit.Location
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.util.Vector

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:56.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */

var autoRespawn: Boolean = false
    set(value) {
        field = value
        ::autoRespawn.name.updateEvent<PlayerDeathEvent>(
            value, priority0 = EventPriority.LOW, function = { event ->
                plugin.schedule(delay = 2) { event.entity.spigot().respawn() }
            }
        )
    }
var fixChatFormat: Boolean = false
    set(value) {
        field = value
        ::fixChatFormat.name.updateEvent<AsyncPlayerChatEvent>(
            value, function = { event ->
                event.format = event.format.replace("%", "%%")
            }
        )
    }
var resetVelocityAfterRespawn: Boolean = false
    set(value) {
        field = value
        ::resetVelocityAfterRespawn.name.updateEvent<PlayerRespawnEvent>(
            value, function = { event ->
                event.player.velocity = Vector(0, 0, 0)
            }
        )
    }
var resetFireTicksAfterRespawn: Boolean = false
    set(value) {
        field = value
        ::resetFireTicksAfterRespawn.name.updateEvent<PlayerRespawnEvent>(
            value, function = { event ->
                event.player.fireTicks = 0
            }
        )
    }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:56.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setChatFormat(block: (AsyncPlayerChatEvent) -> String?) {
    unregisterChatFormat()
    "chat-format".addEvent<AsyncPlayerChatEvent> { event -> event.format = block(event) }
}

fun unregisterChatFormat() {
    activeListener["chat-format"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:59.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setKeepInventory(block: (PlayerDeathEvent) -> Boolean) {
    unregisterChatFormat()
    "keep-inventory".addEvent<PlayerDeathEvent> { event -> event.keepInventory = block(event) }
}

fun unregisterKeepInventory() {
    activeListener["keep-inventory"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:40.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setRespawn(block: (PlayerRespawnEvent) -> Location) {
    unregisterRespawn()
    "respawn".addEvent<PlayerRespawnEvent> { event ->
        val location = block(event)
        if (location.world == null) {
            unregisterRespawn()
            System.err.println("Respawn location world can not be null")
            return@addEvent
        }
        event.respawnLocation = location
    }
}

fun unregisterRespawn() {
    activeListener["respawn"]?.unregister()
}
