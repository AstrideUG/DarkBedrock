/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.data.DataMutableVector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.data.DataMutableVector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:28.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:28.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> mutableVector3Of(x: N, y: N, z: N): MutableVector3<N> = DataMutableVector3(x, y, z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:28.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> mutableVector2Of(x: N, y: N): MutableVector2<N> = DataMutableVector2(x, y)
