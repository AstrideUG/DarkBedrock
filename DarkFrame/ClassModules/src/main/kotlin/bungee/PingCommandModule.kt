import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class PingCommandModule : Module, Command(
        commandName = "Ping",
        usage = "[Spieler]",
        maxLength = 1
) {
    override val description: ModuleDescription = ModuleDescription("PingCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the ping command")

    override fun perform(sender: CommandSender, args: Array<String>) = if (args.isEmpty())
        isPlayer(sender, {
            sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Dein Ping beträgt $IMPORTANT${it.ping}${TEXT}ms"))
        }, { sendUseMessage(sender) })
    else getTarget(sender, args[0]) {
        sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Der Ping des Spieler's $IMPORTANT${it.name}$TEXT beträgt $IMPORTANT${it.ping}${TEXT}ms"))
    }
}