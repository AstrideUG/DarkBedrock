/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.collections.Iterable
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.forEach
import kotlin.collections.map
import kotlin.collections.mapNotNull
import kotlin.collections.toMap
import kotlin.reflect.full.createInstance
import kotlin.collections.toList as toKList

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:16.
 * Current Version: 1.0 (09.05.2019 - 04.06.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 12:52.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
@Suppress("FunctionName")
fun JsonArray(input: Iterable<JsonElement>): JsonArray = JsonArray().apply { input.forEach { add(it) } }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 18:54.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun JsonObject(input: Map<String, JsonElement>): JsonObject =
    JsonObject().apply { input.entries.forEach { add(it.key, it.value) } }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:38.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun JsonObject.toMap(): Map<String, Any?> = entrySet().map { (key, value) -> key to value.toObject() }.toMap()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2019 21:47.
 * Current Version: 1.0 (23.05.2019 - 23.05.2019)
 */
fun JsonArray.toList(): List<Any?> = this.toKList().map { it.toObject() }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:27.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
fun Map<String, JsonElement>.toJsonObject(jsonObject: JsonObject = JsonObject()): JsonObject = jsonObject.apply {
    this@toJsonObject.forEach { (value, key) -> add(value, key) }
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:13.
 *
 * only Boolean, Number, String, Char are allowed
 *
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Any.toJsonPrimitive(): JsonPrimitive? = when (this) {
    is Boolean -> JsonPrimitive(this)
    is Number -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is Char -> JsonPrimitive(this)
    else -> null
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:11.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun JsonElement.toObject(): Any? = when (this) {
    is JsonNull -> null
    is JsonPrimitive -> when {
        isBoolean -> asBoolean
        isNumber -> asNumber
        isString -> asString
        else -> IllegalStateException("JsonPrimitive value can not be anyone else as Boolean, Number or String")
    }
    is JsonArray -> this.toList()
    is JsonObject -> this.toMap()
    else -> this
}

fun Any?.toJsonElement(serializeNull: Boolean = false): JsonElement? = when (this) {
    null -> if (serializeNull) JsonNull.INSTANCE else null
    is JsonElement -> this
    is Iterable<*> -> this.toJsonArray()
    is Map<*, Any?> -> this.toJsonObject()
    else -> toJsonPrimitive()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2019 18:58.
 * Current Version: 1.0 (23.05.2019 - 02.06.2019)
 */
fun Iterable<*>.toJsonArray(): JsonArray =
    if (this is JsonArray) this else JsonArray(this.mapNotNull { it?.toJsonElement() })

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2019 18:59.
 * Current Version: 1.0 (23.05.2019 - 23.05.2019)
 */
fun Map<*, Any?>.toJsonObject(): JsonObject =
    JsonObject(this.mapNotNull { (it.key as? String to it.value.toJsonElement()).toNotNull() }.toMap())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:18.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
fun Map<String, Any?>.toJsonObject(
    serializeNull: Boolean = false,
    jsonObject: JsonObject = JsonObject()
): JsonObject = mapNotNull { (key, value) ->
    (key to value.toJsonElement(serializeNull)).toSecondNotNull()
}.toMap().toJsonObject(jsonObject)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.05.2019 03:56.
 * Current Version: 1.0 (13.05.2019 - 13.05.2019)
 */
fun Iterable<JsonElement>.toMap() = mapNotNull {
    val jsonObject = it as? JsonObject ?: return@mapNotNull null
    jsonObject.toMap()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.05.2019 15:47.
 * Current Version: 1.0 (30.05.2019 - 30.05.2019)
 */
inline fun <reified E : JsonElement> ConfigData.load(): E = file.load()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.05.2019 15:53.
 * Current Version: 1.0 (30.05.2019 - 30.05.2019)
 */
inline fun <reified E : JsonElement> File.load(): E = readText().load()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.05.2019 15:55.
 * Current Version: 1.0 (30.05.2019 - 30.05.2019)
 */
inline fun <reified E : JsonElement> String.load(): E = JsonParser().parse(this) as? E ?: E::class.createInstance()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:30.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun JsonElement.save(configData: ConfigData, serializeNulls: Boolean = true): Unit =
    configData.save(this, serializeNulls)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:30.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun JsonElement.save(file: File, serializeNulls: Boolean = true): Unit =
    file.save(this, serializeNulls)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:31.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun ConfigData.save(jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
    file.save(jsonElement, serializeNulls)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:31.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun File.save(jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
    jsonElement.format(serializeNulls).save(this)

fun String.save(file: File) {
    val temp = File("$file.temp")
    FileWriter(temp).also {
        it.write(this)
        it.flush()
        it.close()
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
fun JsonElement.format(serializeNulls: Boolean = true): String = GsonBuilder().setPrettyPrinting().apply {
    if (serializeNulls) serializeNulls()
}.create().toJson(this)