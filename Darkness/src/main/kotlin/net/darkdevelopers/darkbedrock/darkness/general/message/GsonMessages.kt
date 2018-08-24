/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 23:09.
 * Last edit 25.08.2018
 */
class GsonMessages(config: GsonConfig) {

    private val language = config.getAs<JsonPrimitive>("language")?.asString ?: "en_US"
    private val languages = config.getAsNotNull<JsonObject>("languages").asJsonObject
    private val messages = config.getAsNotNull<JsonObject>(language, languages).asJsonObject
    private val rawMessages = messages.entrySet()
    val availableMessages = mutableMapOf<String, String>().apply {
        rawMessages.forEach { this[it.key] = it.value.asString }
    }

}