/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2019 07:11.
 * Last edit 24.05.2019
 */

infix fun String.notSame(other: String): Boolean = !this.equals(other, ignoreCase = true)
infix fun String.same(other: String): Boolean = this.equals(other, ignoreCase = true)

inline fun <reified T> simpleName(): String = T::class.java.simpleName.orEmpty()