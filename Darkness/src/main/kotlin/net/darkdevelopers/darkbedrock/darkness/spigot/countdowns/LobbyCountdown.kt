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
 * Created by LartyHD on 24.06.2017  16:41.
 * Last edit 06.07.2018
 */
class LobbyCountdown(private val minPlayers: Int, private val gameName: String) : Countdown(60) {

    private var idling: Boolean = false
    private lateinit var idle: Thread

    override fun start() = if (!isRunning) {
        isRunning = true
        stopIdle()
        loop {
            when (seconds) {
                0 -> finish()
                1 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Das Spiel startet in ${IMPORTANT}einer$TEXT Sekunde")
                2, 3, 4, 5, 10, 15, 20, 30, 45, 60 -> Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Das Spiel startet in $IMPORTANT$seconds$TEXT Sekunden")
            }
            when (seconds) {
                10 -> {
//                    Bukkit.getPluginManager().callEvent(LobbyCountdownLastTenSecondsEvent(this))
                    Utils.goThroughAllPlayers {
                        Utils.sendTitle(it, gameName).sendSubTitle(it, Messages.SERVER_NAME.toString()).sendTimings(it, 10, 20, 10)
                        it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
                    }
                }
                5, 4, 3, 2, 1 -> Utils.goThroughAllPlayers {
                    Utils.sendTitle(it, "$SECONDARY$seconds").sendTimings(it, 1, 18, 1)
                    it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
                }
            }
            seconds--
            setLevel()
        }
    } else System.err.println("The lobby countdown should start, although it is already running")

    fun idle() = if (!idling) {
        idling = true
        stopCountdown()
        idle = loop(10000) {
            when (minPlayers - Bukkit.getOnlinePlayers().size) {
                0 -> start()
                1 -> Bukkit.broadcastMessage("${Messages.PREFIX}" + TEXT + "Warte auf " + IMPORTANT + "einen" + TEXT + " weiteren Spieler...")
                else -> Bukkit.broadcastMessage("${Messages.PREFIX}" + TEXT + "Warte auf " + IMPORTANT + (minPlayers - Bukkit.getOnlinePlayers().size) + TEXT + " weitere Spieler...")
            }
        }
    } else System.err.println("The lobby countdown idle should start, although it is already running")

    override fun stop() {
        stopCountdown()
        stopIdle()
    }

    private fun stopCountdown() = defaultStop("lobby")

    private fun stopIdle() = if (idling) {
        idle.interrupt()
        idling = false
    } else System.err.println("The lobby countdown idle should be stopped even though it is not running")

    override fun finish() {
//        val lobbyCountdownPreFinishedEvent = LobbyCountdownPreFinishedEvent(this)
//        Bukkit.getPluginManager().callEvent(lobbyCountdownPreFinishedEvent)
//        if (!lobbyCountdownPreFinishedEvent.isNext()) {
//            Bukkit.getPluginManager().callEvent(LobbyCountdownFinishedEvent(this))
        Bukkit.broadcastMessage("${Messages.PREFIX}" + TEXT + "Das Spiel startet")
        Utils.goThroughAllPlayers { it.playSound(it.location, Sound.LEVEL_UP, 1F, 1F) }
        stopCountdown()
//        }
    }
}
	
