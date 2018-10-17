/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonElement
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.functions.castedInstanceAndNotNull
import net.darkdevelopers.darkbedrock.darkness.general.functions.check


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 17.10.2018 07:52.
 * Last edit 18.10.2018
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class GsonMessages2(unsafeInput: Map<String, Any?>) {

	companion object {

		/**
		 * @author Lars Artmann | LartyHD
		 * @return a [GsonMessages2] by [GsonConfig#mapByJson(JsonElement)]
		 * @since 17.10.2018
		 */
		fun byJson(jsonElement: JsonElement): GsonMessages2 = GsonMessages2(GsonConfig.mapByJson(jsonElement))

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Create [MutableMap<String, V>] changed in [function] and {@return [Map<String, V>]}
		 *
		 * @since 17.10.2018
		 */
		fun <V : Any?> of(function: MutableMap<String, V>.() -> Unit): Map<String, V> = net.darkdevelopers.darkbedrock.darkness.general.functions.of(function)

	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * The Map with all values
	 *
	 * @since 17.10.2018
	 */
	val input: Map<String, Any?> = unsafeInput.run {
		fun Map<String, Any?>.checker(): Map<String, Any?> {
			val replaceValuesWithKey = GsonConfig.replaceValuesWithKey(this)
			replaceValuesWithKey.forEach {
				if (it.value.javaClass.kotlin.isInstance(Map::class.java.kotlin)) {
					val checker = it.javaClass.kotlin.castedInstanceAndNotNull(mapOf<String, Any?>())?.check()?.checker()
					if (checker != null) return checker
				}
			}
			return replaceValuesWithKey
		}
		this.check().checker()
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * The [separator] [String] for sub groups
	 *
	 * @since 17.10.2018
	 */
	val separator: String = kotlin.run {
		val separator = input["separator"] as? String
		return@run if (separator == null || separator == "\\") "." else separator
	}
	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * This language will be default for all user
	 *
	 * @since 17.10.2018
	 */
	val defaultLanguage: String = input["language"] as? String ?: "en_US"
	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * A [Map<String, String>] of all messages in all [languages]
	 *
	 * @since 18.10.2018
	 */
	@Suppress("UNCHECKED_CAST")
//	TODO: Check if is the best way
	val languages: Map<String, String> = (input["languages"] as? Map<String, Any> ?: emptyMap()).run {
		of {
			fun Any.forAllMaps() {
				fun Any.maps() = (this as? Map<String, Any?>)?.filter { it.value as? Map<String, Any?> != null }

				this.maps()?.forEach { _, value ->
					fun Map<String, Any?>.getAllStringsAndPutIn(map: MutableMap<String, String>): Map<String, String> {
						map.putAll(this.filter { it.value is String }.map { it.key to it.value.toString() }.toMap())
						return map
					}

					val allMaps = value?.maps() ?: return@forEach
					allMaps.getAllStringsAndPutIn(this@of)
					allMaps.forAllMaps()
				}
			}
			this@run.forAllMaps()
		}
	}

	private fun <K, V> Map<K, V>.check() = this.check(
			String::class.java.kotlin,
			String::class.java.kotlin,
			Map::class.java.kotlin,
			arrayOf<Any?>().javaClass.kotlin,
			Double::class.java.kotlin,
			Float::class.java.kotlin,
			Long::class.java.kotlin,
			Int::class.java.kotlin,
			Short::class.java.kotlin,
			Byte::class.java.kotlin,
			Boolean::class.java.kotlin
	)
}


