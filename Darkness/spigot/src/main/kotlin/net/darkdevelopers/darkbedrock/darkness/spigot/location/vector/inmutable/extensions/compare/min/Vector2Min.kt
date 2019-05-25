/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.compare.min

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector2
import kotlin.math.min

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:13.
 * Last edit 25.05.2019
 */

@JvmName("minI")
infix fun Vector2I.min(other: Vector2I): Vector2I = min(x, other.x) toVector2 min(y, other.y)

@JvmName("minL")
infix fun Vector2L.min(other: Vector2L): Vector2L = min(x, other.x) toVector2 min(y, other.y)

@JvmName("minD")
infix fun Vector2D.min(other: Vector2D): Vector2D = min(x, other.x) toVector2 min(y, other.y)

@JvmName("minF")
infix fun Vector2F.min(other: Vector2F): Vector2F = min(x, other.x) toVector2 min(y, other.y)


@JvmName("minB")
infix fun Vector2B.min(other: Vector2B): Vector2B =
    min(x.toInt(), other.x.toInt()).toByte() toVector2 min(y.toInt(), other.y.toInt()).toByte()

@JvmName("minS")
infix fun Vector2S.min(other: Vector2S): Vector2S =
    min(x.toInt(), other.x.toInt()).toShort() toVector2 min(y.toInt(), other.y.toInt()).toShort()


@ExperimentalUnsignedTypes
@JvmName("minUI")
infix fun Vector2UI.min(other: Vector2UI): Vector2UI = min(x, other.x) toVector2 min(y, other.y)

@ExperimentalUnsignedTypes
@JvmName("minUL")
infix fun Vector2UL.min(other: Vector2UL): Vector2UL = min(x, other.x) toVector2 min(y, other.y)


@ExperimentalUnsignedTypes
@JvmName("minUB")
infix fun Vector2UB.min(other: Vector2UB): Vector2UB =
    min(x.toInt(), other.x.toInt()).toUByte() toVector2 min(y.toInt(), other.y.toInt()).toUByte()

@ExperimentalUnsignedTypes
@JvmName("minU")
infix fun Vector2US.min(other: Vector2US): Vector2US =
    min(x.toInt(), other.x.toInt()).toUShort() toVector2 min(y.toInt(), other.y.toInt()).toUShort()
