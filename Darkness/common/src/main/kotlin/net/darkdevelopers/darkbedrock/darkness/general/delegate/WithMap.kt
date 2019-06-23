/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegate

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 01:23.
 * Last edit 05.06.2019
 */
class WithMap<M, O>(private val entries: MutableMap<M, O> = mutableMapOf()) {

    operator fun getValue(input: M, property: KProperty<*>): O? = entries[input]

    operator fun setValue(input: M, property: KProperty<*>, value: O) {
        if (value == null) entries.remove(input) else entries[input] = value
    }

}