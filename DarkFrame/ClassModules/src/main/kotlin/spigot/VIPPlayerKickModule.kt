import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerLoginEvent
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 19:24.
 * Last edit 07.07.2018
 */
class VIPPlayerKickModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("VIPPlayerKickModule", "1.0", "Lars Artmann | LartyHD", "This module makes room for VIP players when the server is full")

    @EventHandler
    fun onPlayerLoginEvent(event: PlayerLoginEvent) {
        val permission = "vip.kick"
        if (Bukkit.getMaxPlayers() == Bukkit.getOnlinePlayers().size && hasPermission(event.player, permission))
            ArrayList(Bukkit.getOnlinePlayers()).apply { shuffle() }.forEach {
                if (!hasPermission(it, permission)) {
                    it.kickPlayer("${TEXT}Du wurdest gekickt um einem Spieler mit einem höheren Rang platz zu machen")
                    return
                }
            }
    }

}