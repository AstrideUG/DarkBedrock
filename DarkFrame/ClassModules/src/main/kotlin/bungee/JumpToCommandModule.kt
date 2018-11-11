import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 05.07.2018
 */
class JumpToCommandModule : Module, Command(
		commandName = "JumpTo",
		permission = "darkbedrock.darkframe.bungee.modules.commands.jumpto",
		usage = "<Spieler>",
		minLength = 1,
		maxLength = 1
) {
	override val description: ModuleDescription = ModuleDescription("JumpToCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the jumpto command")

	override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) { player ->
		getTarget(sender, args[0]) { target ->
			player.connect(target.server.info)
		}
	}
}