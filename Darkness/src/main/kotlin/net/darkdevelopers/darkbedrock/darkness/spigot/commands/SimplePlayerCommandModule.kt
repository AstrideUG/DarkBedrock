package net.darkdevelopers.darkbedrock.darkness.spigot.commands

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:14.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs
import net.darkdevelopers.darkbedrock.darkness.general.functions.getOrKey
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.possiblePlayer
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
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
abstract class SimplePlayerCommandModule(protected val defaultCommandName: String) : Module {

    override val description: ModuleDescription = ModuleDescription(
        javaClass.canonicalName,
        "1.0",
        "Lars Artmann | LartyHD",
        "Added a ${defaultCommandName.toLowerCase()} command"
    )

    private lateinit var config: Config
    private val prefix get() = "${description.name}.Command.$defaultCommandName"
    protected abstract val command: () -> PlayerCommand

    override fun load() {
        config = Config()
    }

    override fun start() {
        command()
    }

    protected inner class PlayerCommand(
        javaPlugin: JavaPlugin,
        name: String = config.commandName,
        permission: String = config.permissions.getOrKey(prefix),
        usage: String = "[Player]:${config.permissions.getOrKey("$prefix.Other")}",
        tabCompleter: TabCompleter = TabCompleter { _, _, _, args -> if (args.isEmpty()) Utils.getPlayers().map { it.name } else listOf<String>() }
    ) : Command(
        javaPlugin,
        name,
        permission,
        usage = usage,
        maxLength = 1,
        tabCompleter = tabCompleter
    ) {

        override fun perform(sender: CommandSender, args: Array<String>) =
            possiblePlayer(config.messages, prefix, sender, args[0]) { this.execute(it) }

    }

    protected abstract fun CommandSender.execute(target: Player)

    protected inner class Config {
        val configData = ConfigData(description.folder, "config.json")
        val jsonObject = GsonService.loadAsJsonObject(configData)
        val messages = SpigotGsonMessages(GsonConfig(configData).load()).availableMessages
        val permissions = GsonStringMapWithSubs(jsonObject["permissions"]?.asJsonObject ?: JsonObject()).available
        val commandName = jsonObject["command-name"]?.asString ?: defaultCommandName
    }


}