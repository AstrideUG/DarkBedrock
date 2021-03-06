/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.commands

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 02:52.
 *
 * Change the name to OldModulesCommand at 19.12.2018
 *
 * Last edit 04.07.2018
 */
class OldModulesCommand(javaPlugin: JavaPlugin, private val moduleManagers: Map<String, ModuleManager>) : Command(
    javaPlugin,
    "OldModules",
    permission = "darkbedrock.darkframe.spigot.commands.modules",
    usage = "[ModuleName]",
    minLength = 0,
    maxLength = 1,
    tabCompleter = TabCompleter { _, _, _, args ->
        if (args.size == 1) getModules(
            moduleManagers
        ) else emptyList()
    }
) {
    override fun perform(sender: CommandSender, args: Array<String>) = if (args.isEmpty()) sendListOfModules(sender)
    else {
        val module = getModule(args[0])
        if (module == null) sendUseMessage(sender) else sendInfosOfModule(sender, module)
    }

    private fun sendInfosOfModule(sender: CommandSender, module: Module) {
        val description = module.description
        sender.run {
            sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
            sendMessage("${messages.prefix}${SECONDARY}Infos of module $EXTRA${description.name}$IMPORTANT:")
            sendMessage("${messages.prefix}${SECONDARY}Version$IMPORTANT: $SECONDARY$EXTRA${description.version}")
            sendMessage("${messages.prefix}${SECONDARY}Author$IMPORTANT: $SECONDARY$EXTRA${description.author}")
            sendMessage("${messages.prefix}${SECONDARY}Description$IMPORTANT: $SECONDARY$EXTRA${description.description}")
            sendMessage("${messages.prefix}${SECONDARY}Async$IMPORTANT: $SECONDARY$EXTRA${if (description.async) "enable" else "disabled"}")
            sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
        }
    }

    private fun sendListOfModules(sender: CommandSender) {
        sender.sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
        for (key in moduleManagers.keys) {
            sender.sendMessage("${messages.prefix}${SECONDARY}Modules from ModuleManager $key$IMPORTANT:")
            moduleManagers[key]?.modules?.forEach { sender.sendMessage("${messages.prefix}$PRIMARY${it.javaClass.simpleName}") }
        }
        sender.sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
    }

    private fun getModule(moduleName: String): Module? {
        for (value in moduleManagers.values) {
            val module = value.getModule(moduleName)
            if (module != null) return module
        }
        return null
    }

    companion object {
        fun getModules(moduleManagers: Map<String, ModuleManager>): List<String> =
            moduleManagers.flatMap { it.value.modules }.map { it.description.name }.toSet().toList()
    }
}