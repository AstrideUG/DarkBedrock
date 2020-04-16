/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */

@file:JvmName("JsonUtils")
@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import java.io.File
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.map
import kotlin.collections.mapNotNull
import kotlin.reflect.full.createInstance
import kotlin.collections.toList as toKList

/*
 * Created on 09.05.2019 14:16.
 * @author Lars Artmann | LartyHD
 */

@Suppress("FunctionName")
fun JsonArray(input: Iterable<JsonElement>): JsonArray = JsonArray().apply { input.forEach { add(it) } }

@Suppress("FunctionName")
fun JsonObject(input: Map<String, JsonElement>): JsonObject = jsonObjectOf(input)

fun jsonObjectOf(vararg input: Pair<String, JsonElement>): JsonObject = jsonObjectOf(mapOf(*input))

@Suppress("UNCHECKED_CAST")
@JvmName("jsonObjectOfString")
fun jsonObjectOf(vararg input: Pair<String, Any>): JsonObject =
    jsonObjectOf(*input.map { it.toJsonElement() } as Array<out Pair<String, JsonElement>>)

fun jsonObjectOf(input: Map<String, JsonElement>): JsonObject =
    JsonObject().apply { input.entries.forEach { add(it.key, it.value) } }

fun JsonObject.toMap(): Map<String, Any?> = entrySet().map { (key, value) -> key to value.toObject() }.toMap()

fun JsonArray.toList(): List<Any?> = this.toKList().map { it.toObject() }

fun Map<String, JsonElement>.toJsonObject(jsonObject: JsonObject = JsonObject()): JsonObject = jsonObject.apply {
    this@toJsonObject.forEach { (value, key) -> add(value, key) }
}

fun List<String>.toJsonPrimitive() = map { it.toJsonPrimitive() }
fun List<String>.toJsonPrimitiveArray(): JsonArray = JsonArray(this.toJsonPrimitive())

fun Any.toJsonPrimitive(default: Any.() -> JsonPrimitive? = { null }): JsonPrimitive? = when (this) {
    is Boolean -> toJsonPrimitive()
    is Number -> toJsonPrimitive()
    is String -> toJsonPrimitive()
    is Char -> toJsonPrimitive()
    else -> default()
}

fun Boolean.toJsonPrimitive(): JsonPrimitive = JsonPrimitive(this)

fun Number.toJsonPrimitive(): JsonPrimitive = JsonPrimitive(this)

fun String.toJsonPrimitive(): JsonPrimitive = JsonPrimitive(this)

fun Char.toJsonPrimitive(): JsonPrimitive = JsonPrimitive(this)

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

fun Any?.toJsonElement(serializeNull: Boolean = false, defaultForSub: Any.() -> JsonElement? = { null }): JsonElement? =
    when (this) {
        null -> if (serializeNull) JsonNull.INSTANCE else null
        is JsonElement -> this
        is Iterable<*> -> this.toJsonArray(defaultForSub)
        is Map<*, Any?> -> this.toJsonObject(defaultForSub)
        else -> toJsonPrimitive()
    }

fun Iterable<*>.toJsonArray(default: Any.() -> JsonElement? = { null }): JsonArray =
    if (this is JsonArray) this else JsonArray(this.mapNotNull { it?.toJsonElement() ?: it?.default() })

fun Map<*, Any?>.toJsonObject(default: Any.() -> JsonElement? = { null }): JsonObject = JsonObject(this.mapNotNull {
    (it.key as? String to (it.value.toJsonElement() ?: it.value?.default())).toNotNull()
}.toMap())

fun Map<String, Any?>.toJsonObject(
    serializeNull: Boolean = false,
    jsonObject: JsonObject = JsonObject()
): JsonObject = mapNotNull { (key, value) ->
    (key to value.toJsonElement(serializeNull)).toSecondNotNull()
}.toMap().toJsonObject(jsonObject)

fun Iterable<JsonElement>.toMap() = mapNotNull {
    val jsonObject = it as? JsonObject ?: return@mapNotNull null
    jsonObject.toMap()
}

inline fun <reified E : JsonElement> ConfigData.load(): E = file.load()

inline fun <reified E : JsonElement> File.load(): E = readText().load()

inline fun <reified E : JsonElement> String.load(): E = JsonParser().parse(this) as? E ?: E::class.createInstance()

fun JsonElement.save(configData: ConfigData, serializeNulls: Boolean = true): Unit =
    configData.save(this, serializeNulls)

fun JsonElement.save(file: File, serializeNulls: Boolean = true): Unit =
    file.save(this, serializeNulls)

fun ConfigData.save(jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
    file.save(jsonElement, serializeNulls)

fun File.save(jsonElement: JsonElement, serializeNulls: Boolean = true): Unit =
    jsonElement.format(serializeNulls).save(this)

/**
 * Converts a [JsonElement] to a [String]
 *
 * @param serializeNulls is `false`, does not call [GsonBuilder#serializeNulls()]
 * @since 1.0 (20.10.2018 - 20.10.2018)
 */
@Suppress("MemberVisibilityCanBePrivate")
fun JsonElement.format(serializeNulls: Boolean = true): String = GsonBuilder().setPrettyPrinting().apply {
    if (serializeNulls) serializeNulls()
}.create().toJson(this)

fun JsonElement.asString(): String? = if (isJsonNull) null else asString

