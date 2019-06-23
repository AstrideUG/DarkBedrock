/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.bungee.commands

import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages.PREFIX
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ModuleManager
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 02:52.
 * Last edit 04.07.2018
 */
class ModulesCommand(private val moduleManagers: Map<String, ModuleManager>) : Command(
    commandName = "BungeeModules",
    permission = "darkbedrock.darkframe.bungee.commands.modules",
    usage = "[ModuleName]",
    minLength = 0,
    maxLength = 1,
    aliases = *arrayOf("BModules")
) {
    override fun perform(sender: CommandSender, args: Array<String>) = if (args.isEmpty()) sendListOfModules(sender)
    else {
        val module = getModule(args[0])
        if (module == null) sendUseMessage(sender) else sendInfosOfModule(sender, module)
    }

    private fun sendInfosOfModule(sender: CommandSender, module: Module) {
        val description = module.description
        sender.run {
            sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
            sendMessage(TextComponent("$PREFIX${SECONDARY}Infos of module $EXTRA${description.name}$IMPORTANT:"))
            sendMessage(TextComponent("$PREFIX${SECONDARY}Version$IMPORTANT: $SECONDARY$EXTRA${description.version}"))
            sendMessage(TextComponent("$PREFIX${SECONDARY}Author$IMPORTANT: $SECONDARY$EXTRA${description.author}"))
            sendMessage(TextComponent("$PREFIX${SECONDARY}Description$IMPORTANT: $SECONDARY$EXTRA${description.description}"))
            sendMessage(TextComponent("$PREFIX${SECONDARY}Async$IMPORTANT: $SECONDARY$EXTRA${if (description.async) "enable" else "disabled"}"))
            sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
        }
    }

    private fun sendListOfModules(sender: CommandSender) {
        sender.sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
        for (key in moduleManagers.keys) {
            sender.sendMessage(TextComponent("${PREFIX}${SECONDARY}Modules from ModuleManager $key$IMPORTANT:"))
            moduleManagers[key]?.modules?.forEach { sender.sendMessage(TextComponent("$PREFIX$PRIMARY${it.javaClass.simpleName}")) }
        }
        sender.sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
    }

    private fun getModule(moduleName: String): Module? {
        for (value in moduleManagers.values) {
            val module = value.getModule(moduleName)
            if (module != null) return module
        }
        return null
    }
}