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
import java.nio.file.StandardCopyOption

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.10.2018 17:36.
 * Current Version: 1.0 (20.10.2018 - 18.03.2019)
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
     * Cast the loaded [JsonElement] by [ConfigData] to [J]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    private fun <J : JsonElement> loadAs(
        configData: ConfigData,
        name: String,
        check: (JsonElement) -> Boolean,
        cast: (JsonElement) -> J
    ): J {
        val load = load(configData.file)
        return if (check(load)) cast(load) else throw ClassCastException("The loaded JsonElement (${configData.file}) is not a $name")
    }

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [ConfigData] to [JsonObject]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun loadAsJsonObject(configData: ConfigData): JsonObject = loadAsJsonObject(configData.file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [ConfigData] to [JsonArray]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun loadAsJsonArray(configData: ConfigData): JsonArray = loadAsJsonArray(configData.file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Converts the content of a [File] content into a [JsonElement]
     *
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun load(file: File): JsonElement = load(String(Files.readAllBytes(file.toPath())))

    /**
     * @author Lars Artmann | LartyHD
     *
     * Converts the content of a [String] content into a [JsonElement]
     *
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun load(string: String): JsonElement = JsonParser().parse(string)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [File] to [J]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    private fun <J : JsonElement> loadAs(
        file: File,
        name: String,
        check: (JsonElement) -> Boolean,
        cast: (JsonElement) -> J
    ): J {
        val load = load(file)
        return if (check(load)) cast(load) else throw ClassCastException("The loaded JsonElement is not a $name")
    }

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [File] to [JsonObject]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 22.12.2018)
     */
    fun loadAsJsonObject(file: File): JsonObject =
        loadAs(file, "JsonObject", { file.isFile and (it.isJsonObject or (file.length() == 0L)) }, {
            if (file.length() == 0L) JsonObject() else it.asJsonObject
        })

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [File] to [JsonArray]
     *
     * @throws ClassCastException when [check] is `false`
     * @since 1.0 (20.10.2018 - 22.12.2018)
     */
    fun loadAsJsonArray(file: File): JsonArray =
        loadAs(file, "JsonArray", { file.isFile and (it.isJsonArray or (file.length() == 0L)) }, {
            if (file.length() == 0L) JsonArray() else it.asJsonArray
        })

    /**
     * @author Lars Artmann | LartyHD
     *
     * Saves the [JsonElement] into the [File] of the [ConfigData] with [formatJson]
     *
     * @param configData used for the [File]
     * @param serializeNulls see [formatJson]
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun save(configData: ConfigData, jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
        save(configData.file, jsonElement, serializeNulls)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Saves the [JsonElement] into the [File] of the [ConfigData] with [formatJson]
     *
     * @param serializeNulls see [formatJson]
     * @since 1.0 (20.10.2018 - 18.03.2019)
     */
    fun save(file: File, jsonElement: JsonElement, serializeNulls: Boolean = true) {
        val temp = File("$file.temp")
        FileWriter(temp).apply {
            write(formatJson(jsonElement, serializeNulls))
            flush()
            close()
        }
        Files.move(temp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
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
    fun mapByJson(jsonObject: JsonObject): Map<String, Any?> =
        Gson().fromJson<Map<String, Any?>>(jsonObject, Map::class.java).toNonNull("Map")


}