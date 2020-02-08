/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.builder.interfaces

/**
 * Created by Segdo aka. Dominik Milenko on 11.05.2018 08:34.
 * Project: Darkness
 *
 * Web: https://segdogames.com
 * Mail: segdo@segdogames.com
 */
interface Builder<out T> {

    fun build(): T

}