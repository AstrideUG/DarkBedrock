/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegate

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2019 23:54.
 * Current Version: 1.0 (23.05.2019 - 23.05.2019)
 */
@Suppress("unused")
class MapperCheckByCollection<I : Any?, C : Any?>(
    private val collection: MutableCollection<C> = mutableSetOf(),
    private val mapped: I.() -> C
) {

    operator fun getValue(thisRef: I, property: KProperty<*>): Boolean = thisRef.mapped() in collection

    operator fun setValue(thisRef: I, property: KProperty<*>, value: Boolean) {
        val mapped: C = thisRef.mapped()
        if (value) collection += mapped else collection -= mapped
    }

}