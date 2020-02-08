/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.events.api


import java.util.concurrent.CompletableFuture


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 06:48.
 *
 * Allows groups to register and deregister listeners for event handlers.
 *
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
interface EventManager {

    /**
     * Requests that the specified `listener` listen for events and associate it with the `group`.
     *
     * @param group the group to associate with the listener
     * @param listener the listener to register
     */
    fun register(group: Any, listener: Any)

    /**
     * Requests that the specified `handler` listen for events and associate it with the `group`.
     *
     * @param group the group to associate with the handler
     * @param eventClass the class for the event handler to register
     * @param handler the handler to register
     * @param <E> the event type to handle
    </E> */
    fun <E> register(group: Any, eventClass: Class<E>, handler: EventHandler<E>) =
        register(group, eventClass, PostOrder.NORMAL, handler)

    /**
     * Requests that the specified `handler` listen for events and associate it with the `group`.
     *
     * @param group the group to associate with the handler
     * @param eventClass the class for the event handler to register
     * @param postOrder the order in which events should be posted to the handler
     * @param handler the handler to register
     * @param <E> the event type to handle
    </E> */
    fun <E> register(group: Any, eventClass: Class<E>, postOrder: Int, handler: EventHandler<E>)

    /**
     * Fires the specified event to the event bus asynchronously. This allows Velocity to continue
     * servicing connections while a group handles a potentially long-running operation such as a
     * database query.
     *
     * @param event the event to fire
     * @return a [CompletableFuture] representing the posted event
     */
    fun <E : Any> fire(event: E): CompletableFuture<E>

    /**
     * Posts the specified event to the event bus and discards the result.
     *
     * @param event the event to fire
     */
    fun fireAndForget(event: Any) {
        fire(event)
    }

    /**
     * Unregisters all listeners for the specified `group`.
     *
     * @param group the group to deregister listeners for
     */
    fun unregisterListeners(group: Any)

    /**
     * Unregisters a specific listener for a specific group.
     *
     * @param group the group associated with the listener
     * @param listener the listener to deregister
     */
    fun unregisterListener(group: Any, listener: Any)

    /**
     * Unregisters a specific event handler for a specific group.
     *
     * @param group the group to associate with the handler
     * @param handler the handler to register
     * @param <E> the event type to handle
    </E> */
    fun <E> unregister(group: Any, handler: EventHandler<E>)
}