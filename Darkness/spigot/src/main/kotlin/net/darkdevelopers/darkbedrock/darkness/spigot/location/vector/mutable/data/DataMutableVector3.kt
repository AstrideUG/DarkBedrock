/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:51.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataMutableVector3<N>(
    override var x: N,
    override var y: N,
    override var z: N
) : MutableVector3<N> {
    constructor(vector2D: Vector2<N>, z: N) : this(vector2D.x, vector2D.y, z)
}