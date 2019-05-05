/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import org.bukkit.Location
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerRespawnEvent

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:56.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */

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
    "respawn".addEvent<PlayerRespawnEvent> { event -> event.respawnLocation = block(event) }
}

fun unregisterRespawn() {
    activeListener["respawn"]?.unregister()
}
