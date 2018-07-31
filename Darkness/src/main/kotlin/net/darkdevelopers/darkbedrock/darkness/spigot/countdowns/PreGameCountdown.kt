/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Sound

/**
 * Created by LartyHD on 24.06.2017  18:01.
 * Last edit 06.07.2018
 */
class PreGameCountdown : Countdown(5) {

    override fun start() = if (!isRunning) {
        isRunning = true
//        Bukkit.getPluginManager().callEvent(PreGameCountdownStartedEvent(this))
        loop {
            when (seconds) {
                0 -> finish()
                1 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Die Runde startet in ${IMPORTANT}einer$TEXT Sekunde")
                2, 3, 4, 5, 10, 15, 20, 30, 45, 60 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Die Runde startet in $IMPORTANT$seconds$TEXT Sekunden")
            }
            when (seconds) {
                10, 5, 4, 3, 2, 1 -> Utils.goThroughAllPlayers {
                    Utils.sendTitle(it, "$SECONDARY$seconds").sendTimings(it, 1, 18, 1)
                    it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
                }
            }
            setLevel()
            seconds--
        }
    } else System.err.println("The pregame countdown should start, although it is already running")

    override fun stop() = defaultStop("pregame")

    override fun finish() {
//        Bukkit.getPluginManager().callEvent(PreGameCountdownFinishedEvent(this))
        Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Die Runde startet")
        stop()
    }
}
