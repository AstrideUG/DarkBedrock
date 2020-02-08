/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands

/**
 * Created on 25.06.2019 02:08.
 * @author Lars Artmann | LartyHD
 */
interface CommandConfig {
    val commandName: String
    val permission: String
    val permissionMessage: String
    val usage: String
    val minLength: Int
    val maxLength: Int
    val aliases: Set<String>
}
