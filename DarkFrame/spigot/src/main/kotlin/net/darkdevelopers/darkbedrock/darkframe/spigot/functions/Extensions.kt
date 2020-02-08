/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.functions

/*
 * Created on 25.06.2019 04:15.
 * @author Lars Artmann | LartyHD
 */

fun String.formatForCancellable(): String = drop(3).replaceFirst("Cancel", "")