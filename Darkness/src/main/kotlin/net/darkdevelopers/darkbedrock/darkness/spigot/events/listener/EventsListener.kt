/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events.listener

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Vector
import kotlin.concurrent.thread

/**
 * Created by LartyHD on 22.01.2018  00:14.
 * Last edit 06.07.2018
 */
class EventsListener private constructor(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        if (!autoRespawn) return
        thread {
            Thread.sleep(50)
            event.entity.spigot().respawn()
        }
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        val format = event.format ?: return
        if (format != "<%1\$s> %2\$s") event.format = format.replace("%", "%%")
    }

    @EventHandler
    fun onPlayerRespawnEvent(event: PlayerRespawnEvent) {
        event.player.velocity = Vector(0, 0, 0)
        event.player.fireTicks = 0
    }

    @EventHandler
    fun onPlayerQuitEvent(event: PlayerQuitEvent) {
        val playerDisconnectEvent = PlayerDisconnectEvent(event.player)
        Bukkit.getPluginManager().callEvent(playerDisconnectEvent)
        event.quitMessage = playerDisconnectEvent.leaveMessage
    }

    @EventHandler
    fun onPlayerKickEvent(event: PlayerKickEvent) {
        if (!event.isCancelled) {
            val playerDisconnectEvent = PlayerDisconnectEvent(event.player)
            Bukkit.getPluginManager().callEvent(playerDisconnectEvent)
            event.leaveMessage = playerDisconnectEvent.leaveMessage
        }
    }

    companion object {
        private var instance: EventsListener? = null
        var autoRespawn: Boolean = false

        fun getSimpleInstance(javaPlugin: JavaPlugin) = if (instance == null) EventsListener(javaPlugin) else instance
    }
}