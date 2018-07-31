package net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 02.06.2018
 */
interface HasPermission {

    fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit)

    fun hasPermission(target: CommandSender, permission: String): Boolean

}
