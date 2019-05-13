/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.manager.game

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.Location
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.Plugin

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 29.11.2017 14:06.
 * Last edit 13.05.2019
 */
object PreGameEventsTemplate : EventsTemplate() {

    fun setup(plugin: Plugin) {

        setupCancel()

        setJoinMessage { null }
        setDisconnectMessage { "${Messages.PREFIX}$IMPORTANT${it.player.displayName}$TEXT hat die Runde verlassen" } //TODO: ADD TEAM INFOS
        setDeathMessage { null }
        setKeepInventory { true }
        setChatFormat { "${it.player.displayName}$IMPORTANT: ${Colors.RESET}${it.message}" }

        listen<PlayerMoveEvent>(plugin) { event ->
            val from = event.from
            val to = event.to
            if (to.blockX == from.blockX && from.blockZ == to.blockZ) return@listen
            event.player.teleport(Location(from.world, from.x, from.y, from.z, to.yaw, to.pitch))
        }

    }

    override fun reset() {

        resetCancel()

        unregisterJoinMessage()
        unregisterDisconnectMessage()
        unregisterDeathMessage()
        unregisterKeepInventory()
        unregisterChatFormat()

        listener.unregister()
        listener.clear()

    }


}
