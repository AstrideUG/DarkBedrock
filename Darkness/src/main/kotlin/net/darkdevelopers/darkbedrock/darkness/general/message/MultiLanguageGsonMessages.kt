/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.08.2018 00:10.
 * Last edit 25.08.2018
 */
class MultiLanguageGsonMessages(config: GsonConfig) {

    private val languages = config.getAsNotNull<JsonObject>("languages").asJsonObject
    private val rawLanguages = languages.entrySet()
    val availableLanguages = mutableMapOf<String, Map<String, String>>().apply {
        rawLanguages.forEach { entry ->
            val messages = mutableMapOf<String, String>().apply {
                entry.value.asJsonObject.entrySet().forEach { this[it.key] = it.value.asString }
            }.toMap()
            this[entry.key] = messages
        }
    }.toMap()

}