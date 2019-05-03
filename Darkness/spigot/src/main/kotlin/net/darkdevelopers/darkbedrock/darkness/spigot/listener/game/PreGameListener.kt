/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by LartyHD on 29.11.2017  14:06.
 * Last edit 02.05.2019
 */
open class PreGameListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    fun setup(): Unit = setupCancel()
    fun restet(): Unit = resetCancel()

    @EventHandler
    open fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        event.joinMessage = null
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
        //TODO: ADD TEAM INFOS
    }

    @EventHandler
    open fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        val from = event.from
        val to = event.to
        if (to.blockX == from.blockX && from.blockZ == to.blockZ) return
        event.player.teleport(Location(from.world, from.x, from.y, from.z, to.yaw, to.pitch))
    }

    @EventHandler
    open fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

}
