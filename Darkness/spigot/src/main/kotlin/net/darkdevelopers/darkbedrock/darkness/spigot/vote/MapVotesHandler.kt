/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.vote

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.loadBukkitWorld
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 10:50.
 * Last edit 07.07.2018
 */
class MapVotesHandler(votes: MutableSet<Vote>, private val force: String?) : VotesHandler(votes) {
    public override fun finishVotes(winner: String) {
        getMapAndBroadcast(winner).loadBukkitWorld()
    }

    private fun getMapAndBroadcast(winner: String): String {
        broadcastMap(winner)
        return getMap(winner)
    }

    private fun broadcastMap(winner: String): Unit = if (isVotedMap())
        sendMapInfo(configService.prefix, winner)
    else sendWinnerMapInfo(configService.prefix, winner, calculateVotesCount(winner))

    private fun getMap(winner: String) = if (isVotedMap()) winner else force!!

    private fun isVotedMap() = force == null || force.isBlank()

    private fun calculateVotesCount(winner: String): Int {
        var count = 0
        votes.forEach { if (it.name.equals(winner, ignoreCase = true)) count = it.voter.size }
        return count
    }

    private fun sendMapInfo(prefix: String, mapName: String) = players.forEach {
        it.run {
            sendMessage(prefix)
            sendMessage("$prefix$TEXT     Map$IMPORTANT: $PRIMARY$mapName")
            sendMessage(prefix)
        }
    }

    private fun sendWinnerMapInfo(prefix: String, winner: String, count: Int) = players.forEach {
        it.run {
            sendMessage(prefix)
            sendMessage("$prefix$TEXT     Das Voting ist beendet")
            sendMessage("$prefix$TEXT     Gewinner$IMPORTANT: $PRIMARY$winner$TEXT ($IMPORTANT$count$TEXT)")
            sendMessage(prefix)
        }
    }
}