/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */
@file:JvmName("ScoreBoardUtils")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.entity.Player
import kotlin.collections.Map as KMap

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 16.01.2018 22:17.
 * Last edit 21.03.2019
 */

private val scoreboard: Scoreboard = Scoreboard()
private val objective: ScoreboardObjective = scoreboard.registerObjective("object", IScoreboardCriteria.b)

fun Player.sendLobbyScoreBoard(mapName: String, displayName: String) = sendScoreBoard(
    displayName, listOf(
        " ",
        "${TEXT}Map$IMPORTANT:",
        "$PRIMARY$mapName",
        "  "
    ).index().toScoreboardScore().toSet()
)

@Deprecated("", ReplaceWith("sendScoreBoard(displayName, scoreboardScores.toSet())"))
fun Player.sendScoreBoard(displayName: String, scoreboardScores: List<ScoreboardScore>) =
    sendScoreBoard(displayName, scoreboardScores.toSet())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:06.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendScoreBoard(displayName: String, scoreboardScores: Set<ScoreboardScore>) {

    objective.displayName = displayName.take(32)
    /* Remove Packet */
    sendPacket(PacketPlayOutScoreboardObjective(objective, 1))
    /* Create Packet */
    sendPacket(PacketPlayOutScoreboardObjective(objective, 0))
    /* Display Packet */
    sendPacket(PacketPlayOutScoreboardDisplayObjective(1, objective))
    /* Score Packet */
    scoreboardScores.forEach { sendPacket(PacketPlayOutScoreboardScore(it)) }

}

@Suppress("MemberVisibilityCanBePrivate")
@Deprecated("Use toScoreboardScore", ReplaceWith("(name to score).toScoreboardScore()"))
fun scoreboardScore(name: String, score: Int): ScoreboardScore = (name to score).toScoreboardScore()

fun Set<String>.index(): KMap<String, Int> = toList().index()

fun List<String>.index(): KMap<String, Int> {
    var index = size
    return associateWith { index-- }
}

fun KMap<String, Int>.toScoreboardScore(): List<ScoreboardScore> = map { it.toPair().toScoreboardScore() }
fun Pair<String, Int>.toScoreboardScore() = ScoreboardScore(
    scoreboard,
    objective, first
).apply { score = second }
