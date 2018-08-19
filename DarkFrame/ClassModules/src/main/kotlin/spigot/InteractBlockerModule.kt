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
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.08.2018 02:05.
 * Last edit 19.08.2018
 */
class InteractBlockerModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("InteractBlockerModule", "1.0", "Lars Artmann | LartyHD", "")
    private val blocked = mutableSetOf<Material>()

    override fun start() {
        val config = BukkitGsonConfig(ConfigData(description.folder)).load()
        config.getAsNotNull<JsonArray>("blockedTypes").forEach { blocked.add(Material.getMaterial((it as JsonPrimitive).asString)) }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) = blocked.forEach {
        if (event.hasBlock() && event.clickedBlock?.type == it && !hasPermission(event.player, "InteractBlocker.bypass") && !hasPermission(event.player, "InteractBlocker.bypass.$it")) {
            cancel(event)
            event.player.sendMessage("${ChatColor.RED}Du darfst mit ${ChatColor.DARK_RED}$it ${ChatColor.RED}interagieren")
        }
    }


}