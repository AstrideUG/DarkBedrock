/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
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