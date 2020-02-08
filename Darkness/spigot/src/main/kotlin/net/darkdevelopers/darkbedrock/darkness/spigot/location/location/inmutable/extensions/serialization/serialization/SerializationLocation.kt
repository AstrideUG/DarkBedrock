/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.serialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.lookableOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.serialization.toMapTo
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.serialization.toMapTo
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 06:41.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

fun <W, N> Location<W, Vector3<N>>.toMap(
    location: Location<W, Vector3<N>>
): Map<String, Any?> = toMap(location.world, location.vector)

fun <W, N> Location<W, Vector3<N>>.toMap(
    world: W,
    x: N,
    y: N,
    z: N
): Map<String, Any?> = toMap(world, vector3Of(x, y, z))

fun <W, N> Location<W, Vector3<N>>.toMap(
    defaultWorld: W,
    defaultVector: Vector3<N>
): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    if (world != defaultWorld) this["world"] = world
    if (vector != defaultVector) vector.toMapTo(
        defaultVector.x,
        defaultVector.y,
        defaultVector.y,
        destination = this
    )
}

fun <W, N, L> LookableLocation<W, Vector3<N>, Lookable<L>>.toMap(
    world: W,
    x: N,
    y: N,
    z: N,
    yaw: L,
    pitch: L
): Map<String, Any?> = toMap(world, x, y, z, yaw, pitch)

fun <W, N, L> LookableLocation<W, Vector3<N>, Lookable<L>>.toMap(
    lookableLocation: LookableLocation<W, Vector3<N>, Lookable<L>>
): Map<String, Any?> = toMap(lookableLocation.world, lookableLocation.vector, lookableLocation.lookable)

fun <W, N, L> LookableLocation<W, Vector3<N>, Lookable<L>>.toMap(
    world: W,
    vector: Vector3<N>,
    yaw: L,
    pitch: L
): Map<String, Any?> = toMap(world, vector, lookableOf(yaw, pitch))

fun <W, N, L> LookableLocation<W, Vector3<N>, Lookable<L>>.toMap(
    world: W,
    x: N,
    y: N,
    z: N,
    lookable: Lookable<L>
): Map<String, Any?> = toMap(world, vector3Of(x, y, z), lookable)

fun <W, N, L> LookableLocation<W, Vector3<N>, Lookable<L>>.toMap(
    defaultWorld: W,
    defaultVector: Vector3<N>,
    defaultLookable: Lookable<L>
): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    if (world != defaultWorld) this["world"] = world
    if (vector != defaultVector) vector.toMapTo(defaultVector.x, defaultVector.y, defaultVector.y, destination = this)
    if (lookable != defaultLookable) lookable.toMapTo(
        defaultLookable.yaw,
        defaultLookable.pitch,
        destination = this
    )
}
