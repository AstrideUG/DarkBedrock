/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

//TODO: Add it to Kotlin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 02:27.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
@JvmName("countWith")
inline fun <T> Iterable<T>.count(predicate: (T) -> Int): Int {
    if (this is Collection && isEmpty()) return 0
    var count = 0
    for (element in this) count += predicate(element)
    return count
}