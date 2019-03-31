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
 * Last edit 22.12.2018
 */
open class GsonMessages(private val config: GsonConfig) {

    private val jsonObject = config.getAs<JsonObject>("Messages") ?: JsonObject()
    private val useExternalFiles = config.getAs<JsonPrimitive>("UseExternalFiles", jsonObject)?.asBoolean ?: false
    private val language = config.getAs<JsonPrimitive>("language", jsonObject)?.asString ?: "en_US"
    private val languages = jsonObjectConsideringExternalFiles(language, "languages", jsonObject) ?: JsonObject()
    private val acrossLanguagesMessages = jsonObjectConsideringExternalFiles("across-languages", languages)
    private val messages = config.getAs<JsonObject>(language, languages) ?: JsonObject()
    private val gsonStringMap = GsonStringMapWithSubs(messages)
    private val gsonStringMapWithSubs =
        if (acrossLanguagesMessages != null) GsonStringMapWithSubs(acrossLanguagesMessages) else null
    val availableMessages = gsonStringMap.available
    val availableMessagesOnInit = availableMessages.toMap()

    init {
//        replaceKeys(availableMessages)
//        availableSubMessages.forEach { replaceKeys(it.value, "${it.key}.") }
    }

    private fun replaceKeys(map: MutableMap<String, String>, prefix: String = ""): MutableMap<String, String> {
        for (entry1 in map.entries) for (entry2 in map.entries) {
            if (entry1 == entry2) continue
            val key = "$prefix${entry2.key}"
            map[entry1.key] = replace(entry1.value, key, entry2.value)
        }
        return map
    }

    private fun jsonObjectConsideringExternalFiles(key: String, jsonObject: JsonObject): JsonObject? =
        jsonObjectConsideringExternalFiles(key, key, jsonObject)

    private fun jsonObjectConsideringExternalFiles(key1: String, key2: String, jsonObject: JsonObject): JsonObject? =
        if (useExternalFiles) jsonObjectByFile(key1) else config.getAs(key2, jsonObject)

    private fun jsonObjectByFile(fileName: String) =
        GsonConfig(ConfigData("${config.getDirectory()}languages", "$fileName.json")).jsonObject

    private fun replace(input: String, key: String, value: String) = input.replace("%$key%", value, true)

}