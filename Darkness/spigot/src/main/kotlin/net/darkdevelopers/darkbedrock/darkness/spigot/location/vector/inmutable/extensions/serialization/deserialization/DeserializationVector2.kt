/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.deserialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2I
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector2Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:31.
 * Last edit 25.05.2019
 */

fun Map<String, Any?>.toVector2I(
    defaultX: Int = 0,
    defaultY: Int = 0
): Vector2I = vector2Of(
    this["x"].toString().toIntOrNull() ?: defaultX,
    this["y"].toString().toIntOrNull() ?: defaultY
)

fun Map<String, Any?>.toVector2D(
    defaultX: Double = 0.0,
    defaultY: Double = 0.0
): Vector2D = vector2Of(
    this["x"].toString().toDoubleOrNull() ?: defaultX,
    this["y"].toString().toDoubleOrNull() ?: defaultY
)


