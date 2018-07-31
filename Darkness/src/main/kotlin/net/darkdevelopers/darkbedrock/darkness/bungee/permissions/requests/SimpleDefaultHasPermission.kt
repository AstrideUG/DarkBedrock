package net.darkdevelopers.darkbedrock.darkness.bungee.permissions.requests

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 05.07.2018
 */
interface SimpleDefaultHasPermission : DefaultHasPermission {

    val permissionMessage: String

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) {
        when {
            hasPermission(target, permission) -> lambda()
            permissionMessage.isNotEmpty() -> target.sendMessage(TextComponent("\u00a7cYou do not have permission to execute this command!"))
            else -> for (line in permissionMessage.replace("<permission>", permission).split("\n")) target.sendMessage(TextComponent(line))
        }
    }

    override fun hasPermission(target: CommandSender, permission: String): Boolean {
        if (permission.isEmpty()) return true
        for (perm in permission.split(";"))
            if (target.hasPermission(perm)) return true
        return false
    }
}
