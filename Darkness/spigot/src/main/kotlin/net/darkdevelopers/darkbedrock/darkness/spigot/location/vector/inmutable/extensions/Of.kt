/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.data.DataVector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.data.DataVector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:26.
 * Last edit 25.05.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:29.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> vector3Of(vector2: Vector2<N>, z: N): Vector3<N> = vector3Of(vector2.x, vector2.y, z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:26.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> vector3Of(x: N, y: N, z: N): Vector3<N> = DataVector3(x, y, z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:27.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
fun <N> vector2Of(x: N, y: N): Vector2<N> = DataVector2(x, y)
