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
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 29.11.2017 14:06.
 * Last edit 02.05.2019
 */
open class LobbyListener protected constructor(
    javaPlugin: JavaPlugin,
    private val lobbyLocation: Location
) : Listener(javaPlugin) {

    init {

        lobbyLocation.world.apply {
            setSpawnLocation(lobbyLocation.blockX, lobbyLocation.blockY, lobbyLocation.blockZ)
            time = 6000
            setGameRuleValue("spawnRadius", "0")
            setGameRuleValue("doDaylightCycle", "false")
            setGameRuleValue("doMobSpawning", "false")
            setGameRuleValue("doFireTick", "false")
            weatherDuration = -1
            isThundering = false
            setStorm(false)
            isAutoSave = false
//            difficulty = Difficulty.PEACEFUL
        }

    }

    fun setup() {
        setupCancel()
        lobbyLocation.setToRespawn()
    }

    fun restet() {
        restetCancel()
        unregisterRespawn()
    }

    @EventHandler
    open fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        player.teleport(lobbyLocation)
        event.joinMessage = "${Messages.PREFIX}$IMPORTANT${player.displayName}$TEXT hat die Runde betreten"
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
    }

    @EventHandler
    open fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        if (event.player.location.blockY < 0) event.player.teleport(lobbyLocation)
    }

    @EventHandler
    open fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (!event.action.isRight()) return
        if (event.item != Items.LEAVE.itemStack) return
        event.player.kickPlayer("LEAVE")
    }

    @EventHandler
    open fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

}