/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.general.minecraft.fetcher

import com.google.gson.JsonArray
import net.darkdevelopers.darkbedrock.darkness.general.functions.getTextFromURL
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.02.2019 15:20.
 *
 * Helper-class for getting UUIDs of players
 *
 * Current Version: 1.0 (03.02.2019 - 25.04.2019)
 */
//TODO: Move to Spigot
object Fetcher {

    private val nameCash = mutableMapOf<String, String>() //UUID, NAME
    private val uuidCash = mutableMapOf<String, UUID>() //NAME, UUID

    /**
     * @param playerName The name of the player
     * @return The [UUID] of the given player
     * @throws IndexOutOfBoundsException
     * TODO add try catch java.io.IOException
     */
    fun getUUID(playerName: String): UUID {
        val cashed = uuidCash[playerName]
        if (cashed != null) return cashed

        val output = getTextFromURL("https://api.mojang.com/users/profiles/minecraft/$playerName")
        val uuid = StringBuilder(output?.substring(7, 39) ?: "")
            .insert(20, '-')
            .insert(16, '-')
            .insert(12, '-')
            .insert(8, '-')
            .toString()
        return UUID.fromString(uuid).apply { uuidCash += playerName to this }
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
     * TODO add try catch java.io.IOException
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun getName(uuid: String): String? {
        val cashed = nameCash[uuid]
        if (cashed != null) return cashed

        val url = "https://api.mojang.com/user/profiles/${uuid.replace("-", "")}/names"
        val text = getTextFromURL(url)
        val jsonArray = text?.load() as? JsonArray ?: return null
        return jsonArray.last().asJsonObject["name"]?.asString?.apply { nameCash += uuid to this }
    }

}