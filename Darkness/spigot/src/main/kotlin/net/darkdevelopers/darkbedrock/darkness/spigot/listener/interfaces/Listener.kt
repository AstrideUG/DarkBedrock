package net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces

import net.darkdevelopers.darkbedrock.darkness.spigot.listener.cancellable.DefaultCancellable
import net.darkdevelopers.darkbedrock.darkness.spigot.permissions.requests.SimpleHasPermission

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:58.
 * Last edit 02.06.2018 (15.02.2019 added deprecated)
 */
@Suppress("DEPRECATION")
interface Listener : DefaultCancellable, SimpleHasPermission, org.bukkit.event.Listener {

    fun register()

    fun unregister()

    fun reload()

}