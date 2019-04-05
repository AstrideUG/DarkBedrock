package net.darkdevelopers.darkbedrock.darkness.spigot.commands

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService.loadAs
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs
import net.darkdevelopers.darkbedrock.darkness.general.functions.getOrKey
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 06:09.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
abstract class SimplePermissionsCommandModule(protected val defaultCommandName: String) : Module {

    override val description: ModuleDescription = ModuleDescription(
        javaClass.canonicalName,
        "1.0",
        "Lars Artmann | LartyHD",
        "Added a ${defaultCommandName.toLowerCase()} command"
    )

    protected lateinit var config: Config
    protected val prefix get() = "Modules.Command.$defaultCommandName"
    protected abstract val command: () -> PermissionCommand
    protected lateinit var commandInstance: PermissionCommand
    protected val singlePerms
        get() = config.permissions
            .map { it.key to it.value.joinToString(";") }
            .toMap()
            .getOrKey(prefix)

    override fun load() {
        config = Config()
    }

    override fun start() {
        commandInstance = command()
    }

    protected abstract inner class PermissionCommand(
        javaPlugin: JavaPlugin,
        name: String = config.commandName,
        permission: String = singlePerms,
        usage: String = "",
        minLength: Int = 0,
        maxLength: Int = 0,
        tabCompleter: TabCompleter? = null,
        vararg aliases: String = config.aliases
    ) : Command(
        javaPlugin,
        name,
        permission,
        usage = usage,
        minLength = minLength,
        maxLength = maxLength,
        tabCompleter = tabCompleter,
        aliases = *aliases
    )

    protected inner class Config {
        val configData = ConfigData(description.folder, "config.json")
        val jsonObject = loadAs(configData) ?: JsonObject()
        val messages = SpigotGsonMessages(GsonConfig(configData).load()).availableMessages
        val permissions = GsonStringMapWithSubs(jsonObject["permissions"]?.asJsonObject ?: JsonObject()).available
        val commandName = jsonObject["command-name"]?.asString ?: defaultCommandName
        val aliases: Array<out String> =
            jsonObject["aliases"]?.asJsonArray?.map { it.asString }?.toTypedArray() ?: emptyArray()
    }


}