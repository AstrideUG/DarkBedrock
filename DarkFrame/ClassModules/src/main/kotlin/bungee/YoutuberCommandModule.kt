import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
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
class YoutuberCommandModule : Module, Command(
        commandName = "Youtuber",
        aliases = *arrayOf("yt")
) {
    override val description: ModuleDescription = ModuleDescription("YoutuberCommandModule", "1.0.1", "Lars Artmann | LartyHD and rexlManu", "This module adds the youtuber command")

    override fun perform(sender: CommandSender, args: Array<String>) = sender.run {
        sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Vorraussetzungen für ${SECONDARY}Youtuber$IMPORTANT:"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Mindestens ${IMPORTANT}1000 ${TEXT}Abonnenten"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Min. ein Video oder Stream auf unserem Server"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Usere IP in der Beschreibung von den Videos/Streams"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Videos werden ab 300 Aufrufen gewertet"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Streams werden ab 30 Zuschauern gewertet"))
    }
}