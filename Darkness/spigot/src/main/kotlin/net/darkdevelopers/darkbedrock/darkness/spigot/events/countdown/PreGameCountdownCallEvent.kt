/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.PreGameCountdown
import net.darkdevelopers.darkbedrock.darkness.spigot.events.KCancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Created on 05.05.2019 13:35.
 * @author Lars Artmann | LartyHD
 */
class PreGameCountdownCallEvent(@Suppress("unused") val preGameCountdown: PreGameCountdown) : Event(), KCancellable {

    override var cancellable: Boolean = false

    override fun getHandlers(): HandlerList = handlerList

    companion object {
        @JvmStatic //Important for Bukkit due to the Java ByteCode
        val handlerList: HandlerList = HandlerList()
    }

}
