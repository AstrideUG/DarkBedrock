/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 05.06.2019
 */
interface SimpleHasPermission : HasPermission {

    val permissionMessage: String

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit): Unit = when {
        hasPermission(target, permission) -> lambda()
        permissionMessage.isBlank() -> target.sendMessage(configService.defaultHasPermission)
        else -> for (line in permissionMessage.replace("<permission>", permission).split("\n")) line.sendTo(target)
    }

    override fun hasPermission(target: CommandSender, permission: String): Boolean {
        if (permission.isEmpty()) return true
        for (perm in permission.split(";"))
            if (target.hasPermission(perm)) return true
        return false
    }
}
