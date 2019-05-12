package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toVector2D

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:21.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
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
    defaultSize: Int = 60_000_000,
    defaultCenter: Vector2D = 0.toVector2D(),
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
    defaultSize: Int = 60_000_000,
    defaultCenter: Vector2D = 0.toVector2D(),
    defaultDamageBuffer: Double = 0.0,
    defaultDamageAmount: Double = 0.0,
    defaultWarningTime: Int = 0,
    defaultWarningDistance: Int = 0,
    destination: D
): Map<String, Any?> = destination.apply {
    if (size != defaultSize) destination["size"] = size
    if (center != defaultCenter) destination["center"] = center.toMap()
    if (damageBuffer != defaultDamageBuffer) destination["damage-buffer"] = size
    if (damageAmount != defaultDamageAmount) destination["damage-amount"] = size
    if (warningTime != defaultWarningTime) destination["warning-time"] = size
    if (warningDistance != defaultWarningDistance) destination["warning-distance"] = size
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toWorldBorder(
    defaultSize: Int = 60_000_000,
    defaultCenter: Vector2D = 0.toVector2D(),
    defaultDamageBuffer: Double = 0.0,
    defaultDamageAmount: Double = 0.0,
    defaultWarningTime: Int = 0,
    defaultWarningDistance: Int = 0
): WorldBorder = DataWorldBorder(
    this["size"].toString().toIntOrNull() ?: defaultSize,
    (this["center"] as? Map<String, Any?>)?.toVector2D() ?: defaultCenter,
    this["damage-buffer"].toString().toDoubleOrNull() ?: defaultDamageBuffer,
    this["damage-amount"].toString().toDoubleOrNull() ?: defaultDamageAmount,
    this["warning-time"].toString().toIntOrNull() ?: defaultWarningTime,
    this["warning-distance"].toString().toIntOrNull() ?: defaultWarningDistance
)