/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 15:19.
 * Last edit 24.08.2018
 */
var isPlayerFailedMessage: String = "Der Command ist nur für Spieler"

inline fun CommandSender.isPlayer(lambda: (Player) -> Unit) = isPlayer(lambda) { sendMessage(isPlayerFailedMessage) }

inline fun CommandSender.isPlayer(onSuccess: (Player) -> Unit, onFail: () -> Unit) =
    if (this is Player) onSuccess(this) else onFail()