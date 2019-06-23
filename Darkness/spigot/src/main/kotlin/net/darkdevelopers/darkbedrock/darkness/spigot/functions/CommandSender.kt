/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Created on 31.08.2018 19:15.
 * @author Lars Artmann | LartyHD
 */

infix fun String?.sendIfNotNull(sender: CommandSender) {
    this?.sendTo(sender)
}

infix fun Array<String?>.sendIfNotNull(sender: CommandSender): Unit = mapNotNull { it }.sendTo(sender)
infix fun Collection<String?>.sendIfNotNull(sender: CommandSender): Unit = mapNotNull { it }.sendTo(sender)

infix fun String.sendTo(sender: CommandSender): Unit = sender.sendMessage(this)
infix fun String.sendTo(players: Iterable<CommandSender>): Unit = players.forEach { sendTo(it) }
infix fun Array<String?>.sendTo(sender: CommandSender): Unit = sender.sendMessage(this)
infix fun Collection<String?>.sendTo(sender: CommandSender): Unit = sender.sendMessage(toTypedArray())

inline fun CommandSender.isPlayer(
    lambda: (Player) -> Unit,
    isNoPlayerMessage: String = configService.isPlayerFailedMessage
): Unit =
    isPlayer(lambda) { sendMessage(isNoPlayerMessage) }

inline fun CommandSender.isPlayer(onSuccess: (Player) -> Unit, onFail: () -> Unit): Unit =
    if (this is Player) onSuccess(this) else onFail()

fun CommandSender.execute(command: String): Boolean = Bukkit.dispatchCommand(this, command)
