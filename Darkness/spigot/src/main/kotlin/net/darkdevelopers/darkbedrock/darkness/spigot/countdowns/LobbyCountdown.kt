/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.LobbyCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.LobbyCountdownIdleEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendSubTitle
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTimings
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTitle
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.universal.functions.call
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

/**
 * Created by LartyHD on 24.06.2017 16:41.
 * Last edit 03.05.2019
 */
class LobbyCountdown(
    seconds: Int = 60,
    private val minPlayers: Int,
    private val gameName: String,
    private val players: Collection<Player> = allPlayers
) : Countdown(seconds) {

    private var idling: Boolean = false
    private lateinit var idle: Thread

    override fun start(): Unit = if (!isRunning) {
        isRunning = true

        stopIdle()
        loop {

            if (LobbyCountdownCallEvent().call().isCancelled) return@loop

            if (seconds == 0) finish()
            else if (seconds in arrayOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60))
                Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Das Spiel startet in $IMPORTANT${if (seconds == 1) "einer$TEXT Sekunde" else "$seconds$TEXT Sekunden"}")

            if (seconds == 10) {
                players.forEach {
                    it.sendTitle(gameName)
                    it.sendSubTitle(Messages.SERVER_NAME.toString())
                    it.sendTimings(10, 20, 10)
                    it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
                }
            } else if (seconds in 1..5) players.forEach {
                it.sendTitle("$SECONDARY$seconds")
                it.sendTimings(1, 18, 1)
                it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
            }

            seconds--
            setLevel()

        }
    } else System.err.println("The lobby countdown should start, although it is already running")

    fun idle() = if (!idling) {
        idling = true
        stopCountdown()
        idle = loop(TimeUnit.SECONDS.toMillis(10)) {

            if (LobbyCountdownIdleEvent().call().isCancelled) return@loop

            val i = minPlayers - players.size
            if (i == 0) start()
            else Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Warte auf $IMPORTANT${if (seconds == 1) "einen$TEXT weiteren" else "$i$TEXT weitere"} Spieler...")
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
        Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Das Spiel startet")
        players.forEach { it.playSound(it.location, Sound.LEVEL_UP, 1F, 1F) }
        stopCountdown()
//        }
    }
}
	
