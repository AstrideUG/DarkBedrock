/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import me.clip.placeholderapi.PlaceholderAPI
import net.darkdevelopers.darkbedrock.darkness.general.message.format
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.locale
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.PluginManager
import java.util.*
import net.darkdevelopers.darkbedrock.darkness.general.message.msg as msg0

/*
 * Created on 03.07.2019 01:57.
 * @author Lars Artmann | LartyHD
 */

fun Player.msg(string: String, vararg objects: Any): String =
    msg0(locale, string.replaced(this), objects)

fun Player.msg(locale: Locale, string: String, vararg objects: Any): String =
    msg0(locale, string.replaced(this), objects)

@Suppress("unused")
fun Player.msg(bundle: ResourceBundle, string: String, vararg objects: Any): String =
    if (objects.isEmpty()) bundle.getString(string)
    else format(bundle, string.replaced(player), *objects)

fun String.replaced(player: Player, pluginManager: PluginManager = Bukkit.getPluginManager()): String =
    if (pluginManager.getPlugin("PlaceholderAPI") != null) PlaceholderAPI.setPlaceholders(player, this) else this
