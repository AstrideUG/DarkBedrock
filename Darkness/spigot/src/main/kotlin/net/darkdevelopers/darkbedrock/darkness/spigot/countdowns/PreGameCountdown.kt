/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.PreGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTimings
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTitle
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.SECONDARY
import org.bukkit.Sound
import org.bukkit.entity.Player
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

class PreGameCountdown(
    seconds: Int = 5,
    private val inputPlayers: () -> Collection<Player> = { allPlayers }
) : Countdown(seconds) {

    val players get() = inputPlayers()

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
