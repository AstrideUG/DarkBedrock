/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.team

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.team.utils.Teams
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:35.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */

val Iterable<GameTeam>.noTeamPlayers: Set<Player> get() = players.filter { getTeam(it) == null }.toSet()
val Iterable<GameTeam>.livingTeams: Int get() = count { it.players.size > 0 }
val Iterable<GameTeam>.lastLivingTeam: GameTeam? get() = find { it.players.size > 0 }
val Iterable<GameTeam>.lowestTeam: GameTeam
    get() = sortedWith(Comparator { o1: GameTeam, o2: GameTeam ->
        if (o1.players.size < o2.players.size) -1 else 1
    }).first()

val GameTeam.item: ItemStack
    get() = ItemBuilder(Material.BANNER, players.size, name.toBannerID())
        .setName("$chatColor$name $IMPORTANT| $TEXT${players.size}$IMPORTANT/$TEXT$size")
        .setLore(players.map { "$TEXT- $chatColor${it.displayName}" }.ifEmpty { listOf("${TEXT}Leer") })
        .addAllItemFlags()
        .build()

fun Iterable<GameTeam>.getTeam(player: Player): GameTeam? = find { player in it.players }
fun Iterable<GameTeam>.getTeam(name: String): GameTeam? = find { it.name.equals(name, ignoreCase = true) }

fun Iterable<GameTeam>.finishTeams(): Boolean {
    players.forEach {
        if (getTeam(it) != null) return@forEach
        val gameTeam = lowestTeam
        if (!gameTeam.add(it))
            "${configService.prefix}${IMPORTANT}Du konntest keine Team zugeortet werden!".sendTo(it)
        else "${configService.prefix}${TEXT}Du bist nun im Team $IMPORTANT${gameTeam.chatColor}${gameTeam.name}".sendTo(
            it
        )
    }
    val size = count { it.players.size > 0 }
    if (size < 2) Bukkit.broadcastMessage("${configService.prefix}${TEXT}Es müssen mindestens ${IMPORTANT}zwei ${TEXT}Teams mit Spieler existieren")
    return size < 2
}

fun generateTeams(teamsCount: Int, colored: Boolean = true): MutableSet<GameTeam> =
    generateTeamsTo(mutableSetOf(), teamsCount, colored)

@Suppress("MemberVisibilityCanBePrivate")
fun <C : MutableCollection<in GameTeam>> generateTeamsTo(destination: C, teamsCount: Int, colored: Boolean = true): C {
    if (colored && teamsCount <= 14) Teams.values().forEach { team ->
        if (destination.size < teamsCount)
            destination += GameTeam(team.name, team.color, Utils.players.size / teamsCount + 1, true)
    } else for (i in 1..teamsCount) destination += GameTeam(
        "T$i",
        ChatColor.DARK_GRAY,
        Utils.players.size / teamsCount + 1,
        false
    )
    return destination
}

private fun String.toBannerID(): Short = when (this) {
    "Weiß" -> 15
    "Orange" -> 14
    "Magenta" -> 13
    "Hellblau" -> 12
    "Gelb" -> 11
    "Hellgrün" -> 10
    "Rosa" -> 9
    "Grau" -> 8
    "Hellgrau" -> 7
    "Türkis" -> 6
    "Violettes" -> 5
    "Blau" -> 4
    "Braun" -> 3
    "Grün" -> 2
    "Rot" -> 1
    "Schwarz" -> 0
    else -> 8
}
