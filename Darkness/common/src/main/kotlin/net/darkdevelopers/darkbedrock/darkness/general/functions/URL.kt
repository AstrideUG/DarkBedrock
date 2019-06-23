/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.io.InputStreamReader
import java.net.ConnectException
import java.net.URL

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 02:32.
 * Current Version: 1.0 (25.04.2019 - 06.05.2019)
 */
fun getTextFromURL(url: String, timeout: Int = 1000): String? {
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