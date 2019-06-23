/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.player

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toPlayer
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toPlayerUUID
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 16:53.
 * Last edit 27.03.2019
 */
interface DefaultGetTarget : GetTarget {

    override fun getTarget(sender: CommandSender, uuid: UUID, lambda: (Player) -> Unit) =
        getTarget(sender, uuid.toPlayer(), lambda)

    override fun getTarget(sender: CommandSender, name: String, lambda: (Player) -> Unit) =
        getTarget(sender, name.toPlayerUUID(), lambda)

    override fun getTarget(uuid: UUID?, lambda: (Player?) -> Unit) =
        if (uuid == null) lambda(null) else getTarget(uuid.toPlayer(), lambda)

    override fun getTarget(name: String?, lambda: (Player?) -> Unit) =
        if (name == null) lambda(null) else getTarget(name.toPlayerUUID(), lambda)
}
