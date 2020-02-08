/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.serialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.alliases.LookableD
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.alliases.LookableI

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:23.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

fun LookableI.toMap(
    defaultYaw: Int = 0,
    defaultPitch: Int = 0
): Map<String, Any?> = toMapTo(defaultYaw, defaultPitch, mutableMapOf())

fun LookableD.toMap(
    defaultYaw: Double = 0.0,
    defaultPitch: Double = 0.0
): Map<String, Any?> = toMapTo(defaultYaw, defaultPitch, mutableMapOf())

fun <N, D : MutableMap<String, Any?>> Lookable<N>.toMapTo(
    defaultYaw: N,
    defaultPitch: N,
    destination: D
): D {
    if (yaw != defaultYaw) destination["yaw"] = yaw
    if (pitch != defaultPitch) destination["pitch"] = pitch
    return destination
}


