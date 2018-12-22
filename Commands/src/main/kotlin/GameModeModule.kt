import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.functions.getOrKey
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.SimplePermissionsCommandModule
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.possiblePlayer
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:47.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
class GameModeModule : SimplePermissionsCommandModule("GameMode") {
    private val otherPerms = config.permissions.getOrKey("$singlePerms.Other")
    override val command: () -> PermissionCommand = {
        object : PermissionCommand(
            DarkFrame.instance,
            usage = "<$defaultCommandName>$singlePerms|<$defaultCommandName> [Player]:$otherPerms",
            minLength = 1,
            maxLength = 2,
            tabCompleter = TabCompleter { _, _, _, args ->
                when (args.size) {
                    0 -> GameMode.values().map { it.name }
                    1 -> Utils.getPlayers().map { it.name }
                    else -> listOf<String>()
                }
            },
            aliases = *arrayOf("GM", "Mode")
        ) {
            override fun perform(sender: CommandSender, args: Array<String>) {
                val gameMode = try {
                    val id = args[0].toIntOrNull()
                    if (id != null) org.bukkit.GameMode.getByValue(id) else org.bukkit.GameMode.valueOf(args[0].toUpperCase())
                } catch (ex: Exception) {
                    null
                }
                if (gameMode == null) sendUseMessage(sender)
                else {
                    possiblePlayer(
                        config.messages,
                        prefix,
                        sender,
                        args[1],
                        singlePerms,
                        otherPerms
                    ) { _: CommandSender, target: Player -> target.gameMode = gameMode }
                }
            }
        }
    }
}