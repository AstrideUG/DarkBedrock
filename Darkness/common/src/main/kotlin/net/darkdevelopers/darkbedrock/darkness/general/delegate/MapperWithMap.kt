/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegate

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 01:23.
 * Last edit 05.06.2019
 */
class MapperWithMap<I, M, O>(
    private val entries: MutableMap<M, O> = mutableMapOf(),
    private val mapped: I.() -> M
) {

    operator fun getValue(input: I, property: KProperty<*>): O? = entries[input.mapped()]

    operator fun setValue(input: I, property: KProperty<*>, value: O) {
        if (value == null) entries.remove(input.mapped()) else entries[input.mapped()] = value
    }

}