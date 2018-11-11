import net.darkdevelopers.darkbedrock.darkframe.bungee.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.bungee.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.event.ProxyPingEvent
import net.md_5.bungee.event.EventHandler

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class MotdModule : Module, Listener(DarkFrame.instance) {
	override val description: ModuleDescription = ModuleDescription("MotdModule", "1.0", "Lars Artmann | LartyHD", "This module manage the Motd")

	private var lineOne: String = "§cDie Config konnte nicht geladen werden"
	private var lineTwo: String = "§4Bitte informiere einen Admin"
	private var maxPlayers: Int = 0

	@EventHandler
	fun onProxyPingEvent(event: ProxyPingEvent) {
		val ping = event.response
		ping.version.name = "${Messages.SERVER_NAME} System 1.8.X"
		ping.players.online = ProxyServer.getInstance().onlineCount
		ping.players.max = maxPlayers
		ping.description = "$lineOne\n$lineTwo"
	}
}