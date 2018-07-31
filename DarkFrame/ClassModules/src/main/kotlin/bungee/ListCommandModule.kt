import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
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
class ListCommandModule : Module, Command(commandName = "List") {
    override val description: ModuleDescription = ModuleDescription("ListCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the list command")

    override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
        val info = it.server.info
        sender.run {
            sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
            sendMessage(TextComponent("${TEXT}Spieler auf dem ${IMPORTANT}Netzwerk$IMPORTANT: $TEXT${ProxyServer.getInstance().onlineCount}"))
            sendMessage(TextComponent("${TEXT}Spieler auf $IMPORTANT${info.name}$IMPORTANT: $TEXT${info.players.size}"))
            sendMessage(TextComponent("$PRIMARY$EXTRA$DESIGN                                                               "))
        }
    }
}