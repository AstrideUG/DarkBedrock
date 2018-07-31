import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class KickCommandModule : Module, Command(
        commandName = "Kick",
        permission = "darkbedrock.darkframe.bungee.modules.commands.kick",
        usage = "<Spieler> <Grund>",
        minLength = 2,
        maxLength = Int.MAX_VALUE
) {
    override val description: ModuleDescription = ModuleDescription("KickCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the kick command")

    override fun perform(sender: CommandSender, args: Array<String>) = getTarget(sender, args[0]) { target ->
        val reason = getReason(args, target)
        target.disconnect(TextComponent("${TEXT}Du wurdest vom ${Messages.SERVER_NAME}$IMPORTANT Netzwerk$TEXT gekickt.\n${TEXT}Grund$IMPORTANT: $IMPORTANT$reason"))
        sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Du hast $IMPORTANT${target.name}$TEXT gekickt"))
        for (players in ProxyServer.getInstance().players) hasPermission(players, permission) {
            players.run {
                sendMessage(TextComponent("${Messages.PREFIX}$IMPORTANT${target.name}$TEXT wurde von $IMPORTANT${sender.name}$TEXT gekickt."))
                sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Grund$IMPORTANT: $IMPORTANT$reason"))
            }
        }
    }

    private fun getReason(args: Array<String>, target: ProxiedPlayer): String {
        val sb = StringBuilder()
        for (arg in args) sb.append(arg).append(" ")
        return sb.toString().substring(target.name.length + 1)
    }
}