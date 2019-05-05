/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.LobbyCountdown
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 13:34.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
class LobbyCountdownCallEvent(val lobbyCountdown: LobbyCountdown) : Event(), Cancellable {

    private var cancellable: Boolean = false

    override fun isCancelled(): Boolean = cancellable

    override fun setCancelled(cancel: Boolean) {
        cancellable = cancel
    }

    override fun getHandlers(): HandlerList = handlerList

    companion object {
        @JvmStatic //Important for Bukkit due to the Java ByteCode
        val handlerList: HandlerList = HandlerList()
    }

}
