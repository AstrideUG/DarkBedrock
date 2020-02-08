/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:23.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface Vector3<N> : Vector2<N> {
    override val x: N
    override val y: N
    val z: N
}

