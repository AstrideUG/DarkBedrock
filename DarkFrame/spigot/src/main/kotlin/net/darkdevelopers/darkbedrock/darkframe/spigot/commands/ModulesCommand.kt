/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.commands

import de.astride.darkbedrock.apis.modules.api.events.ModuleReloadEvent
import de.astride.darkbedrock.apis.modules.api.events.ModulesReloadedEvent
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import de.astride.darkbedrock.apis.modules.common.loader.ModuleLoader
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.12.2018 20:54.
 * Current Version: 1.0 (19.12.2018 - 19.12.2018)
 */
class ModulesCommand(
    javaPlugin: JavaPlugin,
    private val loaders: Set<ModuleLoader>,
    private val messages: Map<String, String?>
) : Command(
    javaPlugin,
    "OldModules",
    permission = "darkbedrock.darkframe.spigot.commands.modules",
    usage = "reload [ModuleName]:DarkFrame.Command.Modules.Reload|[ModuleName]",
    minLength = 0,
    maxLength = 2,
    tabCompleter = TabCompleter { _, _, _, args ->
        if (args.size == 1) getModules(
            loaders
        ) else emptyList()
    }
) {
    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 19.12.2018 21:06.
     * Current Version: 1.0 (19.12.2018 - 19.12.2018)
     */
    override fun perform(sender: CommandSender, args: Array<String>) {
        when {
            args.isEmpty() -> sendListOfModules(sender)
            args[0] == "reload" && hasPermission(sender, "DarkFrame.Command.Modules.Reload") -> if (args.size == 1)
                loaders.forEach { loader ->
                    messages["DarkFrame.Command.Modules.Reload.All.Running"]?.apply { sender.sendMessage(this) }
                    loader.modules.forEach { reloadModules(sender, it.description.name) }
                    loader.eventManager.fire(ModulesReloadedEvent(loader.modules))
                    messages["DarkFrame.Command.Modules.Reload.All.Finished"]?.apply { sender.sendMessage(this) }
                } else reloadModules(sender, args[1])
            args.size == 2 -> sendUseMessage(sender)
            else -> getModule(args[0])?.apply { sendInfosOfModule(sender, this) } ?: sendUseMessage(sender)
        }
    }

    private fun sendInfosOfModule(sender: CommandSender, module: ModuleDescription) {
        sender.run {
            fun message(arg1: String, arg2: String) =
                sendMessage("${configService.prefix}$SECONDARY$arg1$IMPORTANT: $SECONDARY$EXTRA$arg2")
            sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")

            message("Infos of module $EXTRA${module.name}", "")
            message("ID", module.id)
            message("Version", module.version)
            message("Authors", module.authors.toString())
            message("URL", module.url)
            message("Description", module.description)
            message("Dependencies", module.dependencies.toString())

            sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")
        }
    }

    private fun sendListOfModules(sender: CommandSender) {
        sender.design()
        loaders.forEach { loader ->
            sender.sendMessage("${configService.prefix}${SECONDARY}Modules from ModuleLoader ${loader.type}$IMPORTANT:")
            loader.modules.forEach { sender.sendMessage("${configService.prefix}$PRIMARY${it.description.name}") }
        }
        sender.design()
    }

    private fun getModule(moduleName: String): ModuleDescription? {
        loaders.forEach { it.getModule(moduleName)?.apply { return description } }
        return null
    }

    private fun reloadModules(sender: CommandSender, name: String) = loaders.forEach { loader ->
        messages["DarkFrame.Command.Modules.Reload.One.Running"]?.apply { sender.sendMessage(this) }
        loader.eventManager.fire(ModuleReloadEvent(loader.getModule(name) ?: return@forEach))
        messages["DarkFrame.Command.Modules.Reload.One.Finished"]?.apply { sender.sendMessage(this) }
    }

    companion object {
        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 19.12.2018 21:13.
         * Current Version: 1.0 (19.12.2018 - 19.12.2018)
         */
        fun getModules(loaders: Set<ModuleLoader>): List<String> =
            loaders.flatMap { loader -> loader.modules.map { it.description.name } }
    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 19.12.2018 21:08.
     * Current Version: 1.0 (19.12.2018 - 19.12.2018)
     */
    private fun CommandSender.design() =
        sendMessage("$PRIMARY$EXTRA$DESIGN                                                               ")

}
