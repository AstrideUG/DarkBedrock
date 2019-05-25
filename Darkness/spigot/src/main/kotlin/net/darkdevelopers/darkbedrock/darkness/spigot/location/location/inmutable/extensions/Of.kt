/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.data.DataLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.lookableOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:38.
 * Last edit 25.05.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:48.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, N> locationOf(world: W, x: N, y: N, z: N): Location<W, Vector3<N>> = locationOf(world, vector3Of(x, y, z))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:52.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> locationOf(world: W, vector: V): Location<W, V> = DataLocation(world, vector)


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:49.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, N, F> lookableLocationOf(
    world: W,
    x: N,
    y: N,
    z: N,
    yaw: F,
    pitch: F
): LookableLocation<W, Vector3<N>, Lookable<F>> = lookableLocationOf(world, vector3Of(x, y, z), yaw, pitch)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:50.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, F> lookableLocationOf(
    world: W,
    vector: V,
    yaw: F,
    pitch: F
): LookableLocation<W, V, Lookable<F>> = lookableLocationOf(world, vector, lookableOf(yaw, pitch))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 10:01.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, F> lookableLocationOf(
    location: Location<W, V>,
    yaw: F,
    pitch: F
): LookableLocation<W, V, Lookable<F>> = lookableLocationOf(location, lookableOf(yaw, pitch))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:51.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> lookableLocationOf(
    location: Location<W, V>,
    lookable: L
): LookableLocation<W, V, L> = lookableLocationOf(location.world, location.vector, lookable)


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:43.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> lookableLocationOf(
    world: W,
    vector: V,
    lookable: L
): LookableLocation<W, V, L> = DataLookableLocation(world, vector, lookable)
