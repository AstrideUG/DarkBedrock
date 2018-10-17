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
 * Check the map by the types of {@param value1} and {@param value2}
 *
 * @throws IllegalStateException if "key" is not an instance of {@param value1} or "value" is not an instance of {@param value2}
 * @return the checked map but in the code is not declared as checked
 * @since 17.10.2018
 */
@Suppress("unused")
fun Map<*, *>.check(value1: KClass<*>, value2: KClass<*>): Map<*, *> = check(arrayOf(value1), arrayOf(value2))

/**
 * @author Lars Artmann | LartyHD
 *
 * Check the map by the types of {@param value1} and {@param value2}
 *
 * @throws IllegalStateException if {@param value1} or {@param value2} is empty
 * @throws IllegalStateException if "key" is not an instance of {@param value1} or "value" is not an instance of {@param value2}
 * @return the checked map but in the code is not declared as checked
 * @since 17.10.2018
 */
@Suppress("unused")
fun Map<*, *>.check(values1: Array<KClass<*>>, values2: Array<KClass<*>>): Map<*, *> {
	if (values1.isEmpty() || values2.isEmpty()) throw IllegalStateException("value1 or values2 is empty")

	fun KClass<*>.notNullSimpleName() = simpleName.toNonNull("SimpleName of KClass<*>")
	fun Array<KClass<*>>.toSimpleName() =
			this.singleOrNull()?.notNullSimpleName() ?: this.map { it.notNullSimpleName() }.toString()

	fun Iterator<KClass<*>>.isInstance(value: Any?): Boolean {
		this.forEach { if (!it.isInstance(value)) return true }
		return false
	}

	this.forEach { key, value ->
		if (values1.iterator().isInstance(key)) throw IllegalStateException("Map \"key\" is not a instance of \"${values1.toSimpleName()}\"")
		if (values2.iterator().isInstance(value)) throw IllegalStateException("Map \"value\" is not a instance of \"${values2.toSimpleName()}\"")
	}
	return this
}