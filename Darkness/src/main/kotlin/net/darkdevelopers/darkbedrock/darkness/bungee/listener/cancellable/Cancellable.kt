package net.darkdevelopers.darkbedrock.darkness.bungee.listener.cancellable

import net.md_5.bungee.api.plugin.Cancellable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018
 */
interface Cancellable {

    fun cancel(cancellable: Cancellable)

    fun cancel(cancellable: Cancellable, boolean: Boolean)

}