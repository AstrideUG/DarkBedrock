/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector

import net.darkdevelopers.darkbedrock.darkness.spigot.location.ReadOnlyLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataVector3D
import kotlin.collections.set
import kotlin.math.max
import kotlin.math.min

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:03.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
interface Vector3D : Vector2D {
    val y: Double
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:36.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Number.toVector3D(): Vector3D = DataVector3D(toDouble(), toDouble(), toDouble())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:16.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
infix fun Vector3D.min(other: Vector3D): Vector3D = DataVector3D(min(other as Vector2D), min(y, other.y))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:16.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
infix fun Vector3D.max(other: Vector3D): Vector3D = DataVector3D(max(other as Vector2D), max(y, other.y))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:43.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector3D.toMap(
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
): Map<String, Any?> = toMapTo(x, y, z, mutableMapOf())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:34.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun <D : MutableMap<String, Any?>> Vector3D.toMapTo(
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    destination: D
): Map<String, Any?> {
    if (this.x != x) destination["x"] = this.x
    if (this.y != y) destination["y"] = this.y
    if (this.z != z) destination["z"] = this.z
    return destination
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 21:01.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Map<String, Any?>.toVector3D(
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
): Vector3D = DataVector3D(toVector2D(x, z), this["y"].toString().toDoubleOrNull() ?: y)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector3D.isInside(min: Vector3D, max: Vector3D): Boolean =
    (this as Vector2D).isInside(min, max) && this.y in min.y..max.y

fun Vector3D.toLocation(world: String): ReadOnlyLocation = DataLocation(world, x, y, z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector3D.copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z
): Vector3D = DataVector3D((this as Vector2D).copy(x, z), y)

