/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.Bukkit

/**
 * Created by LartyHD on 24.06.2017  18:01.
 * Last edit 06.07.2018
 */
class SaveTimeCountdown : Countdown(60) {

	override fun start() = if (!isRunning) {
		isRunning = true
//        Bukkit.getPluginManager().callEvent(SaveTimeCountdownStartedEvent(this))
		loop {
			when (seconds) {
				0 -> finish()
				1 -> Bukkit.broadcastMessage("${Messages.PREFIX} ${TEXT}Die Schutzzeit ended in ${IMPORTANT}einer ${TEXT}Sekunde")
				2, 3, 4, 5, 10, 15, 20, 30, 45, 60 -> Bukkit.broadcastMessage("${Messages.PREFIX} ${TEXT}Die Schutzzeit ended in $IMPORTANT$seconds ${TEXT}Sekunden")
			}
			seconds--
		}
	} else System.err.println("The save time countdown should start, although it is already running")

	override fun stop() = defaultStop("savetime")

	override fun finish() {
//        Bukkit.getPluginManager().callEvent(SaveTimeCountdownFinishedEvent(this))
		Bukkit.broadcastMessage("${Messages.PREFIX} ${TEXT}Die Schutzzeit ended")
		stop()
	}

}
