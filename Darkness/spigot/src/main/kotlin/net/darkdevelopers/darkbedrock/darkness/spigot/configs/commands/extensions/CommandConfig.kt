/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands.CommandConfig
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/*
 * Created on 25.06.2019 03:40.
 * @author Lars Artmann | LartyHD
 */

inline fun <C : CommandConfig> C.register(
    plugin: JavaPlugin,
    tabCompleter: TabCompleter? = null,
    crossinline code: (sender: CommandSender, args: Array<String>, commandConfig: C) -> Unit
): Command = object : Command(
    plugin,
    commandName,
    permission,
    permissionMessage,
    usage,
    minLength,
    maxLength,
    tabCompleter,
    *aliases.toTypedArray()
) {
    override fun perform(sender: CommandSender, args: Array<String>): Unit = code(sender, args, this@register)
}