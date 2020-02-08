/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.events.api


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 06:57.
 *
 * An annotation that indicates that this method can be used to listen for an event from the proxy.
 *
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */

@Target(AnnotationTarget.FUNCTION)
annotation class Subscribe(
    /**
     * The order events will be posted to this listener.
     *
     * @return the order
     */
    val order: Int = PostOrder.NORMAL
)