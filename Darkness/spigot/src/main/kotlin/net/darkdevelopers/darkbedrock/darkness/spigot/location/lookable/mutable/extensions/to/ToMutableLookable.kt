/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.MutableLookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.extensions.mutableLookableOf

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:33.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:33.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toMutableLookable(): MutableLookable<N> = this toMutableLookable this

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:33.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
infix fun <N> N.toMutableLookable(other: N): MutableLookable<N> = mutableLookableOf(this, other)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:33.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> MutableLookable<N>.toMutableLookable(
    yaw: N = this.yaw,
    pitch: N = this.pitch
): MutableLookable<N> = mutableLookableOf(yaw, pitch)
