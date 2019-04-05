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
 * Last edit 05.04.2019
 */
open class GsonMessages(private val config: GsonConfig) {

    private val jsonObject = config.getAs<JsonObject>("Messages") ?: JsonObject()
    private val useExternalFiles = config.getAs<JsonPrimitive>("UseExternalFiles", jsonObject)?.asBoolean ?: false
    private val language = config.getAs<JsonPrimitive>("language", jsonObject)?.asString ?: "en_US"
    private val languages = jsonObjectConsideringExternalFiles(language, "languages", jsonObject) ?: JsonObject()
    private val acrossLanguages = jsonObjectConsideringExternalFiles("across-languages", languages)
    private val messages = config.getAs<JsonObject>(language, languages) ?: JsonObject()
    private val gsonStringMap = GsonStringMapWithSubs(messages)
    private val acrossLanguagesMessages =
        if (acrossLanguages != null) GsonStringMapWithSubs(acrossLanguages).available else null
    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 05.04.2019 23:38.
     * Current Version: 1.0 (05.04.2019 - 05.04.2019)
     */
    val availableMessages: MutableMap<String, List<String?>> =
        gsonStringMap.available.apply { putAll(acrossLanguagesMessages ?: return@apply) }

    init {
        availableMessages.replaceKeys()
    }

    private fun MutableMap<String, List<String?>>.replaceKeys(prefix: String = "") {
        for (entry1 in entries) for (entry2 in entries) {
            if (entry1 == entry2) continue
            val key = "$prefix${entry2.key}"
            this[entry1.key] = entry1.value.replace(key, entry2.value.firstOrNull().orEmpty())
        }
    }

    private fun jsonObjectConsideringExternalFiles(key: String, jsonObject: JsonObject): JsonObject? =
        jsonObjectConsideringExternalFiles(key, key, jsonObject)

    private fun jsonObjectConsideringExternalFiles(key1: String, key2: String, jsonObject: JsonObject): JsonObject? =
        if (useExternalFiles) jsonObjectByFile(key1) else config.getAs(key2, jsonObject)

    private fun jsonObjectByFile(fileName: String) =
        GsonConfig(ConfigData("${config.getDirectory()}languages", "$fileName.json")).jsonObject

    private fun List<String?>.replace(key: String, value: String): List<String?> =
        map { it?.replace("%$key%", value, true) }

}