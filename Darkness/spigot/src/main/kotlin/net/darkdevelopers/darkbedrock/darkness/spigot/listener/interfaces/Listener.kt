/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces

import net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests.SimpleHasPermission

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:58.
 * Last edit 05.06.2019
 */
@Suppress("DEPRECATION")
interface Listener : SimpleHasPermission, org.bukkit.event.Listener {

    fun register()

    fun unregister()

    fun reload()

}