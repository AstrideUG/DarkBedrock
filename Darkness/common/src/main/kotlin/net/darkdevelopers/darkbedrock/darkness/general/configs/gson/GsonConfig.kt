/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.check
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:18.
 * Last edit 04.06.2019
 */
@Suppress("unused")
object GsonConfig {

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
     * @param jsonObject the placeholder for the [JsonObject] or the String or is null
     * @param name key name (for the exception message and get the [JsonElement] of the [jsonObject]})
     * @param directory prefix folder
     * @param canBeNull if is false the result can not be null but it can throw a [IllegalStateException] with the reason [JsonElement] by [jsonObject] is `null`
     * @throws IllegalStateException will be thrown if the [JsonElement] by [jsonObject] is not a [String] or [JsonObject] or `null`. Null is not acceptable, if [canBeNull] is `false`
     * @since 16.10.2018
     */
    fun multiPlaceJsonObject(
        jsonObject: JsonObject,
        name: String,
        directory: File,
        canBeNull: Boolean = true
    ): JsonObject? {
        val element: JsonElement? = jsonObject[name]
        if (!canBeNull) element!!
        return multiPlaceJsonObject(element, name, directory)
    }

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
        if (element == null) null else multiPlaceJsonObject(
            element,
            name,
            directory,
            IllegalStateException("$name must a String, JsonObject or null")
        )

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
        is JsonPrimitive -> if (element.isString) {
            val configData = ConfigData(
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
            )
            configData.load<JsonObject>()
        } else throw exception
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
            result.putAll(
                replaceValues(
                    /*It is checked at the runtime */ value.check(
                        String::class.java.kotlin,
                        Any::class.java.kotlin
                    ) as Map<String, Any>,
                    oldValue,
                    newValue
                )
            )

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
            result.putAll(
                replaceValuesWithKey(
                    /*It is checked at the runtime */value.check(
                        String::class.java.kotlin,
                        Any::class.java.kotlin
                    ) as Map<String, Any>,
                    oldValue
                )
            )
        }
        return result
    }

    /**
     * @author Lars Artmann | LartyHD
     * @return a [Map] of the [JsonElement]s
     * @since 17.10.2018
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun mapByJson(jsonElement: JsonElement): Map<String, Any?> =
        Gson().fromJson<Map<String, Any?>>(jsonElement, Map::class.java).toNonNull()

}

