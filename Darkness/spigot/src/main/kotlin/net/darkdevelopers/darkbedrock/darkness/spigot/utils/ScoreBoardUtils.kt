/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
@file:JvmName("ScoreBoardUtils")

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.entity.Player
import kotlin.collections.Map as KMap

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 16.01.2018 22:17.
 * Last edit 13.01.2019
 */

private val scoreboard: Scoreboard = Scoreboard()
private val objective: ScoreboardObjective = scoreboard.registerObjective("object", IScoreboardCriteria.b)

fun sendLobbyScoreBoard(player: Player, mapName: String, displayName: String) {
    val scores = mapOf(
        " " to 4,
        "${TEXT}Map$IMPORTANT:" to 3,
        "$PRIMARY$mapName" to 2,
        "  " to 1
    ).toScoreboardScore()
    sendScoreBoard(player, displayName, scores)
}

fun sendScoreBoard(player: Player, displayName: String, scoreboardScores: List<ScoreboardScore>) =
    sendScoreBoard(player, displayName, scoreboardScores.toSet())

@Suppress("MemberVisibilityCanBePrivate")
fun sendScoreBoard(player: Player, displayName: String, scoreboardScores: Set<ScoreboardScore>) {
    objective.displayName = displayName.take(31)
    /*
     * Remove Packet
     */
    Utils.sendPacket(player, PacketPlayOutScoreboardObjective(objective, 1))
    /*
     * Create Packet
     */
    Utils.sendPacket(player, PacketPlayOutScoreboardObjective(objective, 0))
    /*
     * Display Packet
     */
    Utils.sendPacket(player, PacketPlayOutScoreboardDisplayObjective(1, objective))
    /*
     * Score Packet
     */
    scoreboardScores.forEach { Utils.sendPacket(player, PacketPlayOutScoreboardScore(it)) }
}


@Suppress("MemberVisibilityCanBePrivate")
@Deprecated("Use toScoreboardScore", ReplaceWith("(name to score).toScoreboardScore()"))
fun scoreboardScore(name: String, score: Int): ScoreboardScore = (name to score).toScoreboardScore()


fun Set<String>.index(): KMap<String, Int> {
    var index = 0
    return associateWith { index++ }
}

fun KMap<String, Int>.toScoreboardScore(): List<ScoreboardScore> = map { it.toPair().toScoreboardScore() }

fun Pair<String, Int>.toScoreboardScore(): ScoreboardScore {
    val scoreboardScore = ScoreboardScore(scoreboard, objective, first)
    scoreboardScore.score = second
    return scoreboardScore
}
