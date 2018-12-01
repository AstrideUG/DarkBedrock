/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.11.2018 20:21.
 * Current Version: 1.0 (29.11.2018 - 29.11.2018)
 */
class NoMoveModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription =
        ModuleDescription(NoMoveModule::class.java.name, "1.0", "Lars Artmann | LartyHD", "")

    init {
        val world = Bukkit.getWorlds()[0]
        val arrayOf = arrayOf(
            world.getBlockAt(0, 99, 0),
            world.getBlockAt(0, 99, 1),
            world.getBlockAt(0, 99, -1),

            world.getBlockAt(1, 99, 0),
            world.getBlockAt(1, 99, 1),
            world.getBlockAt(1, 99, -1),

            world.getBlockAt(-1, 99, 0),
            world.getBlockAt(-1, 99, 1),
            world.getBlockAt(-1, 99, -1)
        )
        for (i in -1..3) arrayOf.clone().forEach {
            val block = it.location.add(0.0, i.toDouble(), 0.0).block
            block.type = Material.STAINED_GLASS
            block.data = 15
        }
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        player.flySpeed = 0F
        player.walkSpeed = 0F
        player.allowFlight = true
        player.isFlying = true
        player.teleport(Location(Bukkit.getWorlds()[0], 0.5, 100.0, 0.5))
        player.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, Int.MAX_VALUE, 1, true, false))
        Utils.goThroughAllPlayers { player.hidePlayer(it) }
        Utils.goThroughAllPlayers { it.hidePlayer(player) }
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        cancel(event)
        event.player.sendMessage("§cDas darfst du hier nicht")
    }
}