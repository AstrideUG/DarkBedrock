package net.darkdevelopers.darkbedrock.darkness.general.asyncmap

import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 16:31.
 * Last edit 31.07.2018
 */
interface AsyncMap {

    fun get(uuid: UUID, key: String, lambda: (Any) -> Unit)

    fun set(uuid: UUID, key: String, value: Any, lambda: () -> Unit)

    fun add(uuid: UUID, key: String, any: Any, lambda: () -> Unit)

    fun remove(uuid: UUID, key: String, lambda: () -> Unit)

}