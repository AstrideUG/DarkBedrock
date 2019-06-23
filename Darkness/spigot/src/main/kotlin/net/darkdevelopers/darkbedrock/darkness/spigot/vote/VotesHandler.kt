/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.vote

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.vote.interfaces.VotesHandler
import org.bukkit.command.CommandSender

/**
 * Created by LartyHD on ?.
 * Last edit 07.07.2018
 */
abstract class VotesHandler(override val votes: MutableSet<Vote>) : VotesHandler {

    override fun getResult() {
        if (votes.isEmpty()) throw IndexOutOfBoundsException("votes can not be empty")
        var max = -1
        var winner = ""
        votes.forEach {
            val i = it.voter.size
            if (i > max) {
                max = i
                winner = it.name
            }
        }
        finishVotes(winner)
    }

    override fun addVote(sender: CommandSender, voteName: String): Boolean {
        if (voteName.isBlank()) throw IndexOutOfBoundsException("voteName can not be blank")
        votes.forEach {
            it.voter.remove(sender.name)
            if (it.name.equals(voteName, ignoreCase = true)) {
                it.voter.add(sender.name)
                sender.sendMessage("${configService.prefix}${TEXT}Du hast für $IMPORTANT$voteName$TEXT abgestimmt")
                return true
            }
        }
        sender.sendMessage("${configService.prefix}${TEXT}Es ist ein ${IMPORTANT}Fehler$TEXT beim zählen deiner stimme aufgetreten!")
        return false
    }

    protected abstract fun finishVotes(winner: String)
}
