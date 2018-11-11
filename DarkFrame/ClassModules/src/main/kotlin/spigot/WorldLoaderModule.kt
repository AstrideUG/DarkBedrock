/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 04:20.
 * Last edit 03.08.2018
 */
class WorldLoaderModule : Module {

	override val description: ModuleDescription = ModuleDescription("WorldLoaderModule", "1.0", "Lars Artmann | LartyHD", "")
	private val config = GsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load()
	private val worlds = HashSet<String>().apply {
		(config.getAs<JsonArray>("worlds")
				?: throw NullPointerException("worlds can not be null")).forEach { add(it.asString) }
	}

	override fun start() = worlds.forEach { if (Bukkit.getWorld(it) == null) Bukkit.createWorld(WorldCreator(it)) }

}