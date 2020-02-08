/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.deserialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3I
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:47.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

fun Map<String, Any?>.toVector3I(
    default: Vector3I = 0.toVector3()
): Vector3I = toVector3I(default.x, default.y, default.z)

fun Map<String, Any?>.toVector3I(
    defaultX: Int = 0,
    defaultY: Int = 0,
    defaultZ: Int = 0
): Vector3I = vector3Of(
    this["x"].toString().toIntOrNull() ?: defaultX,
    this["y"].toString().toIntOrNull() ?: defaultY,
    this["z"].toString().toIntOrNull() ?: defaultZ
)

fun Map<String, Any?>.toVector3D(
    default: Vector3D = 0.0.toVector3()
): Vector3D = toVector3D(default.x, default.y, default.z)

fun Map<String, Any?>.toVector3D(
    defaultX: Double = 0.0,
    defaultY: Double = 0.0,
    defaultZ: Double = 0.0
): Vector3D = vector3Of(
    this["x"].toString().toDoubleOrNull() ?: defaultX,
    this["y"].toString().toDoubleOrNull() ?: defaultY,
    this["z"].toString().toDoubleOrNull() ?: defaultZ
)


