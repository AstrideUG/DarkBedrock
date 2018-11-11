/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 21:00.
 * Last edit 20.08.2018
 */
class OrdnerUpModule : Module, Listener(DarkFrame.instance) {

	override val description: ModuleDescription = ModuleDescription("OrdnerUpModule", "1.0", "Lars Artmann | LartyHD", "")

	@EventHandler
	fun onPlayerCommandPreprocessEvent(event: PlayerCommandPreprocessEvent) {
		if (event.message.contains("../")) {
			cancel(event)
			event.player.sendMessage("../ ist in Commands nicht erlaubt")
		}
	}


}