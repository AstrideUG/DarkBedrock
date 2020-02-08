/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 12:59.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
interface Lookable<N> {
    val yaw: N
    val pitch: N
}
