/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import org.bukkit.Bukkit
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 05:20.
 * Last edit 03.08.2018
 */
class WorldDeleteModule : Module {

    override val description: ModuleDescription = ModuleDescription("WorldDeleteModule", "1.0", "Lars Artmann | LartyHD", "")
    private val config = GsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load()
    private val worlds = HashSet<String>().apply {
        (config.getAs<JsonArray>("worlds")
                ?: throw NullPointerException("worlds can not be null")).forEach { add(it.asString) }
    }

    override fun stop() = worlds.forEach {
        val world = Bukkit.getWorld(it) ?: throw NullPointerException("world can not be null")
        deleteWorld(world.worldFolder)
    }

    private fun deleteWorld(path: File) {
        if (path.exists()) {
            val files = path.listFiles() ?: throw NullPointerException("files can not be null")
            files.indices.forEach { if (files[it].isDirectory) deleteWorld(files[it]) else files[it].delete() }
        }
        path.delete()
    }

}