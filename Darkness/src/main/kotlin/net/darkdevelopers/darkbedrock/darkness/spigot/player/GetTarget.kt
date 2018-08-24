package net.darkdevelopers.darkbedrock.darkness.spigot.player

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:53.
 * Last edit 24.08.2018
 */
interface GetTarget {

    fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit)

    fun getTarget(sender: CommandSender, uuid: UUID, lambda: (Player) -> Unit)

    fun getTarget(sender: CommandSender, name: String, lambda: (Player) -> Unit)

    fun getTarget(player: Player?, lambda: (Player?) -> Unit)

    fun getTarget(uuid: UUID, lambda: (Player?) -> Unit)

    fun getTarget(name: String, lambda: (Player?) -> Unit)
}
