package net.darkdevelopers.darkbedrock.darkness.bungee.utils

import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.07.2018 13:57.
 * Last edit 13.07.2018
 */
object Utils {
	@Suppress("MemberVisibilityCanBePrivate")
	fun getPlayers(): MutableCollection<out ProxiedPlayer> = ProxyServer.getInstance().players

	fun goThroughAllPlayers(lambda: (ProxiedPlayer) -> Unit) = getPlayers().forEach { lambda(it) }

	fun getTime(time: Long): String {
		var remainingTime = ""
		var seconds = time
		var minutes = 0L
		var hours = 0L
		var days = 0L
		var years = 0L
		while (time >= 60) {
			seconds -= 60
			minutes++
		}
		while (minutes >= 60) {
			minutes -= 60
			hours++
		}
		while (hours >= 24) {
			hours -= 24
			days++
		}
		while (days >= 365) {
			days -= 365
			years++
		}
		if (years == 1L) remainingTime = "$remainingTime${IMPORTANT}ein$TEXT Jahr " else if (years != 0L) remainingTime = "$remainingTime$IMPORTANT$years$TEXT Jahre "
		if (days == 1L) remainingTime = "$remainingTime${IMPORTANT}ein$TEXT Tag " else if (days != 0L) remainingTime = "$remainingTime$IMPORTANT$days$TEXT Tage "
		if (hours == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Stunde " else if (hours != 0L) remainingTime = "$remainingTime$IMPORTANT$hours$TEXT Stunden "
		if (minutes == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Minute " else if (minutes != 0L) remainingTime = "$remainingTime$IMPORTANT$minutes$TEXT Minuten "
		if (seconds == 1L) remainingTime = "$remainingTime$IMPORTANT$seconds$TEXT Sekunde " else if (time != 0L) remainingTime = "$remainingTime$IMPORTANT$seconds$TEXT Sekunden "
		return if (remainingTime.isEmpty()) "${IMPORTANT}0 ${TEXT}Sekunden " else remainingTime.substring(0, remainingTime.length - 1)
	}
}