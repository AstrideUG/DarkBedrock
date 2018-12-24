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
fun Command.
        possiblePlayer(
    messages: Map<String, String>,
    prefix: String,
    sender: CommandSender,
    arg: String?,
    singlePerms: String,
    otherPerms: String,
    execute: (CommandSender, Player) -> Unit
) {
    if (arg == null) sender.isPlayer {
        hasPermission(sender, singlePerms){
            messages["$prefix.Success"].sendIfNotNull(sender)
            execute(sender, it)
            messages["$prefix.Successfully"].sendIfNotNull(sender)
        }
    } else getTarget(sender, arg.toPlayer()) {
        hasPermission(sender, otherPerms){
            messages["$prefix.Other.Sender.Success"].sendIfNotNull(sender)
            messages["$prefix.Other.Target.Success"].sendIfNotNull(it)
            execute(sender, it)
            messages["$prefix.Other.Sender.Successfully"].sendIfNotNull(sender)
            messages["$prefix.Other.Target.Successfully"].sendIfNotNull(it)
        }
    }
}