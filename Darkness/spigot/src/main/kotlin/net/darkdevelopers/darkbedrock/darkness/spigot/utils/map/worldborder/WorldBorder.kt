/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.deserialization.toVector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.serialization.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:21.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
interface WorldBorder {
    val size: Double
    val center: Vector2D
    val damageBuffer: Double
    val damageAmount: Double
    val warningTime: Int
    val warningDistance: Int
}

fun WorldBorder.toMap(
    defaultSize: Double = 60_000_000.0,
    defaultCenter: Vector2D = 0.0.toVector2(),
    defaultDamageBuffer: Double = 0.0,
    defaultDamageAmount: Double = 0.0,
    defaultWarningTime: Int = 0,
    defaultWarningDistance: Int = 0
): Map<String, Any?> = toMapTo(
    defaultSize,
    defaultCenter,
    defaultDamageBuffer,
    defaultDamageAmount,
    defaultWarningTime,
    defaultWarningDistance,
    mutableMapOf()
)

fun <D : MutableMap<String, Any?>> WorldBorder.toMapTo(
    defaultSize: Double = 60_000_000.0,
    defaultCenter: Vector2D = 0.0.toVector2(),
    defaultDamageBuffer: Double = 0.0,
    defaultDamageAmount: Double = 0.0,
    defaultWarningTime: Int = 0,
    defaultWarningDistance: Int = 0,
    destination: D
): Map<String, Any?> = destination.apply {
    if (this@toMapTo.size != defaultSize) destination["size"] = size
    if (center != defaultCenter) destination["center"] = center.toMap()
    if (damageBuffer != defaultDamageBuffer) destination["damage-buffer"] = size
    if (damageAmount != defaultDamageAmount) destination["damage-amount"] = size
    if (warningTime != defaultWarningTime) destination["warning-time"] = size
    if (warningDistance != defaultWarningDistance) destination["warning-distance"] = size
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toWorldBorder(
    defaultSize: Double = 60_000_000.0,
    defaultCenter: Vector2D = 0.0.toVector2(),
    defaultDamageBuffer: Double = 0.0,
    defaultDamageAmount: Double = 0.0,
    defaultWarningTime: Int = 0,
    defaultWarningDistance: Int = 0
): WorldBorder = DataWorldBorder(
    this["size"].toString().toDoubleOrNull() ?: defaultSize,
    (this["center"] as? Map<String, Any?>)?.toVector2D() ?: defaultCenter,
    this["damage-buffer"].toString().toDoubleOrNull() ?: defaultDamageBuffer,
    this["damage-amount"].toString().toDoubleOrNull() ?: defaultDamageAmount,
    this["warning-time"].toString().toIntOrNull() ?: defaultWarningTime,
    this["warning-distance"].toString().toIntOrNull() ?: defaultWarningDistance
)