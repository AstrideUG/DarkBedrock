/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("PairUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * Created on 09.05.2019 22:10.
 * @author Lars Artmann | LartyHD
 */

fun <F, S> Pair<F?, S>.toFirstNotNull(): Pair<F, S>? {
    val first = first ?: return null
    return first to second
}

fun <F, S> Pair<F, S?>.toSecondNotNull(): Pair<F, S>? {
    val second = second ?: return null
    return first to second
}

fun <F, S> Pair<F?, S?>.toNotNull(): Pair<F, S>? {
    val first = first ?: return null
    val second = second ?: return null
    return first to second
}
