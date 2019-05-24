/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegate

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2019 05:47.
 * Last edit 24.05.2019
 */
class MapCheckByCollection<T, O : MutableCollection<*>>(
    private val entries: MutableMap<T, O> = mutableMapOf(),
    private val output: () -> O
) {

    operator fun getValue(input: T, property: KProperty<*>): O =
        entries.getOrPut(input) { output() }

    operator fun setValue(input: T, property: KProperty<*>, value: O) {
        entries[input] = value
    }

}
