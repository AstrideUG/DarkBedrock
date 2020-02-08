/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.vector3Of

/*
 * Created on 24.06.2019 09:37.
 * @author Lars Artmann | LartyHD
 */

fun <W, OV : Vector3<*>, NV : Vector3<*>> Location<W, OV>.map(
    mappedVector: OV.() -> NV,
    newVector: NV = vector.mappedVector()
): Location<W, NV> =
    map({ this }, mappedVector, newVector = newVector)

fun <OW, NW, OV : Vector3<*>, NV : Vector3<*>> Location<OW, OV>.map(
    mappedWorld: OW.() -> NW,
    mappedVector: OV.() -> NV,
    newWorld: NW = world.mappedWorld(),
    newVector: NV = vector.mappedVector()
): Location<NW, NV> = locationOf(newWorld, newVector)

fun <O, N> Vector3<O>.map(
    mapped: O.() -> N,
    newX: N = x.mapped(),
    newY: N = y.mapped(),
    newZ: N = z.mapped()
): Vector3<N> = vector3Of(newX, newY, newZ)