/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonPrimitive
import com.google.inject.Inject
import com.velocitypowered.api.command.Command
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PostLoginEvent
import com.velocitypowered.api.event.player.KickedFromServerEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.util.MessagePosition
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.kyori.text.TextComponent
import net.kyori.text.event.ClickEvent
import net.kyori.text.format.TextColor
import org.slf4j.Logger
import java.io.File
import java.nio.file.Path
import java.util.*
import java.util.concurrent.TimeoutException
import kotlin.concurrent.thread

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
    private val logger: Logger,
    @DataDirectory private val dataFolder: Path
) {

    private val config = GsonService.loadAsJsonObject(ConfigData(dataFolder.toFile(), "config.json"))
    private val eula = config["EULA"]?.asString.toNonNull("EULA")
    private val array = config["ConnectionServers"]?.asJsonArray.toNonNull("ConnectionServers")
    private val array1 = config["ByServers"]?.asJsonArray.toNonNull("ByServers").map { it.asString }
    private val usedEula = arrayOf<UUID>()
    private val connection = mutableSetOf<UUID>()
    private val trys = mutableMapOf<Player, Int>()

    private var isRunning = true

    init {
        proxy.commandManager.register(Command { source, _ ->
            when {
                source !is Player -> source.sendMessage(TextComponent.of("Only Player"))
                usedEula.any { source.uniqueId == it } -> source.sendMessage(
                    TextComponent.of("You have already accept the EULA", TextColor.AQUA)
                )
                !array1.any { source.currentServer.orElse(null)?.serverInfo?.name == it } -> source.sendMessage(
                    TextComponent.of("You must on a fallback server", TextColor.RED)
                )
                else -> thread {
                    GsonService.save(
                        ConfigData(
                            "${dataFolder.toFile()}${File.separator}eula",
                            "${source.uniqueId}.json"
                        ), JsonPrimitive(true)
                    )
                    sendPlayer(source)
                }
            }
        }, "accepteula")
        thread {
            while (isRunning) {
                Thread.sleep(1500)
                sendPlayers()
            }
        }
    }

    @Subscribe
    fun on(event: ProxyShutdownEvent) {
        isRunning = false
    }

    @Subscribe
    fun on(event: KickedFromServerEvent) = try {
        event.result = KickedFromServerEvent.RedirectPlayer.create(proxy.getServer(array1[0]).get())
        event.player.sendMessage(
            event.originalReason.orElse(TextComponent.of("Connection error", TextColor.RED)),
            MessagePosition.ACTION_BAR
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    @Subscribe
    fun on(event: PostLoginEvent) {
        val player = event.player
        val configData = ConfigData("${dataFolder.toFile()}${File.separator}eula", "${player.uniqueId}.json")
        try {
            if (GsonService.load(configData).asBoolean) return
        } catch (ignored: Exception) {
        }
        player.sendMessage(TextComponent.of(""))
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
        player.sendMessage(TextComponent.of(""))
    }

    private fun sendPlayers() = proxy.allPlayers.filter { player ->
        array1.find {
            val name = player.currentServer.orElse(null)?.serverInfo?.name
            name == null || name == it
        } != null
    }.forEach { sendPlayer(it) }

    private fun sendPlayer(player: Player) {
        if (connection.contains(player.uniqueId)) return
        connection.add(player.uniqueId)
        if (trys.putIfAbsent(player, 1) == 3) Runtime.getRuntime().gc()
        try {
            player.sendMessage(TextComponent.of("Loading data...", TextColor.BLUE), MessagePosition.ACTION_BAR)
            val a = if (usedEula.any { player.uniqueId == it }) true else {
                val configData =
                    ConfigData("${dataFolder.toFile()}${File.separator}eula", "${player.uniqueId}.json")
                GsonService.load(configData).asBoolean
            }
            if (!a) return
            for (element in array) {
                try {
                    val registeredServer = proxy.getServer(element.asString).orElse(null) ?: continue
                    if (array.find { it == player.currentServer.orElse(null) } != null) continue
                    player.sendMessage(
                        TextComponent.of("Try to connect to ${element.asString}...").color(TextColor.BLUE),
                        MessagePosition.ACTION_BAR
                    )
                    trys[player]?.inc()
                    player.createConnectionRequest(registeredServer).connect().join()
                } catch (ex: TimeoutException) {
                    ex.printStackTrace()
                } catch (ex: Exception) {
                    logger.error(ex.message)
                }
                break
            }
        } catch (ex: Exception) {
            player.sendMessage(
                TextComponent.of("Running in a error").color(TextColor.RED),
                MessagePosition.ACTION_BAR
            )
        } finally {
            connection.remove(player.uniqueId)
        }
    }

}
