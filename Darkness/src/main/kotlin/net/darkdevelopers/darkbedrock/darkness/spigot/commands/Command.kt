/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.commands

import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils.getValueAs
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.interfaces.ICommand
import net.darkdevelopers.darkbedrock.darkness.spigot.copyed.ExternalPluginCommand
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.03.2018 03:41.
 * Last edit 24.05.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "LeakingThis", "CanBeParameter", "unused")
abstract class Command(val javaPlugin: JavaPlugin,
                       val commandName: String,
                       override val permission: String = "",
                       override val permissionMessage: String = "",
                       var usage: String = "",
                       val minLength: Int = 0,
                       val maxLength: Int = 0,
                       tabCompleter: TabCompleter? = null,
                       vararg aliases: String) : ICommand {

    val hasHelp = usage.split("|").isNotEmpty()

    init {
        val commandMap = getValueAs<CommandMap>(Bukkit.getServer(), "commandMap")
        if (commandMap != null) {
            val command = ExternalPluginCommand(commandName, javaPlugin)
            commandMap.register(javaPlugin.name, command)
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
        }
    }

    override fun onCommand(sender: CommandSender, command: org.bukkit.command.Command, s: String, args: Array<String>?): Boolean {
        hasPermission(sender) {
            when {
                args == null || args.isEmpty() -> perform(sender, emptyArray())
                args.size < minLength || args.size > maxLength || (maxLength > 0 && args[0].equals("help", true)) ->
                    sendUseMessage(sender)
                else -> perform(sender, args)
            }
        }
        return true
    }

    override fun sendUseMessage(sender: CommandSender) = if (!hasHelp)
        sender.sendMessage("${Messages.PREFIX}${TEXT}Nutze: $IMPORTANT/$commandName $TEXT$usage")
    else {
        sender.sendMessage("${Messages.PREFIX}${TEXT}Nutze:")
        for (usage in usage.split("|")) {
            if (usage.contains(":")) {
                val subCommand = usage.split(":")
                if (hasPermission(sender, subCommand[1])) sendUseMessage(sender, subCommand[0])
            } else sendUseMessage(sender, usage)
        }
    }

    private fun sendUseMessage(sender: CommandSender, usage: String) {
        val textComponent = TextComponent("$TEXT- $IMPORTANT/$commandName $TEXT$usage")
        isPlayer(sender, {
            textComponent.clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/$commandName ${usage.replace("[", "").replace("]", "").replace("<", "").replace(">", "")}")
            textComponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("${TEXT}Klicke um den Command vorzuschlagen")))
            it.spigot().sendMessage(textComponent)
        }, { sender.sendMessage(textComponent.text) })
    }

    override fun isPlayer(sender: CommandSender, lambda: (Player) -> Unit) = isPlayer(sender, lambda) {
        sender.sendMessage("Der Command ist nur für Spieler")
    }

    override fun isPlayer(sender: CommandSender, onSuccess: (Player) -> Unit, onFail: () -> Unit) =
            if (sender is Player) onSuccess(sender) else onFail()


}



