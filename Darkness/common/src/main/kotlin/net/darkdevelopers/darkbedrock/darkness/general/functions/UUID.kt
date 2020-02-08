/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")
@file:JvmName("UUIDUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.util.*

/*
 * Created on 24.05.2019 01:52.
 * @author Lars Artmann | LartyHD
 */

/**
 * Created on 24.05.2019 01:46.
 *
 * @throws IllegalArgumentException if string has not 4 `-`
 * @throws NumberFormatException if string has `--` or strings between `-` dont match with `[0-9]`
 *
 * @author Lars Artmann | LartyHD
 */
fun String.toUUID(): UUID = UUID.fromString(this)

fun String.toUUIDOrNull(): UUID? = try {
    this.toUUID()
} catch (ex: IllegalArgumentException) {
    null
} catch (ex: NumberFormatException) {
    null
}
