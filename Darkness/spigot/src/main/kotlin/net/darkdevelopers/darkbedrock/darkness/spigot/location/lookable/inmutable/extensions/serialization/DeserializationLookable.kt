/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.serialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.alliases.LookableD
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.alliases.LookableF
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.alliases.LookableI
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.lookableOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.to.toLookable

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:31.
 * Last edit 25.05.2019
 */

fun Map<String, Any?>.toLookableI(
    defaultYaw: Int = 0,
    defaultPitch: Int = 0
): LookableI = lookableOf(
    this["yaw"].toString().toIntOrNull() ?: defaultYaw,
    this["pitch"].toString().toIntOrNull() ?: defaultPitch
)

fun Map<String, Any?>.toLookableD(
    defaultYaw: Double = 0.0,
    defaultPitch: Double = 0.0
): LookableD = lookableOf(
    this["yaw"].toString().toDoubleOrNull() ?: defaultYaw,
    this["pitch"].toString().toDoubleOrNull() ?: defaultPitch
)

fun Map<String, Any?>.toLookableF(
    default: LookableF = 0f.toLookable()
): LookableF = toLookableF(default.yaw, default.pitch)


fun Map<String, Any?>.toLookableF(
    defaultYaw: Float = 0f,
    defaultPitch: Float = 0f
): LookableF = lookableOf(
    this["yaw"].toString().toFloatOrNull() ?: defaultYaw,
    this["pitch"].toString().toFloatOrNull() ?: defaultPitch
)


