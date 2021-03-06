/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("IterableUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import kotlin.reflect.KClass

/*
 * Created on 17.10.2018 08:23.
 * @author Lars Artmann | LartyHD
 */

/**
 * Check the map by the types of [value1] and [value2] + [values2]
 *
 * @throws IllegalStateException if "key" is not an instance of [value1] or `null` or "value" is not an instance of [value2] or one of [values2] or `null`
 * @return the checked map but in the code is not declared as checked
 * @see Map<*, *>.check(Array<KClass<*>>, Array<KClass<*>>)
 */
fun <K : Any?, V : Any?> Map<K, V>.check(value1: KClass<*>, value2: KClass<*>, vararg values2: KClass<*>): Map<K, V> =
    check(setOf(value1), setOf(value2, *values2))

/**
 * Check the map by the types of [values1] and [values2]
 *
 * @throws IllegalStateException if [values1] or [values2] is empty
 * @throws IllegalStateException if "key" is not an instance of [values1] or `null` or "value" is not an instance of [values2] or `null`
 * @return the checked map but in the code is not declared as checked
 */
fun <K : Any?, V : Any?> Map<K, V>.check(values1: Set<KClass<*>>, values2: Set<KClass<*>>): Map<K, V> {
    if (values1.isEmpty() || values2.isEmpty()) throw IllegalStateException("value1 or values2 is empty")

    fun KClass<*>.notNullSimpleName() = simpleName.toNonNull("SimpleName of KClass<*>")
    fun Iterable<KClass<*>>.toSimpleName() =
        this.singleOrNull()?.notNullSimpleName() ?: this.map { it.notNullSimpleName() }.toString()

    this.forEach { (key: Any?, value: Any?) ->
        val k = key as Any?
        val v = value as Any?

        if (!values1.isInstanceAndNotNull(key)) throw IllegalStateException("Map \"key\" (\"${k?.javaClass?.kotlin}\") is not a instance of \"${values1.toSimpleName()}\"")
        if (!values2.isInstanceAndNotNull(value)) throw IllegalStateException("Map \"value\" (\"${v?.javaClass?.kotlin}\") is not a instance of \"${values2.toSimpleName()}\"")
    }
    return this
}

/**
 * @return `true` if entries of [Iterable] are not `null` and [KClass<*>#isInstanceAndNotNull(Any?)] of [value]
 */
fun Iterable<KClass<*>>.isInstanceAndNotNull(value: Any?): Boolean {
    this.forEach { if (it.isInstanceAndNotNull(value)) return true }
    return false
}

/**
 * @return [V] if [value] is [KClass<*>#isInstanceAndNotNull([value]: [V])] if the cast is successful else `null`
 */
fun <V : Any?> KClass<*>.castedInstanceAndNotNull(value: V): V? =
    if (this.isInstanceAndNotNull(value)) this as? V else null

/**
 * @return 'true' if [value] is not null and [KClass<*>#isInstanceAndNotNull([value]: [V])]
 */
fun <V : Any?> KClass<*>.isInstanceAndNotNull(value: V): Boolean = value != null && this.isInstance(value)

/**
 * Create [MutableMap<K, V>] changed in [function] and {@return [Map<K, V>]}
 */
fun <K : Any?, V : Any?> of(function: MutableMap<K, V>.() -> Unit): Map<K, V> =
    mutableMapOf<K, V>().apply(function).toMap()

// TODO: Add it to Kotlin
@JvmName("countWith")
inline fun <T> Iterable<T>.count(predicate: (T) -> Int): Int {
    if (this is Collection && isEmpty()) return 0
    var count = 0
    for (element in this) count += predicate(element)
    return count
}