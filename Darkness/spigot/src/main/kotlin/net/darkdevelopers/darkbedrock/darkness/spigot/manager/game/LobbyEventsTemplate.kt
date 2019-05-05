/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.manager.game

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.*
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.isRight
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Items
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.Plugin

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 29.11.2017 14:06.
 *
 * Lobby & EndGame EventsTemplate
 *
 * Last edit 05.05.2019
 */
object LobbyEventsTemplate : EventsTemplate() {

    fun setup(plugin: Plugin, location: Location, kickMessage: String = "LEAVE") {

        setupCancel()

        location.setup()

        setJoinMessage { "${Messages.PREFIX}$IMPORTANT${it.player.displayName}$TEXT hat die Runde betreten" }
        setDisconnectMessage { "${Messages.PREFIX}$IMPORTANT${it.player.displayName}$TEXT hat die Runde verlassen" }
        setDeathMessage { null }
        setRespawn { location }
        setChatFormat { "${it.player.displayName}$IMPORTANT: $RESET${it.message}" }
        setKeepInventory { true }

        listen<PlayerJoinEvent>(plugin) { it.player.teleport(location) }.add()
        listen<PlayerMoveEvent>(plugin) { if (it.player.location.blockY < 0) it.player.teleport(location) }.add()
        listen<PlayerInteractEvent>(plugin) {
            if (!it.action.isRight()) return@listen
            if (it.item != Items.LEAVE.itemStack) return@listen
            it.player.kickPlayer(kickMessage)
        }.add()

    }

    fun reset() {

        resetCancel()

        unregisterRespawn()
        unregisterJoinMessage()
        unregisterDisconnectMessage()
        unregisterDeathMessage()
        unregisterChatFormat()
        unregisterKeepInventory()

        listener.unregister()
        listener.clear()

    }

    private fun Location.setup(): Unit = world.setup(this)
    private fun World.setup(location: Location) {
        setSpawnLocation(location.blockX, location.blockY, location.blockZ)
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
