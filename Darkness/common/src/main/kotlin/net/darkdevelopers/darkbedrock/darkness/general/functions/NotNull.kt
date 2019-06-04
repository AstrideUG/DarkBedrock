/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 12:50.
 * Last edit 12.11.2018
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
