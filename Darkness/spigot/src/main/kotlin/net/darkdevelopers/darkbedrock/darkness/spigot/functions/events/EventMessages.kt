/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import org.bukkit.Location
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 11:43.
 * Current Version: 1.0 (02.05.2019 - 05.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 12:02.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
@Deprecated(
    "Can be replaced with setRespawn { this }",
    ReplaceWith("setRespawn { this }", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.setRespawn")
)
fun Location.setToRespawn() {
    unregisterRespawn()
    "respawn".addEvent<PlayerRespawnEvent> { event -> event.respawnLocation = this }
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:41.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setJoinMessage(block: (PlayerJoinEvent) -> String?) {
    unregisterJoinMessage()
    "join-message".addEvent<PlayerJoinEvent> { event -> event.joinMessage = block(event) }
}

fun unregisterJoinMessage() {
    activeListener["join-message"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:48.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setQuitMessage(block: (PlayerQuitEvent) -> String?) {
    unregisterQuitMessage()
    "quit-message".addEvent<PlayerQuitEvent> { event -> event.quitMessage = block(event) }
}

fun unregisterQuitMessage() {
    activeListener["quit-message"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:48.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setKickMessage(block: (PlayerKickEvent) -> String?) {
    unregisterKickMessage()
    "kick-message".addEvent<PlayerKickEvent> { event -> event.leaveMessage = block(event) }
}

fun unregisterKickMessage() {
    activeListener["kick-message"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:49.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setDisconnectMessage(block: (PlayerDisconnectEvent) -> String?) {
    unregisterDisconnectMessage()
    unregisterQuitMessage()
    unregisterKickMessage()
    "disconnect-message".addEvent<PlayerDisconnectEvent> { event -> event.leaveMessage = block(event) }
}

fun unregisterDisconnectMessage() {
    activeListener["disconnect-message"]?.unregister()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 08:53.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun setDeathMessage(block: (PlayerDeathEvent) -> String?) {
    unregisterDeathMessage()
    "death-message".addEvent<PlayerDeathEvent> { event -> event.deathMessage = block(event) }
}

fun unregisterDeathMessage() {
    activeListener["death-message"]?.unregister()
}
