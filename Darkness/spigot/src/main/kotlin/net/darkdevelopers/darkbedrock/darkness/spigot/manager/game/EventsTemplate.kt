/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.manager.game

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.unregister
import org.bukkit.event.Listener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:21.
 * Current Version: 1.0 (05.05.2019 - 13.05.2019)
 */
open class EventsTemplate(
    val listener: MutableCollection<Listener> = mutableSetOf()
) {

    protected fun Listener.add() {
        listener += this
    }

    open fun reset() {
        listener.unregister()
        listener.clear()
    }

}