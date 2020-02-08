/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector2Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:59.
 * Last edit 25.05.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:39.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Pair<N, N>.toVector2(): Vector2<N> = first toVector2 second

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:56.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toVector2(): Vector2<N> = this toVector2 this

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:57.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
infix fun <N> N.toVector2(other: N): Vector2<N> = vector2Of(this, other)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:59.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Vector2<N>.toVector2(
    x: N = this.x,
    y: N = this.y
): Vector2<N> = vector2Of(x, y)

