import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs
import net.darkdevelopers.darkbedrock.darkness.general.functions.getNotNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendIfNotNull
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.isPlayer
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 03:00.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class FeedModule : Module {

    override val description: ModuleDescription =
        ModuleDescription(javaClass.canonicalName, "1.0", "Lars Artmann | LartyHD", "Added a feed command")

    private lateinit var config: Config
    private val prefix = "${description.name}.Command.$defaultCommandName"

    override fun load() {
        config = Config()
    }

    override fun start() {
        FeedCommand()
    }

    private inner class FeedCommand : Command(
        DarkFrame.instance,
        config.commandName,
        config.permissions.getNotNull(prefix),
        usage = "[Player]:${config.permissions.getNotNull("$prefix.Other")}",
        maxLength = 1,
        tabCompleter = TabCompleter { _, _, _, args -> if (args.isEmpty()) Utils.getPlayers().map { it.name } else listOf<String>() }
    ) {

        override fun perform(sender: CommandSender, args: Array<String>) {
            val messages = config.messages
            if (args.isEmpty()) sender.isPlayer {
                messages["$prefix.Success"].sendIfNotNull(sender)
                it.feed()
                messages["$prefix.Successfully"].sendIfNotNull(sender)
            } else {
                val player: Player? = try {
                    Bukkit.getPlayer(UUID.fromString(args[0]))
                } catch (ex: IllegalArgumentException) {
                    Bukkit.getPlayer(args[0])
                }
                getTarget(sender, player) {
                    messages["$prefix.Other.Sender.Success"].sendIfNotNull(sender)
                    messages["$prefix.Other.Target.Success"].sendIfNotNull(it)
                    it.feed()
                    messages["$prefix.Other.Sender.Successfully"].sendIfNotNull(sender)
                    messages["$prefix.Other.Target.Successfully"].sendIfNotNull(it)
                }
            }
        }

    }

    private inner class Config {
        val configData = ConfigData(description.folder, "config.json")
        val jsonObject = GsonService.loadAsJsonObject(configData)
        val messages = SpigotGsonMessages(GsonConfig(configData).load()).availableMessages
        val permissions = GsonStringMapWithSubs(jsonObject["permissions"]?.asJsonObject ?: JsonObject()).available
        val commandName = jsonObject["command-name"]?.asString ?: defaultCommandName
    }

    companion object {

        const val defaultCommandName = "Feed"

        private fun Player.feed() {
            foodLevel = 20
            saturation = 20F
        }

    }


}