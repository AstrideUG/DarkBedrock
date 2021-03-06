/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.general.configs

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.*
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.typeOf

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 13:39.
 * Last edit 05.06.2019
 */

@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Undercover

@Suppress("EXPERIMENTAL_API_USAGE")
val defaultMappings: MutableMap<Class<out Any>, (Any?) -> Any?> = mutableMapOf(
    //Kotlin
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
    UUID::class.java to { any -> any?.mapped<String>()?.toUUIDOrNull() }//,
    //Darkness
//    MongoData::class.java to { any -> any?.toConfigMap() }
)

inline operator fun <reified V : Any> Map<in String, V>.getValue(thisRef: Any?, property: KProperty<*>): V =
    mappedBy(property, defaultMappings)

@Suppress("UNCHECKED_CAST")
inline fun <reified V : Any> Map<in String, V>.mappedBy(
    property: KProperty<*>,
    mappings: Map<Class<out Any>, (Any?) -> Any?>
): V {
    val returnType = property.returnType

    val value = get(property.name.formatToConfigPattern())!!
    val mapped = value.mapped<V?>(returnType.jvmErasure, mappings)
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
): O? = if (this is O) this else mappings.entries.find { it.key.kotlin.isSuperclassOf(to) }?.value?.invoke(this) as? O?

fun Any.toConfigMap(): JsonObject = javaClass.declaredMethods.mapNotNull { method ->
    method.isAccessible = true
    val prefix = "get"
    if (!method.name.startsWith(prefix) || method.name.length <= prefix.length) return@mapNotNull null
    if (method.isAnnotationPresent(Undercover::class.java)) return@mapNotNull null
    if (method.parameterCount > 0) {
        println("$javaClass method.parameters.size are bigger than 0 ${method.name}={$method}")
        return@mapNotNull null
    }
    val invoke = method.invoke(this)
    val output = invoke.toJsonElement {
        if (this is Enum<*>) name.toJsonPrimitive() else toConfigMap()
    }
    (method.name.drop(prefix.length).formatToConfigPattern() to output).toNotNull()
}.toMap().toSortedMap().toJsonObject()

fun String.formatToConfigPattern(): String = decapitalize().replace("[A-Z]".toRegex()) { "-${it.value.toLowerCase()}" }

@Suppress("unused")
fun Iterable<KMutableProperty0<*>>.createConfigs(directory: File): Unit = forEach { property ->
    property.createConfig(directory)
}

fun KMutableProperty0<*>.createConfig(directory: File) {

    val configData = toConfigData(directory)
    val values = configData.load<JsonObject>().toMap()

    val createType = returnType.jvmErasure
    val constructor = createType.constructors.find {
        it.parameters.singleOrNull()?.type == typeOf<Map<String, Any?>>()
    } ?: return

    val instance = constructor.call(values)
    setter.call(instance)

    configData.save(instance.toConfigMap())

}

fun KMutableProperty0<*>.toConfigData(directory: File): ConfigData =
    name.formatToConfigPattern().toConfigData(directory)

fun Class<*>.mapFromConfig(directory: File) =
    simpleName?.formatToConfigPattern()?.toConfigData(directory)?.load<JsonObject>()?.toMap() ?: emptyMap()