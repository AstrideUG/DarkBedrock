/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMapWithSubs
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.08.2018 00:10.
 * Last edit 15.10.2018
 */
class MultiLanguageGsonMessages(config: GsonConfig) {

	private val jsonObject = config.getAsNotNull<JsonObject>("Messages")
	private val useExternalFiles = config.getAs<JsonPrimitive>("UseExternalFiles", jsonObject)?.asBoolean ?: false
	private val languages = config.getAsNotNull<JsonObject>("languages", jsonObject)
	private val rawLanguages = languages.entrySet().toNonNull()
	/**
	 * @author Lars Artmann | LartyHD
	 * @since 15.10.2018
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	val availableLanguages: MutableMap<String, GsonStringMapWithSubs> = mutableMapOf<String, GsonStringMapWithSubs>().apply {
		rawLanguages.forEach { this[it.key] = GsonStringMapWithSubs(it.value.asJsonObject) }
	}
	/**
	 * @author Lars Artmann | LartyHD
	 * @since 15.10.2018
	 */
	val availableLanguagesOnInit: Map<String, GsonStringMapWithSubs> = availableLanguages.toMap()

}