/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.compare.min

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of
import kotlin.math.min

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:24.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

@JvmName("minI")
infix fun Vector3I.min(other: Vector3I): Vector3I = vector3Of(min(other as Vector2I), min(this.z, other.z))

@JvmName("minL")
infix fun Vector3L.min(other: Vector3L): Vector3L = vector3Of(min(other as Vector2L), min(this.z, other.z))

@JvmName("minD")
infix fun Vector3D.min(other: Vector3D): Vector3D = vector3Of(min(other as Vector2D), min(this.z, other.z))

@JvmName("minF")
infix fun Vector3F.min(other: Vector3F): Vector3F = vector3Of(min(other as Vector2F), min(this.z, other.z))


@JvmName("minB")
infix fun Vector3B.min(other: Vector3B): Vector3B =
    vector3Of(min(other as Vector2B), min(this.z.toInt(), other.z.toInt()).toByte())

@JvmName("minS")
infix fun Vector3S.min(other: Vector3S): Vector3S =
    vector3Of(min(other as Vector2S), min(this.z.toInt(), other.z.toInt()).toShort())


@ExperimentalUnsignedTypes
@JvmName("minUI")
infix fun Vector3UI.min(other: Vector3UI): Vector3UI = vector3Of(min(other as Vector2UI), min(this.z, other.z))

@ExperimentalUnsignedTypes
@JvmName("minUL")
infix fun Vector3UL.min(other: Vector3UL): Vector3UL = vector3Of(min(other as Vector2UL), min(this.z, other.z))


@ExperimentalUnsignedTypes
@JvmName("minUB")
infix fun Vector3UB.min(other: Vector3UB): Vector3UB =
    vector3Of(min(other as Vector2UB), min(this.z.toInt(), other.z.toInt()).toUByte())

@ExperimentalUnsignedTypes
@JvmName("minUS")
infix fun Vector3US.min(other: Vector3US): Vector3US =
    vector3Of(min(other as Vector2US), min(this.z.toInt(), other.z.toInt()).toUShort())
