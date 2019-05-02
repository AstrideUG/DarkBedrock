/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import org.bukkit.Location
import org.bukkit.event.player.PlayerRespawnEvent

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 11:43.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 12:02.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun Location.setToRespawn() {
    unregisterRespawn()
    "respawn".addEvent<PlayerRespawnEvent> { _, event ->
        if (event !is PlayerRespawnEvent) return@addEvent
        event.respawnLocation = this
    }
}

fun unregisterRespawn() {
    activeListener["respawn"]?.unregister()
}
