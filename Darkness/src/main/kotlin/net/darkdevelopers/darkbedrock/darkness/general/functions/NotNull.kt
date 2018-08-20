/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 12:50.
 * Last edit 20.08.2018
 */
fun <T : Any> T?.toNonNull(name: String) = this ?: throw NullPointerException("$name can not be null")

inline fun <reified T : Any> T?.toNonNull() = toNonNull(T::class.java.simpleName)

inline fun <reified T : Any> T?.toNonNull(name: String, lambda: (T) -> Unit) = lambda(toNonNull(name))

inline fun <reified T : Any> T?.toNonNull(lambda: (T) -> Unit) = toNonNull(T::class.java.simpleName, lambda)