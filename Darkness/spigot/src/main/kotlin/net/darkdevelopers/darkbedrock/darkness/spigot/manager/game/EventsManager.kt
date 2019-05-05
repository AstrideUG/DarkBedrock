/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.manager.game

import org.bukkit.event.Listener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:21.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
open class EventsManager(
    protected val listener: MutableCollection<Listener> = mutableSetOf()
) {

    protected fun Listener.add() {
        listener += this
    }

}