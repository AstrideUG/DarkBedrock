/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("NonNullUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*
 * Created on 20.08.2018 12:50.
 * @author Lars Artmann | LartyHD
 */

fun <T : Any> T?.toNonNull(name: String) = this@toNonNull ?: throw NullPointerException("$name can not be null")

inline fun <reified T : Any> T?.toNonNull(): T = toNonNull(T::class.java.simpleName)

@ExperimentalContracts
inline fun <reified T : Any> T?.toNonNull(name: String, lambda: (T) -> Unit) {
    contract { callsInPlace(lambda, InvocationKind.EXACTLY_ONCE) }
    lambda(toNonNull(name))
}

@ExperimentalContracts
inline fun <reified T : Any> T?.toNonNull(lambda: (T) -> Unit): Unit = toNonNull(T::class.java.simpleName, lambda)

fun Map<String, String?>.getOrKey(key: String) = this[key] ?: key
