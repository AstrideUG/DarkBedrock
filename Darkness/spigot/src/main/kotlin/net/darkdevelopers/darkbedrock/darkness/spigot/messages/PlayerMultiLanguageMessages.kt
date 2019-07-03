/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import me.clip.placeholderapi.PlaceholderAPI
import net.darkdevelopers.darkbedrock.darkness.general.message.MultiLanguageMessages
import org.bukkit.entity.Player
import java.util.*

/**
 * Created on 03.07.2019 01:57.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class PlayerMultiLanguageMessages : MultiLanguageMessages() {

    fun msg(locale: Locale, string: String, player: Player, vararg objects: Any): String =
        msg(locale, string.replaced(player), objects)

    @Suppress("MemberVisibilityCanBePrivate")
    fun msg(bundle: ResourceBundle, string: String, player: Player, vararg objects: Any): String =
        if (objects.isEmpty()) bundle.getString(string)
        else format(bundle, string.replaced(player), *objects)

    private fun String.replaced(player: Player) = PlaceholderAPI.setPlaceholders(player, this)

}