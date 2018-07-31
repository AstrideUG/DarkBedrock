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
class MegaPlusCommandModule : Module, Command(
        commandName = "MegaPlus",
        aliases = *arrayOf("mega+", "premium+", "premiumplus")
) {
    override val description: ModuleDescription = ModuleDescription("MegaPlusCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the megaplus command")

    override fun perform(sender: CommandSender, args: Array<String>) = sender.run {
        sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Vorraussetzungen für ${SECONDARY}Mega+$IMPORTANT:"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Mindestens ${IMPORTANT}500 ${TEXT}Abonnenten"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Min. ein Video oder Stream auf unserem Server"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Usere IP in der Beschreibung von den Videos/Streams"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Videos werden ab 150 Aufrufen gewertet"))
        sendMessage(TextComponent("$IMPORTANT-> ${TEXT}Streams werden ab 15 Zuschauern gewertet"))
    }
}