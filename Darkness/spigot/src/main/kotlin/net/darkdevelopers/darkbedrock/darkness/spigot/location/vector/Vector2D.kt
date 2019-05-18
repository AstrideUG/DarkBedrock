package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector

import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataVector2D
import kotlin.math.max
import kotlin.math.min

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:03.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
interface Vector2D {
    val x: Double
    val z: Double
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:36.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Number.toVector2D(): Vector2D = toDouble() toVector2D toDouble()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:46.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
infix fun Vector2D.min(other: Vector2D): Vector2D = min(x, other.x) toVector2D min(z, other.z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:46.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
infix fun Vector2D.max(other: Vector2D): Vector2D = max(x, other.x) toVector2D max(z, other.z)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:47.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
infix fun Double.toVector2D(other: Double): Vector2D = DataVector2D(this, other)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:50.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Pair<Double, Double>.toVector2D(): Vector2D = first toVector2D second

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:43.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector2D.toMap(
    x: Double = 0.0,
    z: Double = 0.0
): Map<String, Any?> = toMapTo(x, z, mutableMapOf())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:34.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun <D : MutableMap<String, Any?>> Vector2D.toMapTo(
    x: Double = 0.0,
    z: Double = 0.0,
    destination: D
): Map<String, Any?> {
    if (this.x != x) destination["x"] = this.x
    if (this.z != z) destination["z"] = this.z
    return destination
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 21:01.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Map<String, Any?>.toVector2D(
    x: Double = 0.0,
    z: Double = 0.0
): Vector2D = DataVector2D(
    this["x"].toString().toDoubleOrNull() ?: x,
    this["z"].toString().toDoubleOrNull() ?: z
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 20:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector2D.isInside(min: Vector2D, max: Vector2D): Boolean = this.x in min.x..max.x && this.z in min.z..max.z

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:48.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Vector2D.copy(
    x: Double = this.x,
    z: Double = this.z
): Vector2D = DataVector2D(x, z)
