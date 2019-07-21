/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("StringUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * Created on 24.05.2019 07:11.
 * @author Lars Artmann | LartyHD
 */

infix fun String.notSame(other: String): Boolean = !this.equals(other, ignoreCase = true)
infix fun String.same(other: String): Boolean = this.equals(other, ignoreCase = true)

inline fun <reified T> simpleName(): String = T::class.java.simpleName.orEmpty()