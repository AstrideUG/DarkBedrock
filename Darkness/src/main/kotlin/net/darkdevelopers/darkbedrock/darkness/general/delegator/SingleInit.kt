/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.delegator

import kotlin.reflect.KProperty

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.08.2018 19:41.
 * Last edit 31.08.2018
 */
class SingleInit<T> {
    private var type: T? = null

    @Synchronized
    operator fun getValue(any: Any, property: KProperty<*>): T =
            if (type == null) throw UninitializedPropertyAccessException() else type!!

    operator fun setValue(any: Any, property: KProperty<*>, t: T) =
            if (type == null) throw UnsupportedOperationException("$type is already init") else type = t
}