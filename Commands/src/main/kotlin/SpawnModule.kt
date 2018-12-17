/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.inject.Inject
import de.astride.darkbedrock.apis.annotatedcommands.api.*
import de.astride.darkbedrock.apis.events.api.Subscribe
import de.astride.darkbedrock.apis.modules.api.annotations.DataDirectory
import de.astride.darkbedrock.apis.modules.api.annotations.Module
import de.astride.darkbedrock.apis.modules.api.events.ModuleReloadEvent
import de.astride.darkbedrock.apis.modules.api.events.ModulesLoadedEvent
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import java.nio.file.Path


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 18:33.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
@Module(
    "d859edbd-4b75-40e7-80b7-593df72b3a5f",
    "SpawnModule",
    "0.1.0",
    ["Lars Artmann | LartyHD"],
    "A Module for Spawn",
    "astride.de"
)
class SpawnModule @Inject private constructor(@DataDirectory private val path: Path) {

    private lateinit var commandManager: CommandManager

    private var config = Config()
    private var location = config.getLocation()

    private inner class Config {
        val configData = ConfigData(path.toFile(), "config.json")
        val jsonObject = GsonService.loadAsJsonObject(configData)
        val spawn = GsonConfig.multiPlaceJsonObject(jsonObject["Spawn"], "Spawn", configData.directory)
        val messages = SpigotGsonMessages(GsonConfig(configData)).availableMessages

        fun saveLocation(location: Location) {
            spawn.addProperty("World", location.world.name)
            spawn.addProperty("X", location.x)
            spawn.addProperty("Y", location.y)
            spawn.addProperty("Z", location.z)
            spawn.addProperty("Yaw", location.yaw)
            spawn.addProperty("Pitch", location.pitch)
        }

        fun getLocation() = Location(
            Bukkit.getWorld(spawn["World"].asString),
            spawn["X"].asDouble,
            spawn["Y"].asDouble,
            spawn["Z"].asDouble,
            spawn["Yaw"].asFloat,
            spawn["Pitch"].asFloat
        )

    }


    @Subscribe
    fun on(event: ModulesLoadedEvent) {

        commandManager.commands += SpawnCommand::class.java
        commandManager.commands += SetSpawnCommand::class.java

        // TODO REGISTER COMMANDS
    }

    @Subscribe
    fun on(event: ModuleReloadEvent) {
        if (event.container.instance === this) config = Config()
    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 06.12.2018 19:54.
     * Current Version: 1.0 (06.12.2018 - 06.12.2018)
     */
    @Command("Spawn")
    @Permission("SpawnModule.commands.<CommandName>")
    private inner class SpawnCommand @Inject private constructor(@Sender private val player: Player) : Any() {

        @CommandOnly
        private fun execute() {
            player.teleport(location)
        }

    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 06.12.2018 19:54.
     * Current Version: 1.0 (06.12.2018 - 06.12.2018)
     */
    @Command("SetSpawn")
    @Permission("SpawnModule.commands.<CommandName>")
    private inner class SetSpawnCommand @Inject private constructor(@Sender private val player: Player) {

        @CommandOnly
        private fun execute() {

            fun Float.round() = (this * 100).toInt() / 100F

            val cloned = player.location.clone()
            val location = Location(
                cloned.world,
                cloned.blockX + 0.5,
                cloned.y,
                cloned.blockZ + 0.5,
                cloned.yaw.round(),
                cloned.pitch.round()
            )

            config.saveLocation(location)
            this@SpawnModule.location = location

            player.sendMessage(config.messages["SetSpawnSuccessfully"])
        }

    }

}