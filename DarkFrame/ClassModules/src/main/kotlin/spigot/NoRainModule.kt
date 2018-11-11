import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
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
 * Last edit 26.08.2018
 */
class NoRainModule : Module, Listener(DarkFrame.instance) {

	override val description: ModuleDescription = ModuleDescription("NoRainModule", "1.2", "Lars Artmann | LartyHD", "This modules block rain")

	private lateinit var config: GsonConfig

	override fun load() {
		config = GsonConfig(ConfigData(description.folder)).load()
	}

	override fun start() {
		val worldsWhitelistActive = config.getAsNotNull<JsonPrimitive>("WorldsWhitelistActive").asBoolean
		if (worldsWhitelistActive) {
			val rawWorlds = config.getAsNotNull<JsonArray>("Worlds")
			val worlds = mutableSetOf<World>().apply { rawWorlds.forEach { Bukkit.getWorld(it.asString) } }.toSet()
			worlds.forEach(this::clearWeather)
		} else Bukkit.getWorlds().forEach(this::clearWeather)
	}

	@EventHandler
	fun onWeatherChangeEvent(event: WeatherChangeEvent) = cancel(event, event.toWeatherState())

	@EventHandler
	fun onWorldLoadEvent(event: WorldLoadEvent) = clearWeather(event.world)

	private fun clearWeather(world: World) {
		world.setStorm(false)
		world.isThundering = false
	}

}
