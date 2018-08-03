/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson.BukkitGsonConfig
import org.bukkit.command.CommandSender
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 02:07.
 * Last edit 03.08.2018
 */
class SpawnCommandModule : Module, Command(
        DarkFrame.instance,
        GsonConfig(ConfigData("modules${File.separator}SpawnCommandModule", "config.json")).load().getAs<JsonPrimitive>("CommandName")?.asString
                ?: throw NullPointerException("CommandName can not be null")
) {
    override val description: ModuleDescription = ModuleDescription("SpawnCommandModule", "1.0", "Lars Artmann | LartyHD", "")

    private val location = BukkitGsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load().getLocation("SpawnLocation")

//    init {
//        BukkitGsonConfig(ConfigData("SpawnCommandModule", "test.json")).setLocation("SpawnLocation", Location(Bukkit.getWorlds()[0], 0.0, 0.0, 0.0, 0F, 0F)).save()
//    }

    override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) { it.teleport(location) }

}