import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toGameMode
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toWorlds
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 14:40.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class GameModeByWorld : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription =
        ModuleDescription(javaClass.canonicalName, "1.0", "Lars Artmann | LartyHD", "")

    private lateinit var worlds: Map<String, GameMode>

    override fun load() {
        worlds = try {
            @Suppress("UNCHECKED_CAST")
            GsonService.loadAsJsonObject(ConfigData(description.folder, "worlds.json")).entrySet()
                .associate { (key: String, value) ->
                    try {
                        key to value.asString?.toGameMode()
                    } catch (ex: ClassCastException) {
                        key to null
                    }
                }.filterValues { it != null } as Map<String, GameMode>
        } catch (ex: ClassCastException) {
            emptyMap()
        }
    }

    @EventHandler
    fun on(event: PlayerJoinEvent) = event.player.changeGameMode()

    @EventHandler
    fun on(event: PlayerChangedWorldEvent) = event.player.changeGameMode()

    private fun Player.changeGameMode() {
        if (worlds.isNotEmpty() && world !in worlds.keys.toWorlds()) return
        gameMode = worlds[world.name] ?: return
    }

}