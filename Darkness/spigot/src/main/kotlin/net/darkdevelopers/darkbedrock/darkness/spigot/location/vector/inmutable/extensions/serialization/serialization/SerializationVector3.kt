/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.serialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3I

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:41.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

fun Vector3I.toMap(
    defaultX: Int = 0,
    defaultY: Int = 0,
    defaultZ: Int = 0
): Map<String, Any?> = toMapTo(defaultX, defaultY, defaultZ, mutableMapOf())

fun Vector3D.toMap(
    defaultX: Double = 0.0,
    defaultY: Double = 0.0,
    defaultZ: Double = 0.0
): Map<String, Any?> = toMapTo(defaultX, defaultY, defaultZ, mutableMapOf())

fun <T, D : MutableMap<String, Any?>> Vector3<T>.toMapTo(
    defaultX: T,
    defaultY: T,
    defaultZ: T,
    destination: D
): D {
    if (x != defaultX) destination["x"] = x
    if (y != defaultY) destination["y"] = y
    if (z != defaultZ) destination["z"] = z
    return destination
}


