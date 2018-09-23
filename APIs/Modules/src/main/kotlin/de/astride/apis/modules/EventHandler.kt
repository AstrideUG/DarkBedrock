/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.09.2018 02:01.
 * Last edit 06.09.2018
 */
object EventHandler {

    private val events = mutableMapOf<Event, Set<() -> Unit>>()

    fun call(event: Event) = events[event]?.forEach { it() }

    fun register(event: Event) = events[event]?.forEach { it() }

}