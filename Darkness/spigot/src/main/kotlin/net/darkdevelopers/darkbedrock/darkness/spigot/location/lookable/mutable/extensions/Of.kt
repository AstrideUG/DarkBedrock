/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.MutableLookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.data.DataMutableLookable

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:34.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:34.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> mutableLookableOf(yaw: N, pitch: N): MutableLookable<N> = DataMutableLookable(yaw, pitch)
