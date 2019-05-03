/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.setToRespawn
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.unregisterRespawn
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.isRight
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Items
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by LartyHD on 29.11.2017  14:06.
 * Last edit 02.05.2019
 */
open class EndGameListener(
    javaPlugin: JavaPlugin,
    private val endGameLocation: Location
) : Listener(javaPlugin) {

    fun setup() {
        setupCancel()
        endGameLocation.setToRespawn()
    }

    fun restet() {
        resetCancel()
        unregisterRespawn()
    }

    @EventHandler
    open fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.teleport(endGameLocation)
        event.joinMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde betreten"
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
    }

    @EventHandler
    open fun onPlayerMove(event: PlayerMoveEvent) {
        if (event.player.location.blockY < 0) event.player.teleport(endGameLocation)
    }

    @EventHandler
    open fun onPlayerDeath(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    open fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (!event.action.isRight()) return
        if (event.item != Items.LEAVE.itemStack) return
        event.player.kickPlayer("LEAVE")
    }

}
