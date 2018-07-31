/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko & Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.commands.interfaces

import net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests.SimpleDefaultHasPermission
import net.darkdevelopers.darkbedrock.darkness.spigot.player.SimpleGetTarget
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created by Segdo aka. Dominik Milenko & Lars Artmann | LartyHD on 11.05.2018 09:41.
 * Project: Darkness
 *
 * Web: https://segdogames.com
 * Mail: segdo@segdogames.com
 */
interface ICommand : CommandExecutor, SimpleDefaultHasPermission, SimpleGetTarget {

    override fun onCommand(sender: CommandSender, command: org.bukkit.command.Command, s: String, args: Array<String>?): Boolean

    fun perform(sender: CommandSender, args: Array<String>)

    fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit)

    fun isPlayer(sender: CommandSender, onSuccess: (Player) -> Unit, onFail: () -> Unit)

    fun sendUseMessage(sender: CommandSender)

}
