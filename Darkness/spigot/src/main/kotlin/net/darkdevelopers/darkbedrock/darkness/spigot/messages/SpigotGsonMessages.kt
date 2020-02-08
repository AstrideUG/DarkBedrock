/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.message.GsonMessages
import org.bukkit.ChatColor

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.08.2018 13:08.
 * Last edit 05.04.2019
 */
class SpigotGsonMessages(configData: ConfigData) : GsonMessages(configData) {

    init {

        availableMessages.forEach { (key, value) ->
            availableMessages[key] = value.map { ChatColor.translateAlternateColorCodes('&', it) }
        }

    }

}
