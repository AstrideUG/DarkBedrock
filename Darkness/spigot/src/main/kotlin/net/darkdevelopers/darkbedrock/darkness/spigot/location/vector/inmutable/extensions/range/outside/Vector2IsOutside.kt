/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.outside

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.inside.isInside

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:42.
 * Last edit 25.05.2019
 */

@JvmName("isOutsideB")
fun Vector2B.isOutside(min: Vector2B, max: Vector2B): Boolean = !isInside(min, max)

@JvmName("isOutsideS")
fun Vector2S.isOutside(min: Vector2S, max: Vector2S): Boolean = !isInside(min, max)

@JvmName("isOutsideI")
fun Vector2I.isOutside(min: Vector2I, max: Vector2I): Boolean = !isInside(min, max)

@JvmName("isOutsideL")
fun Vector2L.isOutside(min: Vector2L, max: Vector2L): Boolean = !isInside(min, max)

@JvmName("isOutsideF")
fun Vector2F.isOutside(min: Vector2F, max: Vector2F): Boolean = !isInside(min, max)

@JvmName("isOutsideD")
fun Vector2D.isOutside(min: Vector2D, max: Vector2D): Boolean = !isInside(min, max)


@ExperimentalUnsignedTypes
@JvmName("isOutsideUB")
fun Vector2UB.isOutside(min: Vector2UB, max: Vector2UB): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUS")
fun Vector2US.isOutside(min: Vector2US, max: Vector2US): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUI")
fun Vector2UI.isOutside(min: Vector2UI, max: Vector2UI): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUL")
fun Vector2UL.isOutside(min: Vector2UL, max: Vector2UL): Boolean = !isInside(min, max)
