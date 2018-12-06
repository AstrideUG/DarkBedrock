/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.FoodLevelChangeEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.12.2018 21:28.
 * Current Version: 1.0 (02.12.2018 - 02.12.2018)
 */
class NoHungerModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription =
        ModuleDescription(javaClass.simpleName, "1.0", "Lars Artmann | LartyHD", "")

    private val worlds by lazy {
        try {
            GsonService.loadAsJsonArray(ConfigData(description.folder, "worlds.json"))
                .mapNotNull { it.asJsonPrimitive.asString }
        } catch (ex: ClassCastException) {
            emptyList<String>()
        }
    }

    @EventHandler
    fun on(event: FoodLevelChangeEvent) {
        val worlds = worlds.mapNotNull {
            try {
                Bukkit.getWorld(it)
            } catch (ex: Exception) {
                null
            }
        }
        if (worlds.isEmpty() || worlds.contains(event.entity.world)) cancel(event)
    }
}