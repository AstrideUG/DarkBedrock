import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.api.DarkFrameSimplePlayerCommandModule
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.isPlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:31.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class InvSeeModule : DarkFrameSimplePlayerCommandModule("InvSee") {

    override fun execute(sender: CommandSender, target: Player) = sender.isPlayer { it.openInventory(target.inventory) }

}