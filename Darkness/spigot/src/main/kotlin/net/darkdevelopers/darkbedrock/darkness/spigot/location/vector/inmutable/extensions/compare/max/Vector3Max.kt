/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.compare.max

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of
import kotlin.math.max

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:37.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

@JvmName("maxI")
infix fun Vector3I.max(other: Vector3I): Vector3I = vector3Of(max(other as Vector2I), max(this.z, other.z))

@JvmName("maxL")
infix fun Vector3L.max(other: Vector3L): Vector3L = vector3Of(max(other as Vector2L), max(this.z, other.z))

@JvmName("maxD")
infix fun Vector3D.max(other: Vector3D): Vector3D = vector3Of(max(other as Vector2D), max(this.z, other.z))

@JvmName("maxF")
infix fun Vector3F.max(other: Vector3F): Vector3F = vector3Of(max(other as Vector2F), max(this.z, other.z))


@JvmName("maxB")
infix fun Vector3B.max(other: Vector3B): Vector3B =
    vector3Of(max(other as Vector2B), max(this.z.toInt(), other.z.toInt()).toByte())

@JvmName("maxS")
infix fun Vector3S.max(other: Vector3S): Vector3S =
    vector3Of(max(other as Vector2S), max(this.z.toInt(), other.z.toInt()).toShort())


@ExperimentalUnsignedTypes
@JvmName("maxUI")
infix fun Vector3UI.max(other: Vector3UI): Vector3UI = vector3Of(max(other as Vector2UI), max(this.z, other.z))

@ExperimentalUnsignedTypes
@JvmName("maxUL")
infix fun Vector3UL.max(other: Vector3UL): Vector3UL = vector3Of(max(other as Vector2UL), max(this.z, other.z))


@ExperimentalUnsignedTypes
@JvmName("maxUB")
infix fun Vector3UB.max(other: Vector3UB): Vector3UB =
    vector3Of(max(other as Vector2UB), max(this.z.toInt(), other.z.toInt()).toUByte())

@ExperimentalUnsignedTypes
@JvmName("maxUS")
infix fun Vector3US.max(other: Vector3US): Vector3US =
    vector3Of(max(other as Vector2US), max(this.z.toInt(), other.z.toInt()).toUShort())
