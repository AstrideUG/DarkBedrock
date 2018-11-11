package net.darkdevelopers.darkbedrock.darkness.bungee.permissions.requests

import net.md_5.bungee.api.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 05.07.2018
 */
interface HasPermission {

	fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit)

	fun hasPermission(target: CommandSender, permission: String): Boolean

}
