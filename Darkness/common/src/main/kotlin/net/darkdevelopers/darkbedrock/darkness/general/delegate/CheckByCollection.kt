/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegate

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 23.05.2019 23:46.
 * Last edit 23.05.2019
 */
@Suppress("unused")
class CheckByCollection<T : Any?>(private val collection: MutableCollection<T> = mutableSetOf()) {

    operator fun getValue(thisRef: T, property: KProperty<*>): Boolean = thisRef in collection

    operator fun setValue(thisRef: T, property: KProperty<*>, value: Boolean): Unit =
        if (value) collection += thisRef else collection -= thisRef

}