/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.SaveTimeCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 24.06.2017 18:01.
 * Last edit 01.06.2019
 */
class SaveTimeCountdown(
    seconds: Int = 60,
    val players: Collection<Player> = Utils.players
) : Countdown(seconds) {

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
        loop {

            if (SaveTimeCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0) {
                "${Messages.PREFIX} ${TEXT}Die Schutzzeit ended".sendTo(players)
                stop()
            } else if (seconds in arrayOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60))
                "${Messages.PREFIX}${TEXT}Die Schutzzeit ended in ${if (seconds == 1) "${IMPORTANT}einer$TEXT Sekunde" else "$IMPORTANT$seconds$TEXT Sekunden"}"
                    .sendTo(players)

            seconds--

        }
    } else System.err.println("The savetime countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("savetime")

}
