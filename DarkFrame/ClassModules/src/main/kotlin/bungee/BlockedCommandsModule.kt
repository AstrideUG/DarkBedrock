import net.darkdevelopers.darkbedrock.darkframe.bungee.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.bungee.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.event.EventHandler

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class BlockedCommandsModule : Module, Listener(DarkFrame.instance) {
	override val description: ModuleDescription = ModuleDescription("BlockedCommandsModule", "1.0", "Lars Artmann | LartyHD", "This module blocks commands")

	private val blocked: Set<String> = GsonConfig(ConfigData(description.folder, "config.json")).load().getAs<Array<String>>("blockedCommands")?.toSet()
			?: throw NullPointerException("blockedCommands can not be null")

	@EventHandler
	fun onChatEvent(event: ChatEvent) {
		if (event.isCommand) return
		val player = event.sender as? ProxiedPlayer ?: return
		val command = event.message.split(" ").dropLastWhile { it.isEmpty() }.toTypedArray()[0]
		val splits = command.toLowerCase().split(":").dropLastWhile { it.isEmpty() }.toTypedArray()
		blocked.forEach {
			if (command.equals("/$blocked", true) || splits.size >= 2 && splits[1].equals(it, true)) {
				cancel(event)
				player.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Der Command $IMPORTANT$command$TEXT ist verboten"))
			}
		}
	}
}