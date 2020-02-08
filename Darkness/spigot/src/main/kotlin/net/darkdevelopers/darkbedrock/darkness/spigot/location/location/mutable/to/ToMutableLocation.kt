/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.mutableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.mutableLookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> Pair<W, V>.toMutableLocation(): MutableLocation<W, V> = mutableLocationOf(first, second)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> Triple<W, V, L>.toMutableLookableLocation(): MutableLookableLocation<W, V, L> =
    mutableLookableLocationOf(first, second, third)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> V.toMutableLocation(world: W): MutableLocation<W, V> = mutableLocationOf(world, this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> Location<W, V>.toMutableLocation(
    world: W = this.world,
    vector: V = this.vector
): MutableLocation<W, V> = mutableLocationOf(world, vector)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:11.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> LookableLocation<W, V, L>.toMutableLookableLocation(
    world: W = this.world,
    vector: V = this.vector,
    lookable: L = this.lookable
): MutableLookableLocation<W, V, L> = mutableLookableLocationOf(world, vector, lookable)
