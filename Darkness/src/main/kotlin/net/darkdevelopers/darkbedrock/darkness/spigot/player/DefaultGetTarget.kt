package net.darkdevelopers.darkbedrock.darkness.spigot.player

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:53.
 * Last edit 24.08.2018
 */
interface DefaultGetTarget : GetTarget {

    override fun getTarget(sender: CommandSender, uuid: UUID, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(uuid), lambda)

    override fun getTarget(sender: CommandSender, name: String, lambda: (Player) -> Unit) = getTarget(sender, Bukkit.getPlayer(name), lambda)

    override fun getTarget(uuid: UUID, lambda: (Player?) -> Unit) = getTarget(Bukkit.getPlayer(uuid), lambda)

    override fun getTarget(name: String, lambda: (Player?) -> Unit) = getTarget(Bukkit.getPlayer(name), lambda)
}
