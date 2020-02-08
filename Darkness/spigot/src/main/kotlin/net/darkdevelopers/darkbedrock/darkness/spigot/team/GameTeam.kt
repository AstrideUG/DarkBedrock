/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.team

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scoreboard.NameTagVisibility
import org.bukkit.scoreboard.Team

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.01.2018 11:43.
 * Current Version: 1.0 (03.01.2018 - 26.04.2019)
 */
class GameTeam(
    val name: String,
    val chatColor: ChatColor,
    var size: Int,
    colored: Boolean
) {
    val players: MutableSet<Player> = mutableSetOf()
    val team: Team
    val leatherColor: Color = getLeatherColor(chatColor)
    lateinit var location: Location

    init {
        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        team = if (scoreboard.getTeam(name) == null) scoreboard.registerNewTeam(name) else scoreboard.getTeam(name)
        team.run {
            setAllowFriendlyFire(false)
            nameTagVisibility = NameTagVisibility.ALWAYS
            displayName = "$chatColor$name"
            prefix = if (!colored) "$chatColor$name $TEXT| $chatColor" else "$chatColor"
        }
    }

    @Suppress("DEPRECATION")
    fun add(player: Player): Boolean = if (players.size >= size) false else {
        players += player
        team.addPlayer(player)
        true
    }

    @Suppress("DEPRECATION")
    fun remove(player: Player) {
        players -= player
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameTeam) return false

        if (name != other.name) return false
        if (chatColor != other.chatColor) return false
        if (size != other.size) return false
        if (players != other.players) return false
        if (team != other.team) return false
        if (leatherColor != other.leatherColor) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + chatColor.hashCode()
        result = 31 * result + size
        result = 31 * result + players.hashCode()
        result = 31 * result + team.hashCode()
        result = 31 * result + leatherColor.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }

    override fun toString(): String =
        "GameTeam(name='$name', chatColor=$chatColor, size=$size, players=$players, team=$team, leatherColor=$leatherColor, location=$location)"


}
