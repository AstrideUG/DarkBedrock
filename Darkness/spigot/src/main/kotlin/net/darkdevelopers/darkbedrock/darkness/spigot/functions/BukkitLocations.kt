/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("BukkitLocationUtils")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.aliases.BukkitLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.aliases.BukkitWorld

/*
 * Created on 27.06.2019 00:01.
 * @author Lars Artmann | LartyHD
 */

@JvmOverloads
fun BukkitLocation.copy(
    world: BukkitWorld? = null,
    x: Double? = null,
    y: Double? = null,
    z: Double? = null,
    yaw: Float? = null,
    pitch: Float? = null
): BukkitLocation = BukkitLocation(
    world ?: this.world,
    x ?: this.x,
    y ?: this.y,
    z ?: this.z,
    yaw ?: this.yaw,
    pitch ?: this.pitch
)
