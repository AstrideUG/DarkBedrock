import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.block.SignChangeEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 19:24.
 * Last edit 07.07.2018
 */
class ColoredSignsModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("ColoredSignsModule", "1.0", "Lars Artmann | LartyHD", "This module adds colored codes by signs")

    @EventHandler
    fun onSignChangeEvent(event: SignChangeEvent) = event.run {
        setLine(0, translateColorCodes(getLine(0)))
        setLine(1, translateColorCodes(getLine(1)))
        setLine(2, translateColorCodes(getLine(2)))
        setLine(3, translateColorCodes(getLine(3)))
    }

    private fun translateColorCodes(value: String): String = ChatColor.translateAlternateColorCodes('&', value)

}