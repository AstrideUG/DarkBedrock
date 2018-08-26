/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.message.GsonMessages
import org.bukkit.ChatColor

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.08.2018 13:08.
 * Last edit 26.08.2018
 */
class SpigotGsonMessages(config: GsonConfig) : GsonMessages(config) {

    init {
        availableMessages.forEach { availableMessages[it.key] = ChatColor.translateAlternateColorCodes('&', it.value) }
    }

}