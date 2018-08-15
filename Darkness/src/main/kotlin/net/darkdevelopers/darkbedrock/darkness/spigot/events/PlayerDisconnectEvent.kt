/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

/**
 * Created by LartyHD on 29.11.2017 14:21.
 * Last edit 15.08.2018
 */
class PlayerDisconnectEvent(who: Player) : PlayerEvent(who) {
    var leaveMessage: String? = null

    override fun getHandlers(): HandlerList = handlerList


    companion object {
        @JvmStatic //Important for Bukkit due to the Java ByteCode
        val handlerList = HandlerList()
    }
}
