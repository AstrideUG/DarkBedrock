/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.outside

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.inside.isInside

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:51.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

@JvmName("isOutsideB")
fun Vector3B.isOutside(min: Vector3B, max: Vector3B): Boolean = !isInside(min, max)

@JvmName("isOutsideS")
fun Vector3S.isOutside(min: Vector3S, max: Vector3S): Boolean = !isInside(min, max)

@JvmName("isOutsideI")
fun Vector3I.isOutside(min: Vector3I, max: Vector3I): Boolean = !isInside(min, max)

@JvmName("isOutsideL")
fun Vector3L.isOutside(min: Vector3L, max: Vector3L): Boolean = !isInside(min, max)

@JvmName("isOutsideF")
fun Vector3F.isOutside(min: Vector3F, max: Vector3F): Boolean = !isInside(min, max)

@JvmName("isOutsideD")
fun Vector3D.isOutside(min: Vector3D, max: Vector3D): Boolean = !isInside(min, max)


@ExperimentalUnsignedTypes
@JvmName("isOutsideUB")
fun Vector3UB.isOutside(min: Vector3UB, max: Vector3UB): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUS")
fun Vector3US.isOutside(min: Vector3US, max: Vector3US): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUI")
fun Vector3UI.isOutside(min: Vector3UI, max: Vector3UI): Boolean = !isInside(min, max)

@ExperimentalUnsignedTypes
@JvmName("isOutsideUL")
fun Vector3UL.isOutside(min: Vector3UL, max: Vector3UL): Boolean = !isInside(min, max)
