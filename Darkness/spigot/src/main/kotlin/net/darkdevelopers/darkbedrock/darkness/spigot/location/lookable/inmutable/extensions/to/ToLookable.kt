/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.lookableOf

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:56.
 * Last edit 25.05.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:18.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Pair<N, N>.toNullableLookable(
    defaultYaw: N,
    defaultPitch: N
): Lookable<N>? = if (first == defaultYaw && second == defaultPitch) null else lookableOf(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:55.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Pair<N, N>.toLookable(): Lookable<N> = lookableOf(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:57.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toLookable(): Lookable<N> = lookableOf(this, this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:58.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Lookable<N>.toLookable(
    yaw: N = this.yaw,
    pitch: N = this.pitch
): Lookable<N> = lookableOf(yaw, pitch)
