/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:26.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableVector3<N> : Vector3<N>, MutableVector2<N> {
    override var x: N
    override var y: N
    override var z: N
}