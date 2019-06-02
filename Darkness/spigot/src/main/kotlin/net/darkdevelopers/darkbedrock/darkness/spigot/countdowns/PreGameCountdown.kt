/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.PreGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTimings
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTitle
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players
import net.darkdevelopers.darkbedrock.darkness.universal.functions.call
import org.bukkit.Sound

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 24.06.2017 18:01.
 * Last edit 01.06.2019
 */
class PreGameCountdown(seconds: Int = 5) : Countdown(seconds) {

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
        loop {

            if (PreGameCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0) {
                "${Messages.PREFIX}${TEXT}Die Runde startet".sendTo(players)
                stop()
            } else if (seconds in arrayOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60))
                "${Messages.PREFIX}${TEXT}Die Runde startet in ${if (seconds == 1) "${IMPORTANT}einer$TEXT Sekunde" else "$IMPORTANT$seconds$TEXT Sekunden"}"
                    .sendTo(players)


            if (seconds == 10 || seconds in 1..5) players.forEach {
                it.sendTitle("$SECONDARY$seconds")
                it.sendTimings(1, 18, 1)
                it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
            }

            setLevel()
            seconds--

        }
    } else System.err.println("The pregame countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("pregame")

}
