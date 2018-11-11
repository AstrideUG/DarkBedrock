package net.darkdevelopers.darkbedrock.darkness.spigot.listener.cancellable

import org.bukkit.event.Cancellable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018
 */
interface Cancellable {

	fun cancel(cancellable: Cancellable)

	fun cancel(cancellable: Cancellable, boolean: Boolean)

}