/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("FormattingUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.text.NumberFormat
import java.util.*

/*
 * Created on 26.06.2019 00:09.
 * @author Lars Artmann | LartyHD
 */

@JvmOverloads
fun Int.format(locale: Locale = Locale.GERMANY): String = NumberFormat.getInstance(locale).format(toLong())

