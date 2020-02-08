/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.events.api

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 06:50.
 *
 * Represents an interface to perform direct dispatch of an event. This makes integration easier to
 * achieve with platforms such as RxJava.
 *
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
@FunctionalInterface
interface EventHandler<E> {

    fun execute(event: E)

}