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
 * Current Version: 1.1 (20.10.2018 - 01.04.2019)
 */
object GsonService {

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
     * Converts the content of a [File] content into a [JsonElement]
     *
     * @since 1.0 (20.10.2018 - 20.10.2018)
     */
    fun load(file: File): JsonElement = load(String(Files.readAllBytes(file.toPath())))

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
     * Cast the loaded [JsonElement] by [File] to [J]
     *
     * @since 1.1 (20.10.2018 - 01.04.2019)
     */
    inline fun <reified J : JsonElement> loadAs(file: File): J? = load(file) as? J

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [ConfigData] to [J]
     *
     * @since 1.1 (20.10.2018 - 01.04.2019)
     */
    inline fun <reified J : JsonElement> loadAs(configData: ConfigData): J? = loadAs(configData.file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [File] to [JsonObject]
     *
     * @since 1.1 (20.10.2018 - 01.04.2019)
     */
    @Deprecated(
        "", ReplaceWith(
            "loadAs(file) ?: JsonObject()",
            "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService.loadAs",
            "com.google.gson.*"
        )
    )
    fun loadAsJsonObject(file: File): JsonObject? = loadAs(file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [ConfigData] to [JsonObject]
     *
     * @since 1.1 (20.10.2018 - 01.04.2019)
     */
    @Deprecated(
        "", ReplaceWith(
            "loadAs(configData) ?: JsonObject()",
            "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService.loadAsJsonObject",
            "com.google.gson.*"
        )
    )
    fun loadAsJsonObject(configData: ConfigData): JsonObject? = loadAs(configData.file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [File] to [JsonArray]
     *
     * @since 1.0 (20.10.2018 - 01.04.2019)
     */
    @Deprecated(
        "", ReplaceWith(
            "loadAs(file) ?: JsonArray()",
            "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService.loadAs",
            "com.google.gson.*"
        )
    )
    fun loadAsJsonArray(file: File) = loadAs<JsonArray>(file)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Cast the loaded [JsonElement] by [ConfigData] to [JsonArray]
     *
     * @since 1.0 (20.10.2018 - 01.04.2019)
     */
    @Deprecated(
        "", ReplaceWith(
            "loadAs(configData) ?: JsonArray()",
            "net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService.loadAsJsonArray",
            "com.google.gson.*"
        )
    )
    fun loadAsJsonArray(configData: ConfigData): JsonArray? = loadAs(configData.file)

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