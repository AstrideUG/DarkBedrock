/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.Bukkit

/**
 * Created by LartyHD on 24.06.2017  17:22.
 * Last edit 06.07.2018
 */
class EndGameCountdown : Countdown(15) {

    override fun start() = if (!isRunning) {
        isRunning = true
//            Bukkit.getPluginManager().callEvent(EndGameCountdownStartedEvent(this))
        loop {
            if (Bukkit.getOnlinePlayers().isEmpty()) finish()
            when (seconds) {
                0 -> finish()
                1 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Der Server startet in ${IMPORTANT}einer$TEXT Sekunde neu")
                2, 3, 4, 5, 10, 15, 20, 30, 45, 60 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Der Server startet in $IMPORTANT$seconds$TEXT Sekunden neu")
            }
            setLevel()
            seconds--
        }
    } else System.err.println("The endgame countdown should start, although it is already running")

    override fun stop() = defaultStop("endgame")

    override fun finish() {
        //                    Bukkit.getPluginManager().callEvent(EndGameCountdownFinishedEvent(this))
        Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Der Server startet neu")
        Bukkit.shutdown()
        stop()
    }
}
