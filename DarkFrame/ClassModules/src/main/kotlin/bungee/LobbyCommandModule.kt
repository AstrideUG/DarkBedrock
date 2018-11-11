import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class LobbyCommandModule : Module, Command(
		commandName = "Lobby",
		aliases = *arrayOf("Hub", "l", "leave", "quit")
) {
	override val description: ModuleDescription = ModuleDescription("LobbyCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the lobby command")

	override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
		if (it.server.info.name.startsWith("lobby", ignoreCase = true))
			sender.sendMessage(TextComponent("${TEXT}Du bist schon auf einer Lobby"))
		else
			it.connect(ProxyServer.getInstance().getServerInfo("fallback"))
	}
}