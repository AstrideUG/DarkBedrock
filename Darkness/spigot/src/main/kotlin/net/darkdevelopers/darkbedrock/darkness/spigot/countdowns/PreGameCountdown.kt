/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.PreGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTimings
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTitle
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.SECONDARY
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players
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

            if (seconds == 0 || players.isEmpty()) {
                messages.preGameCountdownStartRound.sendTo(players)
                stop()
            } else if (seconds in messages.preGameCountdownStartRoundInIfIn) messages.preGameCountdownStartRoundIn
                .replace("@seconds@", seconds.toString(), true)
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
