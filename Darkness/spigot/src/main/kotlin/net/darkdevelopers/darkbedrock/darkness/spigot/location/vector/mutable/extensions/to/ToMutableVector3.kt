/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.extensions.mutableVector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:03.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:03.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toMutableVector3(): MutableVector3<N> = mutableVector3Of(this, this, this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 04:27.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Vector3<N>.toMutableVector3(
    x: N = this.x,
    y: N = this.y,
    z: N = this.z
): MutableVector3<N> = mutableVector3Of(x, y, z)
