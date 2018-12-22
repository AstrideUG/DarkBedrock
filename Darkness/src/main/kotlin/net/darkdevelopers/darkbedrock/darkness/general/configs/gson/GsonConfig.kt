/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
import net.darkdevelopers.darkbedrock.darkness.general.functions.check
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import java.io.File
import java.io.FileWriter
import java.nio.file.Files

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:18.
 * Last edit 17.10.2018
 */
@Suppress("unused")
open class GsonConfig(override val configData: ConfigData, var jsonObject: JsonObject = JsonObject(),

					  /**
					   * @author Lars Artmann | LartyHD
					   *
					   * If it is `false`, [#formatJson(JsonElement)] does not call [GsonBuilder#serializeNulls()]
					   *
					   * @since 17.10.2018
					   */
					  @Suppress("MemberVisibilityCanBePrivate")
					  val serializeNulls: Boolean = true) : DefaultConfig, Cloneable {

	companion object {

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * This method adds all values to the [JsonObject] when the key of the entry is not in it
		 *
		 * @see [#default(String, Any?)]
		 * @since 15.10.2018
		 */
		fun JsonObject.default(map: Map<String, Any?>): Unit = map.forEach { this.default(it.key, it.value) }


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * This method adds the [value] to the [JsonObject] when the [key] is not in it
		 *
		 * @param value cast to String (with the toString() method)
		 * @since 15.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun JsonObject.default(key: String, value: Any?) {
			if (this[key] == null) this.addProperty(key, value.toString())
		}


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Get a [JsonObject] by name or by the file at the config path by config ([GsonConfig]) with null support
		 * This is the config ([GsonConfig]) version of {@see multiPlaceJsonObject(JsonElement, String, File, IllegalStateException)} and {@see multiPlaceJsonObject(JsonElement?, String, File)}
		 *
		 * @param config the placeholder for the [JsonObject] or the String or is null
		 * @param name key name (for the exception message and get the [JsonElement] of the [config]})
		 * @param directory prefix folder
		 * @param canBeNull if is false the result can not be null but it can throw a [IllegalStateException] with the reason [JsonElement] by [config] is `null`
		 * @throws IllegalStateException will be thrown if the [JsonElement] by [config] is not a [String] or [JsonObject] or `null`. Null is not acceptable, if [canBeNull] is `false`
		 * @since 16.10.2018
		 */
		fun multiPlaceJsonObject(config: GsonConfig, name: String, directory: File, canBeNull: Boolean = true): JsonObject? =
				if (canBeNull) multiPlaceJsonObject(config.getAs<JsonElement>(name), name, directory)
				else multiPlaceJsonObject(config.getAsNotNull<JsonElement>(name), name, directory)

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Get a [JsonObject] by name or by the file at the config path with null support
		 * This is the null accept version of {@see multiPlaceJsonObject(JsonElement, String, File, IllegalStateException)}
		 *
		 * @param element the placeholder for the [JsonObject] or the [String] or is null
		 * @param name key name (for the exception message)
		 * @param directory prefix folder
		 * @throws IllegalStateException will be thrown if the [element] is not a [String], [JsonObject] or `null`
		 * @since 16.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun multiPlaceJsonObject(element: JsonElement?, name: String, directory: File): JsonObject? =
				if (element == null) null else multiPlaceJsonObject(element, name, directory, IllegalStateException("$name must a String, JsonObject or null"))


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Get a [JsonObject] by name or by the file at the config path
		 *
		 * @param element the placeholder for the [JsonObject] or the String
		 * @param name key name (for the exception message)
		 * @param directory prefix folder
		 * @param exception for change the message of the [IllegalStateException]
		 * @throws IllegalStateException will be thrown if the [element]} is not a [String] or [JsonObject]
		 * @since 16.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun multiPlaceJsonObject(
				element: JsonElement,
				name: String,
				directory: File,
				exception: IllegalStateException = IllegalStateException("$name must a String or JsonObject")
		): JsonObject = when (element) {
			is JsonPrimitive -> if (element.isString) GsonService.load(GsonConfig(ConfigData(
					if (!element.asString.contains(File.separator))
						directory
					else {
						val split = element.asString.split(File.separator).toMutableList()
						split.removeAt(split.size - 1)
						val a = split.run {
							val stringBuilder = StringBuilder()
							this.forEach { stringBuilder.append(File.separator).append(it) }
							stringBuilder.toString()
						}
						File("$directory$a")
					}, element.asString
			)).configData).asJsonObject else throw exception
			is JsonObject -> element
			else -> throw exception
		}


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Replace Values [oldValue] with [newValue] in all Strings, in the Map and the Maps in the Map when is no "\" before
		 *
		 * @throws IllegalStateException if [map] key is not a instance of [String] or [map] value is not an instance of Any
		 * @since 17.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun replaceValues(map: Map<String, Any?>, oldValue: String, newValue: String): Map<String, Any> {
			val result = mutableMapOf<String, Any>()
			map.forEach { (key, value) ->
				if (value is String) {
					fun addEntry(raw: String) {
						result[key] = raw.replace(oldValue, newValue, true)
					}

					val split = value.split("\\$oldValue", ignoreCase = true)
					if (split.isEmpty()) addEntry(value) else StringBuilder().apply {
						split.forEach { part -> this.append(part).append("\\$oldValue") }
						addEntry(this.toString())
					}
				} else if (value is Map<*, *>) @Suppress("UNCHECKED_CAST")
				result.putAll(replaceValues(
						/*It is checked at the runtime */ value.check(String::class.java.kotlin, Any::class.java.kotlin) as Map<String, Any>,
						oldValue,
						newValue
				))

			}
			return result
		}

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Replace Values [oldValue] with the key of the Map in all Strings, in the Map and the Maps in the Map when is no "\" before
		 *
		 * @throws IllegalStateException if Map key is not a instance of [String] or Map value is not an instance of Any
		 * @since 17.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun replaceValuesWithKey(map: Map<String, Any?>, oldValue: String = "<Key>"): Map<String, Any> {
			val result = mutableMapOf<String, Any>()
			map.forEach { (key, value) ->
				if (value is String) {
					fun addEntry(raw: String) {
						result[key] = raw.replace(oldValue, key, true)
					}

					val split = value.split("\\$oldValue", ignoreCase = true)
					if (split.isEmpty()) addEntry(value) else StringBuilder().apply {
						split.forEach { part -> this.append(part).append("\\$oldValue") }
						addEntry(this.toString())
					}
				} else if (value is Map<*, *>) @Suppress("UNCHECKED_CAST")
				result.putAll(replaceValuesWithKey(
						/*It is checked at the runtime */value.check(String::class.java.kotlin, Any::class.java.kotlin) as Map<String, Any>,
						oldValue
				))
			}
			return result
		}

		/**
		 * @author Lars Artmann | LartyHD
		 * @return a [Map] of the [JsonElement]s
		 * @since 17.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun mapByJson(jsonElement: JsonElement): Map<String, Any?> = Gson().fromJson<Map<String, Any?>>(jsonElement, Map::class.java).toNonNull()

	}

	@Deprecated("Use GsonService"/*, ReplaceWith("GsonService.load(configData)", "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService")*/)
	override fun load(): GsonConfig {
		try {
			jsonObject = JsonParser().parse(String(Files.readAllBytes(getFile().toPath()))).asJsonObject
//			jsonObject = Gson().fromJson(String(Files.readAllBytes(getFile().toPath())), JsonObject::class.java)
		} catch (ex: IllegalStateException) {
			GsonService.save(configData, jsonObject)
		}
		return this
	}

	@Deprecated("Use GsonService", ReplaceWith("GsonService.save(configData, jsonObject)", "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService"))
	override fun save(): GsonConfig {
		ConfigData.createFoldersIfNotExists(getDirectory())
		ConfigData.createFileIfNotExists(getFile())
		FileWriter(getFile()).apply {
			write(GsonService.formatJson(jsonObject))
			flush()
			close()
		}
		return this
	}

	fun <O> getAsNotNull(key: String): O = getAsNotNull(key, jsonObject)

	@Suppress("MemberVisibilityCanBePrivate")
	fun <O> getAsNotNull(key: String, jsonObject: JsonObject): O = getAs(key, jsonObject)
			?: throw NullPointerException("$key can not be null")

	override fun <O> getAs(key: String): O? = getAs(key, jsonObject)

	@Suppress("MemberVisibilityCanBePrivate")
	fun <O> getAs(key: String, jsonObject: JsonObject): O? {
		@Suppress("UNCHECKED_CAST")
		return jsonObject[key] as? O
	}


	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Adds the entry to the [jsonObject]
	 *
	 * @LastEdit 15.10.2018
	 * @since First known version 31.07.2018
	 */
	override fun <I> put(key: String, value: I): GsonConfig {
		put(key, value, jsonObject)
		return this
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Adds the entry to the [jsonObject]
	 *
	 * @since 15.10.2018
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun <I> put(key: String, value: I, jsonObject: JsonObject): GsonConfig {
		if (value is JsonElement) jsonObject.add(key, value) else jsonObject.add(key, JsonPrimitive(value.toString()))
		return this
	}

	@Suppress("MemberVisibilityCanBePrivate")
	@Deprecated("Use GsonService", ReplaceWith("GsonService.formatJson(jsonObject)", "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService"))
	fun formatJson(jsonElement: JsonElement): String {
		val result = GsonBuilder().setPrettyPrinting()
		if (serializeNulls) result.serializeNulls()
		return result.create().toJson(jsonElement)
	}

	override fun clone(): GsonConfig = copy(jsonObject)

	@Suppress("MemberVisibilityCanBePrivate")
	fun copy(jsonObject: JsonObject): GsonConfig {
		val gsonConfig = GsonConfig(configData)
		gsonConfig.jsonObject = jsonObject
		return gsonConfig
	}

	override fun toString(): String = "GsonConfig(configData=$configData, jsonObject=$jsonObject, serializeNulls=$serializeNulls)"


}

