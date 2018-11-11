package net.darkdevelopers.darkbedrock.darkness.spigot.player

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:58.
 * Last edit 24.08.2018
 */
interface SimpleGetTarget : DefaultGetTarget {

	override fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit) =
			if (player != null)
				lambda(player)
			else
				sender.sendMessage(Messages.PLAYER_NOT_ONLINE.toString())

	override fun getTarget(player: Player?, lambda: (Player?) -> Unit) = lambda(player)
}