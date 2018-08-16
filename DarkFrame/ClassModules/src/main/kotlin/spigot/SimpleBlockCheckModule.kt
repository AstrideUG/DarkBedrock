/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 16.08.2018 16:17.
 * Last edit 16.08.2018
 */
class SimpleBlockCheckModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("SimpleBlockCheckModule", "1.0.1", "Lars Artmann | LartyHD", "")

    private val interact = mutableMapOf<Player, Int>()

    init {
        thread {
            try {
                while (true) {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1))
                    interact.clear()
                }
            } catch (ignored: InterruptedException) {
            }
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player ?: return
        if (interact[player] == null) interact[player] = 1 else interact[player] = (interact[player]!! + 1)
        if (interact[player] != null && interact[player]!! >= 10) player.kickPlayer("Too much interacts")
        val distance = event.clickedBlock?.location?.distance(player.location) ?: return
        if (event.hasBlock() && distance > 10.0) {
            println(player)
            player.isBanned = true
            player.kickPlayer("§4No Cheats")
        }
    }


}