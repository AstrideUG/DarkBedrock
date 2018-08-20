/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.asyncmap.intasyncmap

import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 16:31.
 * Last edit 31.07.2018
 */
interface DefaultIntAsyncMap : IntAsyncMap {

    override fun add(uuid: UUID, key: String, lambda: () -> Unit) = add(uuid, key, 1, lambda)

    override fun add(uuid: UUID, key: String, count: Int, lambda: () -> Unit) =
            get(uuid, key) { set(uuid, key, it + count) { lambda() } }

    override fun remove(uuid: UUID, key: String, lambda: () -> Unit) = remove(uuid, key, 1, lambda)

    override fun remove(uuid: UUID, key: String, count: Int, lambda: () -> Unit) =
            get(uuid, key) { set(uuid, key, it - count) { lambda() } }

    override fun hasEnough(uuid: UUID, key: String, count: Int, lambda: (Boolean) -> Unit) =
            get(uuid, key) { if (it >= count) lambda(true) else lambda(false) }

    override fun removeIfEnough(uuid: UUID, key: String, count: Int, lambda: () -> Unit) = removeIfEnough(uuid, key, count, lambda) {}

    override fun removeIfEnough(uuid: UUID, key: String, count: Int, onSuccess: () -> Unit, onFail: () -> Unit) = hasEnough(uuid, key, count) { if (it) remove(uuid, key, count) { onSuccess() } else onFail() }

}