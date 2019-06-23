/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.serialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2I

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:31.
 * Last edit 25.05.2019
 */

fun Vector2I.toMap(
    defaultX: Int = 0,
    defaultY: Int = 0
): Map<String, Any?> = toMapTo(defaultX, defaultY, mutableMapOf())

fun Vector2D.toMap(
    defaultX: Double = 0.0,
    defaultY: Double = 0.0
): Map<String, Any?> = toMapTo(defaultX, defaultY, mutableMapOf())

fun <T, D : MutableMap<String, Any?>> Vector2<T>.toMapTo(
    defaultX: T,
    defaultY: T,
    destination: D
): D {
    if (x != defaultX) destination["x"] = x
    if (y != defaultY) destination["y"] = y
    return destination
}


