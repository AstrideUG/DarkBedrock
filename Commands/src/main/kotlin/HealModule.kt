import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.api.DarkFrameSimplePlayerCommandModule
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 04:46.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class HealModule : DarkFrameSimplePlayerCommandModule("Heal") {

    override fun CommandSender.execute(target: Player) {
        target.foodLevel = 20
        target.saturation = 20F
        target.health = 20.0
        target.activePotionEffects.forEach { target.removePotionEffect(it.type) }
    }

}