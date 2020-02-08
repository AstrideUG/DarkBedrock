/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.commands.interfaces

import net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests.SimpleDefaultHasPermission
import net.darkdevelopers.darkbedrock.darkness.spigot.player.SimpleGetTarget
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 09:41.
 * Current Version: 1.0 (11.05.2018 - 05.06.2019)
 */
interface ICommand : CommandExecutor, SimpleDefaultHasPermission, SimpleGetTarget {

    fun perform(sender: CommandSender, args: Array<String>)

    fun sendUseMessage(sender: CommandSender)

}
