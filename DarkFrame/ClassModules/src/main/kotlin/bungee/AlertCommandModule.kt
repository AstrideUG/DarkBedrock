import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class AlertCommandModule : Module, Command(
        commandName = "Alert",
        permission = "darkbedrock.darkframe.bungee.modules.commands.alert",
        usage = "<Nachricht>",
        minLength = 1,
        maxLength = Int.MAX_VALUE,
        aliases = *arrayOf("bc", "broadcast")
) {
    override val description: ModuleDescription = ModuleDescription("AlertCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the alert command")

    override fun perform(sender: CommandSender, args: Array<String>) {
        var message = ""
        for (arg in args) message += "$arg "
        for (player in ProxyServer.getInstance().players) player.run {
            sendMessage(TextComponent(" "))
            sendMessage(TextComponent("${Messages.SERVER_NAME}$IMPORTANT$EXTRA: $TEXT${ChatColor.translateAlternateColorCodes('&', message)}"))
            sendMessage(TextComponent(" "))
        }
    }
}