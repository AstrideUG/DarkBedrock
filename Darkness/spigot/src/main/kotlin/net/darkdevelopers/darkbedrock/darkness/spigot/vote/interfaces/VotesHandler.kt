/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.vote.interfaces

import net.darkdevelopers.darkbedrock.darkness.spigot.vote.Vote
import org.bukkit.command.CommandSender

/**
 * Created by LartyHD on 07.07.2018 11:01.
 * Last edit 07.07.2018
 */
interface VotesHandler {

    val votes: MutableSet<Vote>

    fun getResult()

    fun addVote(sender: CommandSender, voteName: String): Boolean

}
