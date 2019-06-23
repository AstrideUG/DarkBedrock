/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.player

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendTo
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created on 02.06.2018 16:58.
 * @author Lars Artmann | LartyHD
 */
interface SimpleGetTarget : DefaultGetTarget {

    override fun getTarget(sender: CommandSender, player: Player?, lambda: (Player) -> Unit): Unit = if (player != null)
        lambda(player)
    else configService.playerNotOnline.sendTo(sender)

    override fun getTarget(player: Player?, lambda: (Player?) -> Unit) = lambda(player)
}