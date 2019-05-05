/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.SaveTimeCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.universal.functions.call
import org.bukkit.Bukkit

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 24.06.2017 18:01.
 * Last edit 05.05.2019
 */
class SaveTimeCountdown(seconds: Int = 60) : Countdown(seconds) {

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
//        Bukkit.getPluginManager().callEvent(SaveTimeCountdownStartedEvent(this))
        loop {

            if (SaveTimeCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0) finish()
            else if (seconds in arrayOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60))
                Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Die Schutzzeit ended in ${if (seconds == 1) "${IMPORTANT}einer$TEXT Sekunde" else "$IMPORTANT$seconds$TEXT Sekunden"}")

            seconds--

        }
    } else System.err.println("The savetime countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("savetime")

    override fun finish() {
//        Bukkit.getPluginManager().callEvent(SaveTimeCountdownFinishedEvent(this))
        Bukkit.broadcastMessage("${Messages.PREFIX} ${TEXT}Die Schutzzeit ended")
        stop()
    }

}
