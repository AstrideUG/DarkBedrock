/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("ListUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * Created on 15.05.2019 06:48.
 * @author Lars Artmann | LartyHD
 */

fun <E> MutableList<E>.editTo(id: Int, new: E) {
    removeAt(id)
    add(id, new)
}
