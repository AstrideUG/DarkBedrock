/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.data.DataMutableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.data.DataMutableLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.lookableOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:04.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:07.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, N> mutableLocationOf(world: W, x: N, y: N, z: N): MutableLocation<W, Vector3<N>> =
    mutableLocationOf(world, vector3Of(x, y, z))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:06.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> mutableLocationOf(world: W, vector: V): MutableLocation<W, V> =
    DataMutableLocation(world, vector)


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:08.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, N, F> mutableLookableLocationOf(
    world: W,
    x: N,
    y: N,
    z: N,
    yaw: F,
    pitch: F
): MutableLookableLocation<W, Vector3<N>, Lookable<F>> =
    mutableLookableLocationOf(world, vector3Of(x, y, z), pitch, yaw)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:08.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, F> mutableLookableLocationOf(
    world: W,
    vector: V,
    yaw: F,
    pitch: F
): MutableLookableLocation<W, V, Lookable<F>> = mutableLookableLocationOf(world, vector, lookableOf(yaw, pitch))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:06.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> mutableLookableLocationOf(
    world: W,
    vector: V,
    lookable: L
): MutableLookableLocation<W, V, L> = DataMutableLookableLocation(world, vector, lookable)
