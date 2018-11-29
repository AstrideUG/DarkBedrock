/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonPrimitive
import com.google.inject.Inject
import com.velocitypowered.api.command.Command
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.ServerConnectedEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.util.title.Titles
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.kyori.text.TextComponent
import net.kyori.text.event.ClickEvent
import net.kyori.text.format.TextColor
import java.io.File
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.11.2018 13:15.
 * Current Version: 1.0 (28.11.2018 - 28.11.2018)
 */
@Plugin(
    id = "moveplugin",
    name = "MovePlugin",
    version = "1.0-SNAPSHOT",
    authors = ["Lars Artmann | LartyHD"]
)
class MovePlugin @Inject private constructor(
    private val proxy: ProxyServer,
    @DataDirectory private val dataFolder: Path
) {

    private val config = GsonService.loadAsJsonObject(ConfigData(dataFolder.toFile(), "config.json"))
    private val eula = config["EULA"]?.asString.toNonNull("EULA")
    private val array = config["ConnectionServers"]?.asJsonArray.toNonNull("ConnectionServers")

    init {
        proxy.commandManager.register(Command { source, _ ->
            if (source is Player) GlobalScope.launch {
                GsonService.save(
                    ConfigData(
                        "${dataFolder.toFile()}${File.separator}eula",
                        "${source.uniqueId}.json"
                    ), JsonPrimitive(true)
                )
                sendPlayer(source)
            }
            else source.sendMessage(TextComponent.of("Only Player"))
        }, "accepteula")
    }


    @Subscribe
    fun on(event: ServerConnectedEvent) = GlobalScope.launch {
        val player = event.player
        player.sendTitle(
            Titles
                .text()
                .title(TextComponent.of("Loading data...").color(TextColor.BLUE))
                .fadeIn(1)
                .stay(Int.MAX_VALUE)
                .fadeOut(1)
                .resetBeforeSend(true)
                .build()
        )
        val load = try {
            GsonService.load(
                ConfigData(
                    "${dataFolder.toFile()}${File.separator}eula",
                    "${player.uniqueId}.json"
                )
            ).asBoolean
        } catch (ex: Exception) {
            player.sendTitle(
                Titles
                    .text()
                    .title(TextComponent.of("Running in a error").color(TextColor.DARK_RED))
                    .subtitle(TextComponent.of("pls accept the eula").color(TextColor.RED))
                    .fadeIn(1)
                    .stay(Int.MAX_VALUE)
                    .fadeOut(1)
                    .resetBeforeSend(true)
                    .build()
            )
            null
        }
        if (load == null) {
            player
            player.sendMessage(TextComponent.of(""))
            player.sendMessage(
                TextComponent
                    .of("Accept the eula click or /accepteula ")
                    .color(TextColor.GREEN)
                    .clickEvent(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/accepteula"))
                    .append(
                        TextComponent
                            .of("[URL]")
                            .clickEvent(ClickEvent(ClickEvent.Action.OPEN_URL, eula))
                            .color(TextColor.WHITE)
                    )
            )
            player.sendMessage(TextComponent.of(""))
        } else sendPlayer(player)
    }

    private suspend fun sendPlayer(player: Player) {
        delay(100)
        array.forEach { element ->
            val registeredServer = proxy.getServer(element.asString).orElse(null) ?: return@forEach
            val currentServer = player.currentServer.orElse(null) ?: return@forEach
            if (array.firstOrNull { it == currentServer } != null) return@forEach
            val join = player.createConnectionRequest(registeredServer).connect().join()
            if (join.status != ConnectionRequestBuilder.Status.SERVER_DISCONNECTED
                && join.status != ConnectionRequestBuilder.Status.CONNECTION_CANCELLED
            ) return@sendPlayer
        }
        this@MovePlugin.sendPlayer(player)
    }

}
