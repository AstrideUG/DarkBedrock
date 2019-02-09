package net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 02.06.2018
 */
interface SimpleDefaultHasPermission : SimpleHasPermission {

    val permission: String

    fun hasPermission(target: CommandSender, lambda: () -> Unit) = super.hasPermission(target, permission, lambda)

    fun hasPermission(target: CommandSender) = super.hasPermission(target, permission)
}
