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
class MapperMapCheckByCollection<I, M, O : MutableCollection<*>>(
    private val entries: MutableMap<M, O> = mutableMapOf(),
    private val mapped: I.() -> M,
    private val output: () -> O
) {

    operator fun getValue(input: I, property: KProperty<*>): O =
        entries.getOrPut(input.mapped()) { output() }

    operator fun setValue(input: I, property: KProperty<*>, value: O) {
        entries[input.mapped()] = value
    }

}
