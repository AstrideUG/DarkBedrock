import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toWorlds
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 13:56.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class BlockPhysicalInteraction : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription =
        ModuleDescription(javaClass.canonicalName, "1.0", "Lars Artmann | LartyHD", "")

    private lateinit var worlds: List<String>

    override fun load() {
        worlds = try {
            GsonService.loadAsJsonArray(ConfigData(description.folder, "worlds.json"))
                .mapNotNull { it.asJsonPrimitive.asString }
        } catch (ex: ClassCastException) {
            emptyList()
        }
    }

    @EventHandler
    fun on(event: PlayerInteractEvent) {
        val worlds = worlds.toWorlds()
        if (event.action != Action.PHYSICAL) return
        if (!worlds.isEmpty() && !worlds.contains(event.player.world)) return
        cancel(event)
    }

}