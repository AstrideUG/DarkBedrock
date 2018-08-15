package net.darkdevelopers.darkbedrock.darkness.spigot.vote

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.MapsUtils
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 10:50.
 * Last edit 07.07.2018
 */
class MapVotesHandler(votes: MutableSet<Vote>, private val force: String?) : VotesHandler(votes) {
    public override fun finishVotes(winner: String) {
        MapsUtils.loadMap(getMapAndBroadcast(winner))
    }

    private fun getMapAndBroadcast(winner: String): String {
        broadcastMap(winner)
        return getMap(winner)
    }

    private fun broadcastMap(winner: String) =
            if (isVotedMap())
                sendMapInfo(Messages.PREFIX.toString(), winner)
            else
                sendWinnerMapInfo(Messages.PREFIX.toString(), winner, calculateVotesCount(winner))

    private fun getMap(winner: String) = if (isVotedMap()) winner else force!!

    private fun isVotedMap() = force == null || force.isBlank()

    private fun calculateVotesCount(winner: String): Int {
        var count = 0
        votes.forEach { if (it.name.equals(winner, ignoreCase = true)) count = it.voter.size }
        return count
    }

    private fun sendMapInfo(prefix: String, mapName: String) = Utils.goThroughAllPlayers {
        it.run {
            sendMessage(prefix)
            sendMessage("$prefix$TEXT     Map$IMPORTANT: $PRIMARY$mapName")
            sendMessage(prefix)
        }
    }

    private fun sendWinnerMapInfo(prefix: String, winner: String, count: Int) = Utils.goThroughAllPlayers {
        it.run {
            sendMessage(prefix)
            sendMessage("$prefix$TEXT     Das Voting ist beendet")
            sendMessage("$prefix$TEXT     Gewinner$IMPORTANT: $PRIMARY$winner$TEXT ($IMPORTANT$count$TEXT)")
            sendMessage(prefix)
        }
    }
}