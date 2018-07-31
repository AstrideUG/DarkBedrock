package net.darkdevelopers.darkbedrock.darkness.spigot.permissions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 13:34.
 * Last edit 07.07.2018
 */
enum class Permissions(private val permission: String) {

    GAME_LOBBY_SETTINGS("dark.settings");

    override fun toString(): String = permission


}