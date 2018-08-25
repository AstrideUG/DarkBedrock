
import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerLoginEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 19:24.
 * Last edit 25.08.2018
 */
class OnlyProxyJoinModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("OnlyProxyJoinModule", "1.0", "Lars Artmann | LartyHD", "This module asks if the host address is valid")

    private lateinit var config: GsonConfig
    private lateinit var proxies: JsonArray
    private lateinit var ips: Set<String>
    private lateinit var kickMessage: String

    override fun load() {
        config = GsonConfig(ConfigData(description.folder)).load()
    }

    override fun start() {
        proxies = config.getAs<JsonArray>("proxies").toNonNull()
        ips = mutableSetOf<String>().apply { proxies.forEach { add(it.asString) } }.toSet()
        kickMessage = config.getAs<JsonPrimitive>("KickMessage")?.asString.toNonNull()
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLoginEvent(event: PlayerLoginEvent) {
        if (!check(event.realAddress.hostAddress)) event.disallow(
                PlayerLoginEvent.Result.KICK_OTHER,
                kickMessage
//                "${TEXT}Bitte joine über ${Messages.SERVER_NAME}"
        )
    }

    private fun check(ip: String): Boolean {
        ips.forEach { if (it.equals(ip, ignoreCase = true)) return true }
        return false
    }

}