/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.locationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:37.
 * Last edit 25.05.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:46.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> Pair<W, V>.toLocation(): Location<W, V> = locationOf(first, second)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:46.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> Triple<W, V, L>.toLookableLocation(): LookableLocation<W, V, L> =
    lookableLocationOf(first, second, third)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:55.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> V.toLocation(world: W): Location<W, V> = locationOf(world, this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:56.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>> Location<W, V>.toLocation(
    world: W = this.world,
    vector: V = this.vector
): Location<W, V> = locationOf(world, vector)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:59.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <W, V : Vector3<*>, L : Lookable<*>> LookableLocation<W, V, L>.toLookableLocation(
    world: W = this.world,
    vector: V = this.vector,
    lookable: L = this.lookable
): LookableLocation<W, V, L> = lookableLocationOf(world, vector, lookable)
