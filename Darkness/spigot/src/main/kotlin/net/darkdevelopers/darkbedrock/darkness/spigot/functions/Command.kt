package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.isPlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:03.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:03.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
//TODO Edit the name
fun Command.possiblePlayer(
    messages: Map<String, String>,
    prefix: String,
    sender: CommandSender,
    arg: String?,
    singlePerms: String,
    otherPerms: String,
    execute: (CommandSender, Player) -> Unit
) {
    if (arg == null) sender.isPlayer {
        hasPermission(sender, singlePerms) {
            messages["$prefix.Success"].sendReplaced(sender)
            execute(sender, it)
            messages["$prefix.Successfully"].sendReplaced(sender)
        }
    } else getTarget(sender, arg.toPlayer()) {
        hasPermission(sender, otherPerms) {
            messages["$prefix.Other.Sender.Success"].sendReplaced(sender, sender, it)
            messages["$prefix.Other.Target.Success"].sendReplaced(it, sender, it)
            execute(sender, it)
            messages["$prefix.Other.Sender.Successfully"].sendReplaced(sender, sender, it)
            messages["$prefix.Other.Target.Successfully"].sendReplaced(it, sender, it)
        }
    }
}

private fun String?.sendReplaced(to: CommandSender, sender: CommandSender? = null, target: CommandSender? = null) {
    var a = this?.replace("<Sender>", sender?.name ?: to.name, true) ?: return
    if (target != null) a = a.replace("<Target>", target.name, true)
    a.sendIfNotNull(to)
}