/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.bungee.commands

import net.darkdevelopers.darkbedrock.darkness.bungee.commands.interfaces.ICommand
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 05.07.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis", "CanBeParameter", "unused")
abstract class Command(val commandName: String,
                       permission: String = "",
                       override val permissionMessage: String = "",
                       var usage: String = "",
                       val minLength: Int = 0,
                       val maxLength: Int = 0,
                       vararg aliases: String) : Command(commandName, permission, *aliases), ICommand {

    val hasHelp = usage.split("|").isNotEmpty()

    init {
        ProxyServer.getInstance().pluginManager.registerCommand(null, this)
        if (maxLength > 0) usage = when {
            minLength == 0 -> "|[help]|$usage"
            hasHelp -> "help|$usage"
            else -> usage
        }
    }

    override fun execute(sender: CommandSender, args: Array<String>?) {
        hasPermission(sender, permission) {
            when {
                (args == null || args.isEmpty()) && minLength > 0 -> perform(sender, emptyArray())
                args == null || args.size < minLength || args.size > maxLength || (maxLength > 0 && args[0].equals("help", true)) ->
                    sendUseMessage(sender)
                else -> perform(sender, args)
            }
        }
    }

    override fun sendUseMessage(sender: CommandSender) = if (!hasHelp)
        sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Nutze: $IMPORTANT/$commandName $TEXT$usage"))
    else {
        sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Nutze:"))
        for (usage in usage.split("|")) {
            if (usage.contains(":")) {
                val subCommand = usage.split(":")
                if (hasPermission(sender, subCommand[1])) sendUseMessage(sender, subCommand[0])
            } else sendUseMessage(sender, usage)
        }
    }

    private fun sendUseMessage(sender: CommandSender, usage: String) =
            sender.sendMessage(TextComponent("$TEXT- $IMPORTANT/$commandName $TEXT$usage"))

    override fun isPlayer(sender: CommandSender, lambda: (ProxiedPlayer) -> Unit) = isPlayer(sender, lambda) {
        sender.sendMessage(TextComponent("Der Command ist nur für Spieler"))
    }

    override fun isPlayer(sender: CommandSender, onSuccess: (ProxiedPlayer) -> Unit, onFail: () -> Unit) =
            if (sender is ProxiedPlayer) onSuccess(sender) else onFail()


}


