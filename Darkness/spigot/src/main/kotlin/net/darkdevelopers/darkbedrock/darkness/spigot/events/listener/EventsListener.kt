/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events.listener

import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.universal.functions.call
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

/**
 * Created by LartyHD on 22.01.2018 00:14.
 * Last edit 06.09.2018
 */
class EventsListener private constructor(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    companion object {
        //        private val handlers = HandlerList()
        private lateinit var instance: EventsListener
        /**
         * @author Lars Artmann | LartyHD
         * If it is true: Will the player, 50 milliseconds, sent a respawn packet after his death
         */
        var autoRespawn: Boolean = false
//        var debug: Boolean = false

        fun getSimpleInstance(javaPlugin: JavaPlugin): EventsListener {
            if (!::instance.isInitialized) instance = EventsListener(javaPlugin)
            return instance
        }

    }

    @EventHandler(priority = EventPriority.LOW)
    fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        if (!autoRespawn) return
        Bukkit.getScheduler().runTaskLater(javaPlugin, { event.entity.spigot().respawn() }, 1)
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        val format = event.format.toNonNull()
//        if (format != "<%1\$s> %2\$s") event.format = format.replace("%", "%%")
        if ('%' !in format.replace(event.message, "")) event.format = format.replace("%", "%%")
    }

    @EventHandler
    fun onPlayerRespawnEvent(event: PlayerRespawnEvent) {
        event.player.velocity = Vector(0, 0, 0)
        event.player.fireTicks = 0
    }

    @EventHandler
    fun onPlayerQuitEvent(event: PlayerQuitEvent) {
        event.quitMessage = PlayerDisconnectEvent(event.player, event.quitMessage).call().leaveMessage
    }

    @EventHandler
    fun onPlayerKickEvent(event: PlayerKickEvent) {
        if (!event.isCancelled) event.leaveMessage =
            PlayerDisconnectEvent(event.player, event.leaveMessage).call().leaveMessage
    }

}