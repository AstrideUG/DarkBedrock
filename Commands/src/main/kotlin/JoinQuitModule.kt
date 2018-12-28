/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.12.2018 09:42.
 * Current Version: 1.0 (28.12.2018 - 28.12.2018)
 */
class JoinQuitModule : Module, Listener(DarkFrame.instance) {

    private lateinit var config: Config

    override val description: ModuleDescription = ModuleDescription(
        javaClass.canonicalName,
        "1.0-SNAPSHOT",
        "Lars Artmann | LartyHD and DevSnox",
        "Join and Quit messages!"
    )

    override fun load() {
        config = Config()
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage = config.messages["Join"]?.replace("<Player>", event.player.name, true)
    }

    @EventHandler
    fun onQuit(event: PlayerDisconnectEvent) {
        event.leaveMessage = config.messages["Disconnect"]?.replace("<Player>", event.player.name, true)
    }

    private inner class Config {
        val configData = ConfigData(description.folder, "config.json")
        val messages = SpigotGsonMessages(GsonConfig(configData).load()).availableMessages
    }
}