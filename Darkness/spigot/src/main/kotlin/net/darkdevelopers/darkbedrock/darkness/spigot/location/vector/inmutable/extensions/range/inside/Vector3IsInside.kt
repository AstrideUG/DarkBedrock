/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.inside

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:50.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

@JvmName("isInsideB")
fun Vector3B.isInside(min: Vector3B, max: Vector3B): Boolean = isInside(min as Vector2B, max) && this.z in min.z..max.z

@JvmName("isInsideS")
fun Vector3S.isInside(min: Vector3S, max: Vector3S): Boolean = isInside(min as Vector2S, max) && this.z in min.z..max.z

@JvmName("isInsideI")
fun Vector3I.isInside(min: Vector3I, max: Vector3I): Boolean = isInside(min as Vector2I, max) && this.z in min.z..max.z

@JvmName("isInsideL")
fun Vector3L.isInside(min: Vector3L, max: Vector3L): Boolean = isInside(min as Vector2L, max) && this.z in min.z..max.z

@JvmName("isInsideF")
fun Vector3F.isInside(min: Vector3F, max: Vector3F): Boolean = isInside(min as Vector2F, max) && this.z in min.z..max.z

@JvmName("isInsideD")
fun Vector3D.isInside(min: Vector3D, max: Vector3D): Boolean = isInside(min as Vector2D, max) && this.z in min.z..max.z


@ExperimentalUnsignedTypes
@JvmName("isInsideUB")
fun Vector3UB.isInside(min: Vector3UB, max: Vector3UB): Boolean =
    isInside(min as Vector2UB, max) && this.z in min.z..max.z

@ExperimentalUnsignedTypes
@JvmName("isInsideUS")
fun Vector3US.isInside(min: Vector3US, max: Vector3US): Boolean =
    isInside(min as Vector2US, max) && this.z in min.z..max.z

@ExperimentalUnsignedTypes
@JvmName("isInsideUI")
fun Vector3UI.isInside(min: Vector3UI, max: Vector3UI): Boolean =
    isInside(min as Vector2UI, max) && this.z in min.z..max.z

@ExperimentalUnsignedTypes
@JvmName("isInsideUL")
fun Vector3UL.isInside(min: Vector3UL, max: Vector3UL): Boolean =
    isInside(min as Vector2UL, max) && this.z in min.z..max.z
