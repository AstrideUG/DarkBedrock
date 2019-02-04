package net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:46.
 * Last edit 02.06.2018
 */
interface SimpleHasPermission : HasPermission {

    val permissionMessage: String

    override fun hasPermission(target: CommandSender, permission: String, lambda: () -> Unit) {
        when {
            hasPermission(target, permission) -> lambda()
            permissionMessage.isBlank() -> target.sendMessage("§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.")
            else -> for (line in permissionMessage.replace("<permission>", permission).split("\n")) target.sendMessage(
                line
            )
        }
    }

    override fun hasPermission(target: CommandSender, permission: String): Boolean {
        if (permission.isEmpty()) return true
        for (perm in permission.split(";"))
            if (target.hasPermission(perm)) return true
        return false
    }
}
