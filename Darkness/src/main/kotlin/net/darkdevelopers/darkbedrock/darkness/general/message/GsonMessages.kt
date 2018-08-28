/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs

@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 23:09.
 * Last edit 28.08.2018
 */
open class GsonMessages(private val config: GsonConfig) {

    private val language = config.getAs<JsonPrimitive>("language")?.asString ?: "en_US"
    private val useExtraConfigs = config.getAs<JsonPrimitive>("UseExtraConfigs")?.asBoolean ?: false
    private val languages = if (useExtraConfigs) getJsonObjectByFile(language) else config.getAsNotNull("languages")
    private val globalMessages: JsonObject? = if (useExtraConfigs) getJsonObjectByFile("global") else config.getAs("global", languages)
    private val messages = config.getAsNotNull<JsonObject>(language, languages)
    private val gsonStringMap = GsonStringMapWithSubs(messages)
    val availableMessages = mutableMapOf<String, String>().apply {
        putAll(gsonStringMap.available)
        if (globalMessages != null) putAll(GsonStringMapWithSubs(globalMessages).available)
    }
    val availableMessagesOnInit = availableMessages.toMap()
    val availableSubMessages = gsonStringMap.availableSubs

    init {
        replaceKeys(availableMessages)
        availableSubMessages.forEach { replaceKeys(it.value, "${it.key}.") }
    }

    private fun replaceKeys(map: MutableMap<String, String>, prefix: String = ""): MutableMap<String, String> {
        for (entry1 in map.entries) {
            for (entry2 in map.entries) {
                if (entry1 == entry2) continue
                map[entry1.key] = entry1.value.replace("%$prefix${entry2.key}%", entry2.value, true)
            }
        }
        return map
    }

//    private fun replaceKeysForNotMutableMaps(map: Map<String, String>) = replaceKeys(HashMap(map))

    private fun getJsonObjectByFile(fileName: String) = GsonConfig(ConfigData("${config.getDirectory()}languages", "$fileName.json")).load().jsonObject

}