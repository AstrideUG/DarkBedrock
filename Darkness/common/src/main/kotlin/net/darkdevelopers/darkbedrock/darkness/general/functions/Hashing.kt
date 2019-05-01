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

fun md5(input: String): String = hashString("MD5", input)

fun sha1(input: String): String = hashString("SHA-1", input)

fun sha224(input: String): String = hashString("SHA-224", input)

fun sha256(input: String): String = hashString("SHA-256", input)

fun sha384(input: String): String = hashString("SHA-384", input)

fun sha512(input: String): String = hashString("SHA-512", input)

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
private fun hashString(type: String, input: String): String {

    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}
