/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import net.darkdevelopers.darkbedrock.darkness.general.functions.toConfigData
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 23:09.
 * Last edit 05.06.2019
 */
open class GsonMessages(private val configData: ConfigData) {

    private val jsonObject = configData.load<JsonObject>()

    private val useExternalFiles = jsonObject.get("use-external-files")?.asBoolean ?: false
    private val language = jsonObject.get("language")?.asString ?: "en_US"
    private val languages = jsonObjectConsideringExternalFiles(language, "languages", jsonObject) ?: JsonObject()
    private val acrossLanguages = jsonObjectConsideringExternalFiles("across-languages", languages)
    private val messages = languages.get(language)?.asJsonObject ?: JsonObject()
    private val gsonStringMap = GsonStringMapWithSubs(messages)
    private val acrossLanguagesMessages =
        if (acrossLanguages != null) GsonStringMapWithSubs(acrossLanguages).available else null
    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 05.04.2019 23:38.
     * Current Version: 1.0 (05.04.2019 - 06.04.2019)
     */
    val availableMessages: MutableMap<String, List<String?>> = gsonStringMap.available.apply {
        putAll(acrossLanguagesMessages ?: return@apply)
        remove("")
    }

    init {
        availableMessages.replaceKeys()
    }

    private fun MutableMap<String, List<String?>>.replaceKeys(prefix: String = "") {
        for (entry1 in entries) for (entry2 in entries) {
            if (entry1 == entry2) continue
            val key = "$prefix${entry2.key}"
            this[entry1.key] = entry1.value.replace(key, entry2.value.firstOrNull().toString())
        }
    }

    private fun jsonObjectConsideringExternalFiles(@Suppress("SameParameterValue") key: String, jsonObject: JsonObject): JsonObject? =
        jsonObjectConsideringExternalFiles(key, key, jsonObject)

    private fun jsonObjectConsideringExternalFiles(key1: String, key2: String, jsonObject: JsonObject): JsonObject? =
        if (useExternalFiles) jsonObjectByFile(key1) else jsonObject[key2]?.asJsonObject

    private fun jsonObjectByFile(fileName: String) =
        "${configData.directory}${File.separator}languages".toConfigData(fileName).load<JsonObject>()

    private fun List<String?>.replace(key: String, value: String): List<String?> =
        map { it?.replace("%$key%", value, true) }

}