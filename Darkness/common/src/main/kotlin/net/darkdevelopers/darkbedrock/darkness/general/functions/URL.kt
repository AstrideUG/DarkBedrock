/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("URLUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import net.darkdevelopers.darkbedrock.darkness.general.configs.config
import java.io.InputStreamReader
import java.net.ConnectException
import java.net.URL

/**
 * Created on 25.04.2019 02:32.
 * @author Lars Artmann | LartyHD
 */
fun getTextFromURL(url: String, timeout: Int = config.textFromUrlDefaultTimeoutInMillis): String? {
    return try {
        val urlConn = URL(url).openConnection()
        urlConn.readTimeout = timeout
        urlConn.useCaches = false
        urlConn?.getInputStream() ?: return null

        InputStreamReader(urlConn.getInputStream()).use {
            return it.readText().ifBlank { null }
        }

    } catch (ex: ConnectException) {
        null
    }
}