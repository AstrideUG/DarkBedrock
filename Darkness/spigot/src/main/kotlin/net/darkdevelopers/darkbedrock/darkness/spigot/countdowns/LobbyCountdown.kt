/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.LobbyCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.LobbyCountdownIdleEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.SECONDARY
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 24.06.2017 16:41.
 * Last edit 01.06.2019
 */
@Suppress("MemberVisibilityCanBePrivate")
class LobbyCountdown(
    seconds: Int = 60,
    val minPlayers: Int,
    val gameName: String,
    val players: Collection<Player> = allPlayers
) : Countdown(seconds) {

    var idling: Boolean = false
    private lateinit var idle: Thread

    override fun start(): Unit = if (!isRunning) {
        isRunning = true

        stopIdle()
        loop {

            if (LobbyCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0) {
                messages.lobbyCountdownStartGame.sendTo(players)
                players.forEach { it.playSound(it.location, Sound.LEVEL_UP, 1F, 1F) }
                stop()
            } else if (seconds in messages.lobbyCountdownStartGameInIfIn) messages.lobbyCountdownStartGameIn
                .replace("@seconds@", seconds.toString(), true)
                .sendTo(players)

            if (seconds == 10) players.forEach {
                it.sendTitle(gameName)
                it.sendSubTitle(messages.serverName)
                it.sendTimings(10, 20, 10)
                it.playSound(it.location, Sound.ORB_PICKUP, 1F, 1F)
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

            if (LobbyCountdownIdleEvent(this).call().isCancelled) return@loop

            val i = minPlayers - players.size
            if (i == 0) start()
            else messages.lobbyCountdownIdle.replace("@count@", minPlayers.toString(), true).sendTo(players)
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

}
	
