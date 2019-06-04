/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.*
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.jvmErasure

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 13:39.
 * Last edit 29.05.2019
 */


@Suppress("EXPERIMENTAL_API_USAGE")
val defaultMappings: MutableMap<Class<out Any>, (Any?) -> Any?> = mutableMapOf(
    Set::class.java to { any ->
        when (any) {
            is Iterable<*> -> any.toSet()
            is Array<*> -> any.toSet()
            else -> null
        }
    },
    List::class.java to { any ->
        when (any) {
            is Iterable<*> -> any.toList()
            is Array<*> -> any.toList()
            else -> null
        }
    },
    Collection::class.java to { any -> any?.mapped<List<*>>() },
    Iterable::class.java to { any -> any?.mapped<List<*>>() },
    String::class.java to { any -> any.toString() },
    Char::class.java to { any -> any?.mapped<String>()?.singleOrNull() },
    java.lang.Byte::class.java to { any -> any?.mapped<String>()?.toByteOrNull() },
    java.lang.Short::class.java to { any -> any?.mapped<String>()?.toShortOrNull() },
    java.lang.Integer::class.java to { any -> any?.mapped<String>()?.toIntOrNull() },
    java.lang.Long::class.java to { any -> any?.mapped<String>()?.toLongOrNull() },
    java.lang.Float::class.java to { any -> any?.mapped<String>()?.toFloatOrNull() },
    java.lang.Double::class.java to { any -> any?.mapped<String>()?.toDoubleOrNull() },
    BigInteger::class.java to { any -> any?.mapped<String>()?.toBigIntegerOrNull() },
    BigDecimal::class.java to { any -> any?.mapped<String>()?.toBigDecimalOrNull() },
    UByte::class.java to { any -> any?.mapped<String>()?.toUByteOrNull() },
    UShort::class.java to { any -> any?.mapped<String>()?.toUShortOrNull() },
    UInt::class.java to { any -> any?.mapped<String>()?.toUIntOrNull() },
    ULong::class.java to { any -> any?.mapped<String>()?.toULongOrNull() },
    UUID::class.java to { any -> any?.mapped<String>()?.toUUIDOrNull() }
)

inline operator fun <reified V : Any> Map<in String, V>.getValue(thisRef: Any?, property: KProperty<*>): V =
    mappedBy(property, defaultMappings)

@Suppress("UNCHECKED_CAST")
inline fun <reified V : Any> Map<in String, V>.mappedBy(
    property: KProperty<*>,
    mappings: Map<Class<out Any>, (Any?) -> Any?>
): V {
    val returnType = property.returnType

    val value = get(property.name.replace("[A-Z]".toRegex()) { "-${it.value.toLowerCase()}" })!!
    val mapped = value.mapped<V>(returnType.jvmErasure, mappings)
    returnType.arguments.forEach { argument ->
        val clazz = argument.type?.jvmErasure ?: return@forEach
        val transform: (Any?) -> Any? = { it?.mapped(clazz, mappings) }
        when (mapped) {
            is Map<*, *> -> return mapped.mapNotNull { (key, value) -> (key to transform(value)).toSecondNotNull() }.toMap() as V
            is Array<*> -> return mapped.mapNotNull(transform).toTypedArray() as V
            is Iterable<*> -> {
                val result = mapped.mapNotNull(transform)
                return when (mapped) {
                    is MutableSet<*> -> result.toMutableSet()
                    is Set<*> -> result.toSet()
                    is MutableList<*> -> result.toMutableList()
                    else -> result
                } as V
            }
        }
    }
    return mapped ?: value
}

@Suppress("UNCHECKED_CAST")
inline fun <reified O> Any.mapped(
    to: KClass<*> = O::class,
    mappings: Map<Class<out Any>, (Any?) -> Any?> = defaultMappings
): O? = if (this is O) this else mappings.entries.find { it.key.kotlin.isSuperclassOf(to) }?.value?.invoke(this) as O

fun Any.toConfigMap(): JsonObject = javaClass.declaredMethods.mapNotNull { method ->
    method.isAccessible = true
    val prefix = "get"
    if (!method.name.startsWith(prefix)) return@mapNotNull null
    val invoke = method.invoke(this)
    val output = if (invoke is Iterable<*>) {
        JsonArray(invoke.mapNotNull { it?.toConfigMap() })
    } else invoke.toJsonElement()
    (method.name.drop(prefix.length).decapitalize().replace("[A-Z]".toRegex()) {
        "-${it.value.toLowerCase()}"
    } to output).toNotNull()
}.toMap().toJsonObject()
