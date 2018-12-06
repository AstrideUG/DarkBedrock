
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerLoginEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 19:24.
 * Last edit 02.12.2018
 */
class OnlyProxyJoinModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription(
        javaClass.simpleName,
        "1.0.1",
        "Lars Artmann | LartyHD",
        "This module asks if the host address is valid"
    )

    private val config by lazy { GsonService.loadAsJsonObject(ConfigData(description.folder, "config.json")) }
    private val proxies by lazy { config["proxies"].asJsonArray }
    private val ips by lazy { mutableSetOf<String>().apply { proxies.forEach { add(it.asString) } }.toSet() }
    private val kickMessage by lazy { config["KickMessage"].asJsonPrimitive.asString }

	@EventHandler(priority = EventPriority.HIGHEST)
	fun onPlayerLoginEvent(event: PlayerLoginEvent) {
        if (!ips.any { it.equals(event.realAddress.hostAddress, ignoreCase = true) })
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessage)
	}

}