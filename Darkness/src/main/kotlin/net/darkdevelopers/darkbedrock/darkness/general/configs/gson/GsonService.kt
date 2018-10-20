/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import java.io.File
import java.io.FileWriter
import java.nio.file.Files

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.10.2018 17:36.
 * Current Version: 1.0 (20.10.2018 - 20.10.2018)
 */
object GsonService {

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Converts the content of a [File] content into a [JsonElement]
	 *
	 * @param configData used for the [File]
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	fun load(configData: ConfigData): JsonElement = load(configData.file)

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Converts the content of a [File] content into a [JsonElement]
	 *
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	fun load(file: File): JsonElement = JsonParser().parse(String(Files.readAllBytes(file.toPath())))

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Saves the [JsonElement] into the [File] of the [ConfigData] with [formatJson]
	 *
	 * @param configData used for the [File]
	 * @param serializeNulls see [formatJson]
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	fun save(configData: ConfigData, jsonElement: JsonElement, serializeNulls: Boolean = true): Unit = save(configData.file, jsonElement, serializeNulls)

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Saves the [JsonElement] into the [File] of the [ConfigData] with [formatJson]
	 *
	 * @param serializeNulls see [formatJson]
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	fun save(file: File, jsonElement: JsonElement, serializeNulls: Boolean = true): Unit = FileWriter(file).run {
		this.write(formatJson(jsonElement, serializeNulls))
		this.flush()
		this.close()
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Converts a [JsonElement] to a [String]
	 *
	 * @param serializeNulls is `false`, does not call [GsonBuilder#serializeNulls()]
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun formatJson(jsonElement: JsonElement, serializeNulls: Boolean = true): String {
		val result = GsonBuilder().setPrettyPrinting()
		if (serializeNulls) result.serializeNulls()
		return result.create().toJson(jsonElement)
	}

	/**
	 * @author Lars Artmann | LartyHD
	 * @return a [Map] of the [JsonObject]s
	 * @since 1.0 (20.10.2018 - 20.10.2018)
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun mapByJson(jsonObject: JsonObject): Map<String, Any?> = Gson().fromJson<Map<String, Any?>>(jsonObject, Map::class.java).toNonNull("Map")


}