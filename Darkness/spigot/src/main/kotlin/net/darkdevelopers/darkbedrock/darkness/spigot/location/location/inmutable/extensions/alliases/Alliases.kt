/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:13.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

private typealias Default = String

typealias ReadOnlyLocation<W> = Location<W, *>
typealias ReadOnlyLookableLocation<W> = LookableLocation<W, *, *>

typealias BlockLocation<W> = Location<W, Vector3<Int>>
typealias EntityLocation<W> = Location<W, Vector3<Double>>
typealias LivingLocation<W> = LookableLocation<W, Vector3<Double>, Lookable<Float>>


typealias DefaultReadOnlyLocation = ReadOnlyLocation<Default>
typealias DefaultReadOnlyLookableLocation = ReadOnlyLookableLocation<Default>

typealias DefaultBlockLocation = BlockLocation<Default>
typealias DefaultEntityLocation = EntityLocation<Default>
typealias DefaultLivingLocation = LivingLocation<Default>


val <N> Location<*, Vector3<N>>.x: N get() = vector.x
val <N> Location<*, Vector3<N>>.y: N get() = vector.y
val <N> Location<*, Vector3<N>>.z: N get() = vector.y

val <L> LookableLocation<*, *, Lookable<L>>.yaw: L get() = lookable.yaw
val <L> LookableLocation<*, *, Lookable<L>>.pitch: L get() = lookable.pitch
