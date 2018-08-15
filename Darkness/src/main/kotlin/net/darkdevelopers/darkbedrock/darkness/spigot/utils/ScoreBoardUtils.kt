/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 16.01.2018 22:17.
 * Last edit 07.07.2018
 */
object ScoreBoardUtils {
    private val scoreboard: Scoreboard = Scoreboard()
    private val objective: ScoreboardObjective = scoreboard.registerObjective("object", IScoreboardCriteria.b)

    fun sendLobbyScoreBoard(player: Player, mapName: String, displayName: String) {
        val score = arrayListOf<ScoreboardScore>()
        score.add(scoreboardScore(" ", 4))
        score.add(scoreboardScore("${TEXT}Map$IMPORTANT:", 3))
        score.add(scoreboardScore("$PRIMARY$mapName", 2))
        score.add(scoreboardScore("  ", 1))
        sendScoreBoard(player, displayName, score)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun sendScoreBoard(player: Player, displayName: String, scoreboardScores: List<ScoreboardScore>) {
        if (displayName.length >= 32) objective.displayName = displayName.substring(0, 31) else objective.displayName = displayName
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
    fun scoreboardScore(name: String, score: Int): ScoreboardScore {
        val scoreboardScore = ScoreboardScore(scoreboard, objective, name)
        scoreboardScore.score = score
        return scoreboardScore
    }

}
