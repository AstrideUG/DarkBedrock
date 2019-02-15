/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.general.minecraft.fetcher

import com.google.gson.JsonArray
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.02.2019 15:20.
 *
 * Helper-class for getting UUIDs of players
 *
 * Current Version: 1.0 (03.02.2019 - 03.02.2019)
 */
object Fetcher {

    /**
     * @param playerName The name of the player
     * @return The [UUID] of the given player
     * @throws IndexOutOfBoundsException
     */
    fun getUUID(playerName: String): UUID {
        val output = getTextFromURL("https://api.mojang.com/users/profiles/minecraft/$playerName")
        val uuid = StringBuilder(output?.substring(7, 39) ?: "")
            .insert(20, '-')
            .insert(16, '-')
            .insert(12, '-')
            .insert(8, '-')
            .toString()
        return UUID.fromString(uuid)
    }

    /**
     * @param uuid The UUID of a player
     * @return The name of the given player
     */
    fun getName(uuid: UUID): String? = getName(uuid.toString())
//
//    /**
//     * @param uuid The UUID of a player (can be trimmed or the normal version)
//     * @return The name of the given player
//     */
//    @Suppress("MemberVisibilityCanBePrivate")
//    fun getName(uuid: String): String? = getTextFromURL(
//        "https://sessionserver.mojang.com/session/minecraft/profile/${uuid.replace("-", "")}"
//    )?.substring(49)?.takeWhile { it != '"' }?.dropLast(1)

    /**
     * @param uuid The UUID of a player (can be trimmed or the normal version)
     * @return The name of the given player
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun getName(uuid: String): String? {
        val url = "https://api.mojang.com/user/profiles/${uuid.replace("-", "")}/names"
        val text = getTextFromURL(url)
        val jsonArray = GsonService.load(text ?: return null) as JsonArray
        return jsonArray.last().asJsonObject["name"]?.asString
    }

    private fun getTextFromURL(url: String): String? {
        val urlConn = URL(url).openConnection()
        urlConn?.getInputStream() ?: return null
        urlConn.readTimeout = 1000

        InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset()).use {
            BufferedReader(it).use { reader ->
                return reader.readText().ifBlank { null }
            }
        }

    }

}