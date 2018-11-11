package net.darkdevelopers.darkbedrock.darkness.bungee.listener.interfaces

import net.darkdevelopers.darkbedrock.darkness.bungee.listener.cancellable.DefaultCancellable
import net.darkdevelopers.darkbedrock.darkness.bungee.permissions.requests.SimpleHasPermission

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:58.
 * Last edit 02.06.2018
 */
interface Listener : DefaultCancellable, SimpleHasPermission, net.md_5.bungee.api.plugin.Listener {

	fun register()

	fun unregister()

	fun reload()

}