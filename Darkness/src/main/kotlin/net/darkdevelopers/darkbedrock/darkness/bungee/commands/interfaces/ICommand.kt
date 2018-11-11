/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko & Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.bungee.commands.interfaces

import net.darkdevelopers.darkbedrock.darkness.bungee.permissions.requests.SimpleHasPermission
import net.darkdevelopers.darkbedrock.darkness.bungee.player.SimpleGetTarget
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 09:41..
 * Last edit 05.07.2018
 */
interface ICommand : SimpleHasPermission, SimpleGetTarget {

	fun perform(sender: CommandSender, args: Array<String>)

	fun isPlayer(sender: CommandSender, lambda: (ProxiedPlayer) -> Unit)

	fun isPlayer(sender: CommandSender, onSuccess: (ProxiedPlayer) -> Unit, onFail: () -> Unit)

	fun sendUseMessage(sender: CommandSender)

}
