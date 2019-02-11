/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.SimplePlayerCommandModule
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.isPlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 00:42.
 * Current Version: 1.0 (12.02.2019 - 12.02.2019)
 */
class EnderChestModule : SimplePlayerCommandModule("EnderChest") {

    override val command: () -> SimplePlayerCommandModule.PlayerCommand = {
        PlayerCommand(
            DarkFrame.instance,
            aliases = *arrayOf("ec")
        )
    }

    override fun execute(sender: CommandSender, target: Player) =
        sender.isPlayer { it.openInventory(target.enderChest) }

}