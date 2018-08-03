/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson.BukkitGsonConfig
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 05:37.
 * Last edit 03.08.2018
 */
class NoFallDamageModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("NoFallDamageModule", "1.0", "Lars Artmann | LartyHD", "")
    private val config = BukkitGsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load()
    private val activeWorlds = config.getAs<JsonArray>("activeWorlds")
    private val radius = config.getAs<JsonPrimitive>("radius")?.asInt
    private var location: Location? = if (radius == null) null else config.getLocationWithOutYawAndPitch("center")

    @EventHandler
    fun onEntityDamageEvent(event: EntityDamageEvent) {
        if (event.isCancelled || event.cause != EntityDamageEvent.DamageCause.FALL) return
        var boolean = false
        if (radius != null && location != null && event.entity.location.world == location?.world) {
            if (event.entity.location.distance(location) <= radius) isInAActiveWorld(event.entity.world.name) { boolean = true }
        } else
            isInAActiveWorld(event.entity.world.name) { boolean = true }
        if (boolean) cancel(event)
    }

    private fun isInAActiveWorld(name: String, lambda: () -> Unit) =
            if (activeWorlds != null) HashSet<String>().apply { activeWorlds.forEach { add(it.asString) } }.forEach { if (name == it) lambda() } else lambda()

}