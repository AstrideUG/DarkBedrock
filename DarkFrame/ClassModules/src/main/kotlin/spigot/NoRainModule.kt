import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.weather.WeatherChangeEvent
import org.bukkit.event.world.WorldLoadEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 11:17.
 * Last edit 05.07.2018
 */
class NoRainModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("NoRainModule", "1.1.1", "Lars Artmann | LartyHD", "This modules block rain")

    override fun init() = Bukkit.getWorlds().forEach(this::clearWeather)

    @EventHandler
    fun onWeatherChangeEvent(event: WeatherChangeEvent) = cancel(event, event.toWeatherState())

    @EventHandler
    fun onWorldLoadEvent(event: WorldLoadEvent) = clearWeather(event.world)

    private fun clearWeather(world: World) {
        world.setStorm(false)
        world.isThundering = false
    }
}
