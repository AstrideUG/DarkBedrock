/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.bungee.player

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:53.
 * Last edit 05.07.2018
 */
interface GetTarget {

    fun getTarget(sender: CommandSender, player: ProxiedPlayer?, lambda: (ProxiedPlayer) -> Unit)

    fun getTarget(sender: CommandSender, uuid: UUID, lambda: (ProxiedPlayer) -> Unit)

    fun getTarget(sender: CommandSender, name: String, lambda: (ProxiedPlayer) -> Unit)
}
