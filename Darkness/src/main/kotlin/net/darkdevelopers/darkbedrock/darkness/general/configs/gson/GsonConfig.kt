/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:18.
 * Last edit 15.10.2018
 */
@Suppress("unused")
open class GsonConfig(override val configData: ConfigData, var jsonObject: JsonObject = JsonObject()) : DefaultConfig, Cloneable {

	companion object {

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * This method adds all values to the {@link JsonObject} when the key of the entry is not in it
		 *
		 * @see #default(String, Any?)
		 * @since 15.10.2018
		 */
		fun JsonObject.default(entries: Map<String, Any?>): Unit = entries.forEach { this.default(it.key, it.value) }


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * This method adds the {@code value} to the {@code JsonObject} when the {@code key} is not in it
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
		 * Get a {@code JsonObject} by name or by the file at the config path by config ({@code GsonConfig}) with null support
		 * This is the config ({@code GsonConfig}) version of {@see multiPlaceJsonObject(JsonElement, String, File, IllegalStateException)} and {@see multiPlaceJsonObject(JsonElement?, String, File)}
		 *
		 * @param config the placeholder for the JsonObject or the String or is null
		 * @param name key name (for the exception message and get the {@code JsonElement} of the {@param config})
		 * @param directory prefix folder
		 * @param canBeNull if is false the result can not be null but it can throw a {@code IllegalStateException} with the reason {@param element} is null
		 * @throws IllegalStateException will be thrown if the {@param element} is not a {@code String} or {@code JsonObject} or {@code null}. Null is not acceptable, if {@code canBeNull} is {@code false}
		 * @since 16.10.2018
		 */
		fun multiPlaceJsonObject(config: GsonConfig, name: String, directory: File, canBeNull: Boolean = true): JsonObject? =
				if (canBeNull) multiPlaceJsonObject(config.getAs<JsonElement>(name), name, directory)
				else multiPlaceJsonObject(config.getAsNotNull<JsonElement>(name), name, directory)

		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Get a {@code JsonObject} by name or by the file at the config path with null support
		 * This is the null accept version of {@see multiPlaceJsonObject(JsonElement, String, File, IllegalStateException)}
		 *
		 * @param element the placeholder for the JsonObject or the String or is null
		 * @param name key name (for the exception message)
		 * @param directory prefix folder
		 * @throws IllegalStateException will be thrown if the {@param element} is not a {@code String}, {@code JsonObject} or {@code null}
		 * @since 16.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun multiPlaceJsonObject(element: JsonElement?, name: String, directory: File): JsonObject? =
				if (element == null) null else multiPlaceJsonObject(element, name, directory, IllegalStateException("$name must a String, JsonObject or null"))


		/**
		 * @author Lars Artmann | LartyHD
		 *
		 * Get a {@code JsonObject} by name or by the file at the config path
		 *
		 * @param element the placeholder for the JsonObject or the String
		 * @param name key name (for the exception message)
		 * @param directory prefix folder
		 * @param exception for change the message of the {@code IllegalStateException}
		 * @throws IllegalStateException will be thrown if the {@param element} is not a {@code String} or {@code JsonObject}
		 * @since 16.10.2018
		 */
		@Suppress("MemberVisibilityCanBePrivate")
		fun multiPlaceJsonObject(
				element: JsonElement,
				name: String,
				directory: File,
				exception: IllegalStateException = IllegalStateException("$name must a String or JsonObject")
		): JsonObject = when (element) {
			is JsonPrimitive -> if (element.isString) GsonConfig(ConfigData(
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
			)).load().jsonObject else throw exception
			is JsonObject -> element
			else -> throw exception
		}

	}

	override fun load(): GsonConfig {
		try {
			jsonObject = JsonParser().parse(String(Files.readAllBytes(getFile().toPath()))).asJsonObject
		} catch (ex: IllegalStateException) {
			save()
		}
		return this
	}

	override fun save(): GsonConfig {
		ConfigData.createFoldersIfNotExists(getDirectory())
		ConfigData.createFileIfNotExists(getFile())
		FileWriter(getFile()).apply {
			write(formatJson(jsonObject))
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
		val any = jsonObject[key] as? O
		if (any == null) {
			put(key, JsonNull.INSTANCE)
			save()
		}
		return any
	}


	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Adds the entry to the {@code JsonObject}
	 *
	 * @lastEdit 15.10.2018
	 * @since First known version 31.07.2018
	 */
	override fun <I> put(key: String, value: I): GsonConfig {
		put(key, value, jsonObject)
		return this
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Adds the entry to the {@code JsonObject}
	 *
	 * @since 15.10.2018
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun <I> put(key: String, value: I, jsonObject: JsonObject): GsonConfig {
		if (value is JsonElement) jsonObject.add(key, value) else jsonObject.add(key, JsonPrimitive(value.toString()))
		return this
	}

	@Suppress("MemberVisibilityCanBePrivate")
	fun formatJson(jsonElement: JsonElement): String = GsonBuilder().setPrettyPrinting().create().toJson(jsonElement)

	override fun clone(): GsonConfig = copy(jsonObject)

	@Suppress("MemberVisibilityCanBePrivate")
	fun copy(jsonObject: JsonObject): GsonConfig {
		val gsonConfig = GsonConfig(configData)
		gsonConfig.jsonObject = jsonObject
		return gsonConfig
	}

	override fun toString(): String = "GsonConfig(configData=$configData, jsonObject=$jsonObject)"

}

