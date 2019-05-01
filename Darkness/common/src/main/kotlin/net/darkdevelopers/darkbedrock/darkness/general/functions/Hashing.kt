/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.security.MessageDigest

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2019 22:10.
 *
 * Hashing Utils
 * @author Sam Clarke <www.samclarke.com>
 * @license MIT
 *
 * Current Version: 1.0 (01.05.2019 - 01.05.2019)
 */

private const val HEX_CHARS: String = "0123456789ABCDEF"

fun String.md5(): String = hash("MD5")

fun String.sha1(): String = hash("SHA-1")

fun String.sha224(): String = hash("SHA-224")

fun String.sha256(): String = hash("SHA-256")

fun String.sha384(): String = hash("SHA-384")

fun String.sha512(): String = hash("SHA-512")

/**
 * Supported algorithms on Android:
 *
 * Algorithm	Supported API Levels
 * MD5          1+
 * SHA-1	    1+
 * SHA-224	    1-8,22+
 * SHA-256	    1+
 * SHA-384	    1+
 * SHA-512	    1+
 */
private fun String.hash(type: String): String {

    val bytes = MessageDigest
        .getInstance(type)
        .digest(this.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}

//Other method (https://www.samclarke.com/kotlin-hash-strings/)
//private fun String.hash(type: String): String = MessageDigest
//    .getInstance(type)
//    .digest(input.toByteArray())
//    .joinToString(separator = "") { String.format("%02X", it) }