/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.08.2018 00:10.
 * Last edit 05.06.2019
 */
@Suppress("unused")
class MultiLanguageGsonMessages(jsonObject: JsonObject) {

    private val languages = jsonObject["languages"]?.asJsonObject ?: JsonObject()
    /**
     * @author Lars Artmann | LartyHD
     * @since 05.06.2019
     */
    @Suppress("MemberVisibilityCanBePrivate")
    val availableLanguages: MutableMap<String, GsonStringMapWithSubs> = languages.entrySet().map {
        it.key to GsonStringMapWithSubs(it.value.asJsonObject)
    }.toMap().toMutableMap()

}