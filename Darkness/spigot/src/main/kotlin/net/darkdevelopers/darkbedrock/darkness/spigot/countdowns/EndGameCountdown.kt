/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.EndGameCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.darkdevelopers.darkbedrock.darkness.universal.functions.call
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 24.06.2017 17:22.
 * Last edit 05.05.2019
 */
class EndGameCountdown(
    seconds: Int = 15,
    val players: Collection<Player> = Utils.players
) : Countdown(seconds) {

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
//            Bukkit.getPluginManager().callEvent(EndGameCountdownStartedEvent(this))
        loop {

            if (EndGameCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0 || players.isEmpty()) finish()
            else if (seconds in arrayOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60))
                Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Die Server startet in ${if (seconds == 1) "${IMPORTANT}einer$TEXT Sekunde" else "$IMPORTANT$seconds$TEXT Sekunden"} neu")

            setLevel()
            seconds--

        }
    } else System.err.println("The endgame countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("endgame")

    override fun finish() {
        //                    Bukkit.getPluginManager().callEvent(EndGameCountdownFinishedEvent(this))
        Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Der Server startet neu")
        Bukkit.shutdown()
        stop()
    }
}
