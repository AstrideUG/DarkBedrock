/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.data.DataLookable

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:52.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:55.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> lookableOf(pair: Pair<N, N>): Lookable<N> = lookableOf(pair.first, pair.second)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:52.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> lookableOf(yaw: N, pitch: N): Lookable<N> = DataLookable(yaw, pitch)
