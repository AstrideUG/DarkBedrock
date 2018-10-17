/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import kotlin.reflect.KClass

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 17.10.2018 08:23.
 * Last edit 17.10.2018
 */

/**
 * @author Lars Artmann | LartyHD
 *
 * Check the map by the types of [values1] and [values2]
 *
 * @throws IllegalStateException if "key" is not an instance of [values1] or "value" is not an instance of [values2]
 * @return the checked map but in the code is not declared as checked
 * @see Map<*, *>.check(Array<KClass<*>>, Array<KClass<*>>)
 * @since 17.10.2018
 */
@Suppress("unused")
fun Map<*, *>.check(value1: KClass<*>, value2: KClass<*>, vararg values2: KClass<*>): Map<*, *> = check(setOf(value1), setOf(value2, *values2))

/**
 * @author Lars Artmann | LartyHD
 *
 * Check the map by the types of [values1] and [values2]
 *
 * @throws IllegalStateException if [values1] or [values2] is empty
 * @throws IllegalStateException if "key" is not an instance of [values1] or "value" is not an instance of [values2]
 * @return the checked map but in the code is not declared as checked
 * @since 17.10.2018
 */
@Suppress("unused")
fun Map<*, *>.check(values1: Set<KClass<*>>, values2: Set<KClass<*>>): Map<*, *> {
	if (values1.isEmpty() || values2.isEmpty()) throw IllegalStateException("value1 or values2 is empty")

	fun KClass<*>.notNullSimpleName() = simpleName.toNonNull("SimpleName of KClass<*>")
	fun Iterable<KClass<*>>.toSimpleName() =
			this.singleOrNull()?.notNullSimpleName() ?: this.map { it.notNullSimpleName() }.toString()

	fun Iterable<KClass<*>>.isInstance(value: Any?): Boolean {
		this.forEach { if (value != null && it.isInstance(value)) return true }
		return false
	}

	this.forEach { key, value ->
		if (!values1.isInstance(key)) throw IllegalStateException("Map \"key\" (\"${key?.javaClass?.kotlin}\") is not a instance of \"${values1.toSimpleName()}\"")
		if (!values2.isInstance(value)) throw IllegalStateException("Map \"value\" (\"${value?.javaClass?.kotlin}\") is not a instance of \"${values2.toSimpleName()}\"")
	}
	return this
}