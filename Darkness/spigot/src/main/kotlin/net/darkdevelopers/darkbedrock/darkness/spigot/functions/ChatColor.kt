/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("ChatColorUtils")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.ChatColor

/*
 * Created on 21.07.2019 02:49.
 * @author Lars Artmann | LartyHD
 */

fun String.translateColors(altColorChar: Char = '&'): String =
    ChatColor.translateAlternateColorCodes(altColorChar, this)

fun String.stripColor(): String = ChatColor.stripColor(this)