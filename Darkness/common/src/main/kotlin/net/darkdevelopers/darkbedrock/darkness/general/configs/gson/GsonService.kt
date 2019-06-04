/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.10.2018 17:36.
 * Current Version: 1.1.1 (20.10.2018 - 04.04.2019)
 */
@Suppress("DEPRECATION")
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
     * @since 1.0 (20.10.2018 - 30.05.2019)
     */
    @Deprecated(
        "use file.load<JsonElement>()", ReplaceWith(
            "file.load<JsonElement>()",
            "net.darkdevelopers.darkbedrock.darkness.general.functions.load"
        )
    )
    fun load(file: File): JsonElement = load(file.readText())

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
     * Created by Lars Artmann | LartyHD on 04.04.2019 19:27.
     * Current Version: 1.1.1 (04.04.2019 - 04.04.2019)
     */
    fun save(configData: ConfigData, output: String): Unit = save(configData.file, output)

    /**
     * @author Lars Artmann | LartyHD
     *
     * Saves the [JsonElement] into the [File] of the [ConfigData] with [formatJson]
     *
     * @param serializeNulls see [formatJson]
     * @since 1.0 (20.10.2018 - 30.05.2019)
     */
    fun save(file: File, jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
        save(file, formatJson(jsonElement, serializeNulls))

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 04.04.2019 19:26.
     * Current Version: 1.1.1 (04.04.2019 - 04.04.2019)
     */
    fun save(file: File, output: String) {
        val temp = File("$file.temp")
        FileWriter(temp).apply {
            write(output)
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


}