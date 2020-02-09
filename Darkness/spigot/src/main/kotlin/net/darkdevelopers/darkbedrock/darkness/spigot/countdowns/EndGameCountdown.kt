/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.EndGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.plugin
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import org.bukkit.entity.Player
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

class EndGameCountdown(
    seconds: Int = 15,
    private val inputPlayers: () -> Collection<Player> = { allPlayers }
) : Countdown(seconds) {

    val players get() = inputPlayers()

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
        loop {

            if (EndGameCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0 || players.isEmpty()) {
                messages.endGameCountdownRestartsServer.sendTo(players)
                plugin.server.shutdown()
                stop()
            } else if (seconds in messages.endGameCountdownRestartsServerInIfIn) messages.endGameCountdownRestartsServerIn
                .replace("@seconds@", seconds.toString(), true)
                .sendTo(players)
            setLevel()
            seconds--

        }
    } else System.err.println("The endgame countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("endgame")

}
