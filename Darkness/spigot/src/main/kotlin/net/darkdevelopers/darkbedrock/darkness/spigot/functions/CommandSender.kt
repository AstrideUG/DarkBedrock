/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.08.2018 19:15.
 * Last edit 22.12.2018
 */
@Deprecated("STATIC problemes")
var messages: MutableMap<String, String> = mutableMapOf()

@Deprecated("STATIC problemes", ReplaceWith("sendMessage(messages[name])"))
fun CommandSender.sendConfigurableMessage(name: String) = sendMessage(messages[name])

fun String?.sendIfNotNull(sender: CommandSender) = this?.sendTo(sender)

fun String.sendTo(sender: CommandSender) = this.apply { sender.sendMessage(this) }
