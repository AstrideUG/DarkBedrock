import net.darkdevelopers.darkbedrock.darkframe.spigot.api.DarkFrameSimplePlayerCommandModule
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 03:00.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class FeedModule : DarkFrameSimplePlayerCommandModule("Feed") {

    override fun execute(sender: CommandSender, target: Player) {
        target.foodLevel = 20
        target.saturation = 20F
    }

}