/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.extensions.mutableVector2Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:02.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:03.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> N.toMutableVector2(): MutableVector2<N> = this toMutableVector2 this

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:03.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
infix fun <N> N.toMutableVector2(other: N): MutableVector2<N> = mutableVector2Of(this, other)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 04:27.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> Vector2<N>.toMutableVector2(
    x: N = this.x,
    y: N = this.y
): MutableVector2<N> = mutableVector2Of(x, y)
