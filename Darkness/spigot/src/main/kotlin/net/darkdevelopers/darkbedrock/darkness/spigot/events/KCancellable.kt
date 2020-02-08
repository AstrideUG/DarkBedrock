/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.events

import org.bukkit.event.Cancellable

/**
 * Created on 21.07.2019 03:47.
 * @author Lars Artmann | LartyHD
 */
interface KCancellable : Cancellable {

    var cancellable: Boolean

    override fun isCancelled(): Boolean = cancellable

    override fun setCancelled(value: Boolean) {
        cancellable = value
    }

}