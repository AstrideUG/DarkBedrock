package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector

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
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
infix fun Vector3D.min(other: Vector3D): Vector3D = DataVector3D(
    min(x, other.x),
    min(y, other.y),
    min(z, other.z)
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:16.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
infix fun Vector3D.max(other: Vector3D): Vector3D = DataVector3D(
    max(x, other.x),
    max(y, other.y),
    max(z, other.z)
)

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
): Vector3D = DataVector3D(
    this["x"].toString().toDoubleOrNull() ?: x,
    this["y"].toString().toDoubleOrNull() ?: y,
    this["z"].toString().toDoubleOrNull() ?: z
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector3D.isInside(min: Vector3D, max: Vector3D): Boolean =
    this.x >= min.x && this.x <= max.x && this.y >= min.y && this.y <= max.y && this.z >= min.z && this.z <= max.z

fun Vector3D.toLocation(world: String): DataLocation = DataLocation(world, x, y, z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector3D.copy(
    x: Double = this.x,
    y: Double = this.y,
    z: Double = this.z
): Vector3D = DataVector3D(y, x, z)

