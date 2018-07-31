import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.weather.WeatherChangeEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 11:17.
 * Last edit 05.07.2018
 */
class NoRainModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("NoRainModule", "1.0.2", "Lars Artmann | LartyHD", "This modules block rain")

    override fun init() {
        for (world in Bukkit.getWorlds()) {
            world ?: continue
            world.setStorm(false)
            world.isThundering = false
        }
    }

    @EventHandler
    fun onWeatherChangeEvent(event: WeatherChangeEvent) = cancel(event)
}
