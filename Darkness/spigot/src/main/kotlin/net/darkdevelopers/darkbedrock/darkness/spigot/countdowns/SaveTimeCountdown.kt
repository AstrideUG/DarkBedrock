/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.events.countdown.SaveTimeCountdownCallEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.entity.Player
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

class SaveTimeCountdown(
    seconds: Int = 60,
    private val inputPlayers: () -> Collection<Player> = { allPlayers }
) : Countdown(seconds) {

    val players get() = inputPlayers()

    override fun start(): Unit = if (!isRunning) {
        isRunning = true
        loop {

            if (SaveTimeCountdownCallEvent(this).call().isCancelled) return@loop

            if (seconds == 0 || players.isEmpty()) {
                messages.saveTimeCountdownEndProtection.sendTo(players)
                stop()
            } else if (seconds in messages.saveTimeCountdownEndProtectionInIfIn) messages.saveTimeCountdownEndProtectionIn
                .replace("@seconds@", seconds.toString(), true)
                .sendTo(Utils.players)

            seconds--

        }
    } else System.err.println("The savetime countdown should start, although it is already running")

    override fun stop(): Unit = defaultStop("savetime")

}
