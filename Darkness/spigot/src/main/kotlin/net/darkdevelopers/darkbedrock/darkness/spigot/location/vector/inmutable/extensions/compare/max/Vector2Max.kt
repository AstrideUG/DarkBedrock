/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.compare.max

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector2
import kotlin.math.max

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:37.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

@JvmName("maxI")
infix fun Vector2I.max(other: Vector2I): Vector2I = max(x, other.x) toVector2 max(y, other.y)

@JvmName("maxL")
infix fun Vector2L.max(other: Vector2L): Vector2L = max(x, other.x) toVector2 max(y, other.y)

@JvmName("maxD")
infix fun Vector2D.max(other: Vector2D): Vector2D = max(x, other.x) toVector2 max(y, other.y)

@JvmName("maxF")
infix fun Vector2F.max(other: Vector2F): Vector2F = max(x, other.x) toVector2 max(y, other.y)


@JvmName("maxB")
infix fun Vector2B.max(other: Vector2B): Vector2B =
    max(x.toInt(), other.x.toInt()).toByte() toVector2 max(y.toInt(), other.y.toInt()).toByte()

@JvmName("maxS")
infix fun Vector2S.max(other: Vector2S): Vector2S =
    max(x.toInt(), other.x.toInt()).toShort() toVector2 max(y.toInt(), other.y.toInt()).toShort()


@ExperimentalUnsignedTypes
@JvmName("maxUI")
infix fun Vector2UI.max(other: Vector2UI): Vector2UI = max(x, other.x) toVector2 max(y, other.y)

@ExperimentalUnsignedTypes
@JvmName("maxUL")
infix fun Vector2UL.max(other: Vector2UL): Vector2UL = max(x, other.x) toVector2 max(y, other.y)


@ExperimentalUnsignedTypes
@JvmName("maxUB")
infix fun Vector2UB.max(other: Vector2UB): Vector2UB =
    max(x.toInt(), other.x.toInt()).toUByte() toVector2 max(y.toInt(), other.y.toInt()).toUByte()

@ExperimentalUnsignedTypes
@JvmName("maxU")
infix fun Vector2US.max(other: Vector2US): Vector2US =
    max(x.toInt(), other.x.toInt()).toUShort() toVector2 max(y.toInt(), other.y.toInt()).toUShort()
