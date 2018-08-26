/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMap

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 23:09.
 * Last edit 26.08.2018
 */
open class GsonMessages(config: GsonConfig) {

    private val language = config.getAs<JsonPrimitive>("language")?.asString ?: "en_US"
    private val languages = config.getAsNotNull<JsonObject>("languages").asJsonObject
    private val messages = config.getAsNotNull<JsonObject>(language, languages).asJsonObject
    val availableMessages = GsonStringMap(messages).available

    init {
        for (entry1 in availableMessages.entries) {
            for (entry2 in availableMessages.entries) {
                if (entry1 == entry2) continue
                availableMessages[entry1.key] = entry1.value.replace("%${entry2.key}%", entry2.value, true)
            }
        }
    }

}