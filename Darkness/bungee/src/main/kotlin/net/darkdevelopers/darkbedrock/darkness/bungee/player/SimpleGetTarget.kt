package net.darkdevelopers.darkbedrock.darkness.bungee.player

import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:58.
 * Last edit 05.07.2018
 */
interface SimpleGetTarget : DefaultGetTarget {

    override fun getTarget(sender: CommandSender, player: ProxiedPlayer?, lambda: (ProxiedPlayer) -> Unit) =
        if (player != null)
            lambda(player)
        else
            sender.sendMessage(TextComponent(Messages.PLAYER_NOT_ONLINE.toString()))

}