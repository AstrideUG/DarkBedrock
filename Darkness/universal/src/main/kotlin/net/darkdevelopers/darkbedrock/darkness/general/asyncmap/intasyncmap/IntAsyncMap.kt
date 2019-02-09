/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.asyncmap.intasyncmap

import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.07.2018 16:42.
 * Last edit 31.07.2018
 */
interface IntAsyncMap {

    fun get(uuid: UUID, key: String, lambda: (Int) -> Unit)

    fun set(uuid: UUID, key: String, value: Int, lambda: () -> Unit)

    fun add(uuid: UUID, key: String, lambda: () -> Unit)

    fun add(uuid: UUID, key: String, count: Int, lambda: () -> Unit)

    fun remove(uuid: UUID, key: String, lambda: () -> Unit)

    fun remove(uuid: UUID, key: String, count: Int, lambda: () -> Unit)

    fun hasEnough(uuid: UUID, key: String, count: Int, lambda: (Boolean) -> Unit)

    fun removeIfEnough(uuid: UUID, key: String, count: Int, lambda: () -> Unit)

    fun removeIfEnough(uuid: UUID, key: String, count: Int, onSuccess: () -> Unit, onFail: () -> Unit)

}