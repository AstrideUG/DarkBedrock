package net.darkdevelopers.darkbedrock.darkness.spigot.commands

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:14.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
import net.darkdevelopers.darkbedrock.darkness.general.functions.getOrKey
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.possiblePlayer
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:13.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
abstract class SimplePlayerCommandModule(defaultCommandName: String) :
    SimplePermissionsCommandModule(defaultCommandName) {

    protected val otherPerms: String = config.permissions.getOrKey("$prefix.Other")

    protected inner class PlayerCommand(
        javaPlugin: JavaPlugin,
        name: String = config.commandName,
        permission: String = singlePerms,
        usage: String = "[Player]:$otherPerms",
        minLength: Int = 0,
        maxLength: Int = 1,
        tabCompleter: TabCompleter? = TabCompleter { _, _, _, args -> if (args.isEmpty()) Utils.getPlayers().map { it.name } else listOf<String>() },
        vararg aliases: String
    ) : PermissionCommand(
        javaPlugin,
        name,
        permission,
        usage = usage,
        minLength = minLength,
        maxLength = maxLength,
        tabCompleter = tabCompleter,
        aliases = *aliases
    ) {

        override fun perform(sender: CommandSender, args: Array<String>) =
            possiblePlayer(config.messages, prefix, sender, args[0], singlePerms, otherPerms) { cs: CommandSender, target: Player -> execute(cs, target) }

    }

    protected abstract fun execute(sender: CommandSender, target: Player)

}