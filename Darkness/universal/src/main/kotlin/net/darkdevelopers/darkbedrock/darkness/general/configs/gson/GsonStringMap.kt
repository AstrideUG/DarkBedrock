/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonObject

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.08.2018 00:37.
 * Last edit 25.08.2018
 */
class GsonStringMap(jsonObject: JsonObject) {

    private val entries = jsonObject.entrySet()
    val available = mutableMapOf<String, String>().apply {
        this@GsonStringMap.entries.forEach { this[it.key] = it.value.asString }
    }

}