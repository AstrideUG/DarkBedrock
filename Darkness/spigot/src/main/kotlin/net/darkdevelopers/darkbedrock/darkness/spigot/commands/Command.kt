/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.commands

import net.darkdevelopers.darkbedrock.darkness.general.utils.getValue
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.interfaces.ICommand
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.copyed.ExternalPluginCommand
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.isPlayer
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 05.06.2019
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis", "CanBeParameter", "unused")
abstract class Command(
    val javaPlugin: JavaPlugin,
    val commandName: String,
    override val permission: String = "",
    override val permissionMessage: String = "",
    var usage: String = "",
    val minLength: Int = 0,
    val maxLength: Int = 0,
    tabCompleter: TabCompleter? = null,
    vararg val aliases: String
) : ICommand {

    val hasHelp = usage.split("|").size > 1

    init {
        val commandMap = javaPlugin.server.getValue("commandMap") as? CommandMap
        if (commandMap != null) {
            val command = ExternalPluginCommand(commandName, javaPlugin)
            if (maxLength > 0) usage = when {
                minLength == 0 -> "|[help]|$usage"
                hasHelp -> "help|$usage"
                else -> usage
            }
            command.apply {
                this.aliases = aliases.toList()
                permission = null
                permissionMessage = ""
                this.tabCompleter = tabCompleter
            }
            command.executor = this
            commandMap.register(javaPlugin.name, command)
        }
    }

    override fun onCommand(
        sender: CommandSender,
        command: org.bukkit.command.Command,
        s: String,
        args: Array<String>?
    ): Boolean {
        hasPermission(sender) {
            if (args == null ||
                args.size < minLength ||
                args.size > maxLength ||
                (maxLength > 0 && args.isNotEmpty() && args[0].equals("help", true))
            ) sendUseMessage(sender)
            else perform(sender, args)
        }
        return true
    }

    override fun sendUseMessage(sender: CommandSender) = if (!hasHelp)
        configService.commandUseMessageSingle.replaceCommand().sendTo(sender)
    else {
        configService.commandUseMessagePrefix.replaceCommand().sendTo(sender)
        for (usage in usage.split("|")) {
            if (usage.contains(":")) {
                val subCommand = usage.split(":")
                if (hasPermission(sender, subCommand[1])) sendUseMessage(sender, subCommand[0])
            } else sendUseMessage(sender, usage)
        }
    }

    private fun sendUseMessage(sender: CommandSender, usage: String) {
        val textComponent = TextComponent(configService.commandUseMessageLine.replaceCommand(usage))
        sender.isPlayer({
            textComponent.clickEvent = ClickEvent(
                ClickEvent.Action.SUGGEST_COMMAND,
                configService.commandUseMessageRun.replaceCommand(usage)
            )
            textComponent.hoverEvent = HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                arrayOf(TextComponent(configService.commandUseMessageHover.replaceCommand(usage)))
            )
            it.spigot().sendMessage(textComponent)
        }, { sender.sendMessage(textComponent.text) })
    }

    private fun String.replaceCommand(usage0: String = usage) = replace("@command-name@", commandName)
        .replace("@permission@", permission)
        .replace("@permissionMessage@", permissionMessage)
        .replace("@usage@", usage0)
        .replace("@minLength@", minLength.toString())
        .replace("@maxLength@", maxLength.toString())
        .replace("@aliases@", aliases.joinToString())

}




