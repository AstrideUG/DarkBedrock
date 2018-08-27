/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonElement
import com.google.gson.JsonObject

@Suppress("MemberVisibilityCanBePrivate"/*, "unused"*/)
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 00:25.
 * Last edit 28.08.2018
 */
class GsonStringMapWithSubs(jsonObject: JsonObject) {

    private val subMessages = mutableMapOf<String, JsonObject>()
    val available = getMessages(jsonObject.entrySet())
    val availableOnInit = available.toMap()
    val availableSubs = mutableMapOf<String, MutableMap<String, String>>()

    init {
        while (subMessages.isNotEmpty()) subMessages.forEach { availableSubs[it.key] = getMessages(it.value.entrySet()) }
    }

    private fun getMessages(entries: Set<Map.Entry<String, JsonElement>>) = getMessages(mutableMapOf(), entries)

    private fun getMessages(map: MutableMap<String, String>, entries: Set<Map.Entry<String, JsonElement>>) = map.apply {
        entries.forEach { if (it.value.isJsonObject) subMessages[it.key] = it.value.asJsonObject else this[it.key] = it.value.asString }
    }

}