/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.range.inside

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.*

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 02:42.
 * Last edit 25.05.2019
 */

@JvmName("isInsideB")
fun Vector2B.isInside(min: Vector2B, max: Vector2B): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@JvmName("isInsideS")
fun Vector2S.isInside(min: Vector2S, max: Vector2S): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@JvmName("isInsideI")
fun Vector2I.isInside(min: Vector2I, max: Vector2I): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@JvmName("isInsideL")
fun Vector2L.isInside(min: Vector2L, max: Vector2L): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@JvmName("isInsideF")
fun Vector2F.isInside(min: Vector2F, max: Vector2F): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@JvmName("isInsideD")
fun Vector2D.isInside(min: Vector2D, max: Vector2D): Boolean = this.x in min.x..max.x && this.y in min.y..max.y


@ExperimentalUnsignedTypes
@JvmName("isInsideUB")
fun Vector2UB.isInside(min: Vector2UB, max: Vector2UB): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@ExperimentalUnsignedTypes
@JvmName("isInsideUS")
fun Vector2US.isInside(min: Vector2US, max: Vector2US): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@ExperimentalUnsignedTypes
@JvmName("isInsideUI")
fun Vector2UI.isInside(min: Vector2UI, max: Vector2UI): Boolean = this.x in min.x..max.x && this.y in min.y..max.y

@ExperimentalUnsignedTypes
@JvmName("isInsideUL")
fun Vector2UL.isInside(min: Vector2UL, max: Vector2UL): Boolean = this.x in min.x..max.x && this.y in min.y..max.y
