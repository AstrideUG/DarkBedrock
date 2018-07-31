package net.darkdevelopers.darkbedrock.darkframe.spigot.commands

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 02:52.
 * Last edit 04.07.2018
 */
class ModulesCommand(javaPlugin: JavaPlugin, private val moduleManagers: Map<String, ModuleManager>) : Command(
        javaPlugin,
        "Modules",
        permission = "darkbedrock.darkframe.spigot.commands.modules",
        usage = "[ModuleName]",
        minLength = 0,
        maxLength = 1,
        tabCompleter = TabCompleter { _, _, _, args ->
            if (args.size == 1) getModules(moduleManagers) else emptyList()
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
            sendMessage("${Messages.PREFIX}${SECONDARY}Infos of module $EXTRA${description.name}$IMPORTANT:")
            sendMessage("${Messages.PREFIX}${SECONDARY}Version$IMPORTANT: $SECONDARY$EXTRA${description.version}")
            sendMessage("${Messages.PREFIX}${SECONDARY}Author$IMPORTANT: $SECONDARY$EXTRA${description.author}")
            sendMessage("${Messages.PREFIX}${SECONDARY}Description$IMPORTANT: $SECONDARY$EXTRA${description.description}")
            sendMessage("${Messages.PREFIX}${SECONDARY}Async$IMPORTANT: $SECONDARY$EXTRA${if (description.async) "enable" else "disabled"}")
            sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
        }
    }

    private fun sendListOfModules(sender: CommandSender) {
        sender.sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
        for (key in moduleManagers.keys) {
            sender.sendMessage("${Messages.PREFIX}${SECONDARY}Modules from ModuleManager $key$IMPORTANT:")
            moduleManagers[key]?.modules?.forEach { sender.sendMessage("${Messages.PREFIX}$PRIMARY${it.javaClass.simpleName}") }
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
        fun getModules(moduleManagers: Map<String, ModuleManager>): List<String> {
            val modules = HashSet<Module>()
            moduleManagers.values.forEach { modules.addAll(it.modules) }
            return modules.map { it.description.name }.toSet().toList()
        }
    }
}