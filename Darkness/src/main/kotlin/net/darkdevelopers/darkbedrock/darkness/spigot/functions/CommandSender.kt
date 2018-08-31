/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.command.CommandSender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.08.2018 19:15.
 * Last edit 31.08.2018
 */
var messages = mutableMapOf<String, String>()

fun CommandSender.sendConfigurableMessage(name: String) = sendMessage(messages[name])
