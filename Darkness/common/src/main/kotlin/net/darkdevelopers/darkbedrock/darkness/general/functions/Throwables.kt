/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/**
 * Created on 21.07.2019 01:46.
 * @author Lars Artmann | LartyHD
 */

fun Throwable.crypt(): String = "${message?.sha256()}:${stackTrace.joinToString("\n").sha256()}"

inline fun cryptThrowable(code: () -> Unit): String? = try {
    code()
    null
} catch (throwable: Throwable) {
    throwable.crypt()
}