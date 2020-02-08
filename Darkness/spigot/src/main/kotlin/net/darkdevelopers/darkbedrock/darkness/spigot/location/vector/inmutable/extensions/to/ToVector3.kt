/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:00.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:00.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toVector3(): Vector3<N> = vector3Of(this, this, this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Vector3<N>.toVector3(
    x: N = this.x,
    y: N = this.y,
    z: N = this.z
): Vector3<N> = vector3Of(x, y, z)