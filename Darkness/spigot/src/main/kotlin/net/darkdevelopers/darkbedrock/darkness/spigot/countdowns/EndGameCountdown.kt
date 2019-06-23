/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.EndGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.plugin
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.entity.Player

/**
 * Created on 24.06.2017 17:22.
 * @author Lars Artmann | LartyHD
 */
class EndGameCountdown(
    seconds: Int = 15,
    val players: Collection<Player> = Utils.players
) : Countdown(seconds) {

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
        loop {

            if (EndGameCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0 || players.isEmpty()) {
                configService.endGameCountdownRestartsServer.sendTo(players)
                plugin.server.shutdown()
                stop()
            } else if (seconds in configService.endGameCountdownRestartsServerInIfIn) configService.endGameCountdownRestartsServerIn
                .replace("@seconds@", seconds.toString(), true)
                .sendTo(players)
            setLevel()
            seconds--

        }
    } else System.err.println("The endgame countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("endgame")

}
