import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerLoginEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 19:24.
 * Last edit 07.07.2018
 */
class OnlyProxyJoinModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("OnlyProxyJoinModule", "1.0", "Lars Artmann | LartyHD", "This module asks if the host address is valid")

    private val proxys: Set<String> = GsonConfig(ConfigData(description.folder, "config.json")).load().getAs<Array<String>>("proxys")?.toSet()
            ?: throw NullPointerException("proxys can not be null")

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerLoginEvent(event: PlayerLoginEvent) {
        if (!check(event.realAddress.hostAddress)) event.disallow(
                PlayerLoginEvent.Result.KICK_OTHER,
                "${TEXT}Bitte joine über ${Messages.SERVER_NAME}"
        )
    }

    private fun check(ip: String): Boolean {
        proxys.forEach { if (it.equals(ip, ignoreCase = true)) return true }
        return false
    }

}