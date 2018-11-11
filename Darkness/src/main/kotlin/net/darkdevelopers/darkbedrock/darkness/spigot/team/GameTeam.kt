/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.team

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scoreboard.NameTagVisibility
import org.bukkit.scoreboard.Team
import java.util.*

/**
 * Created by LartyHD on 03.01.2018  11:43.
 */
class GameTeam internal constructor(
		val name: String,
		val chatColor: ChatColor,
		var size: Int,
		colored: Boolean
) {
	val players: MutableSet<Player> = HashSet()
	val team: Team
	val leatherColor: Color = getLeatherColor(chatColor)
	private lateinit var location: Location

	init {
		val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
		team = if (scoreboard.getTeam(name) == null) scoreboard.registerNewTeam(name) else scoreboard.getTeam(name)
		team.run {
			setAllowFriendlyFire(false)
			nameTagVisibility = NameTagVisibility.ALWAYS
			displayName = "$chatColor$name"
			prefix = if (!colored) "$chatColor$name §7| $chatColor" else "$chatColor"
		}
	}

	@Suppress("DEPRECATION")
	fun add(player: Player) = if (players.size >= size) false else {
		players.add(player)
		team.addPlayer(player)
		true
	}

	@Suppress("DEPRECATION")
	fun remove(player: Player) {
		players.remove(player)
		team.removePlayer(player)
	}

	private fun getLeatherColor(chatColor: ChatColor): Color = when (chatColor.char) {
		'0' -> Color.fromRGB(0, 0, 0)
		'1' -> Color.fromRGB(0, 0, 170)
		'2' -> Color.fromRGB(0, 170, 0)
		'3' -> Color.fromRGB(0, 170, 170)
		'4' -> Color.fromRGB(170, 0, 0)
		'5' -> Color.fromRGB(170, 0, 170)
		'6' -> Color.fromRGB(255, 170, 0)
		'7' -> Color.fromRGB(170, 170, 170)
		'8' -> Color.fromRGB(85, 85, 85)
		'9' -> Color.fromRGB(85, 85, 255)
		'a' -> Color.fromRGB(85, 255, 85)
		'b' -> Color.fromRGB(85, 255, 255)
		'c' -> Color.fromRGB(255, 85, 85)
		'd' -> Color.fromRGB(255, 85, 255)
		'e' -> Color.fromRGB(255, 255, 85)
		'f' -> Color.fromRGB(255, 255, 255)
		else -> Color.WHITE
	}

	override fun toString(): String =
			"GameTeam(chatColor=$chatColor, size=$size, players=$players, team=$team, leatherColor=$leatherColor, location=$location)"
}
