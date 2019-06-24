/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.locationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.map
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector3I

/**
 * Created on 25.05.2019 08:37.
 * @author Lars Artmann | LartyHD
 */

fun <W, V : Vector3<*>> Pair<W, V>.toLocation(): Location<W, V> = locationOf(first, second)

fun <W, V : Vector3<*>, L : Lookable<*>> Triple<W, V, L>.toLookableLocation(): LookableLocation<W, V, L> =
    lookableLocationOf(first, second, third)

fun <W, V : Vector3<*>> V.toLocation(world: W): Location<W, V> = locationOf(world, this)

fun <W, V : Vector3<*>> Location<W, V>.toLocation(
    world: W = this.world,
    vector: V = this.vector
): Location<W, V> = locationOf(world, vector)

fun <W, V : Vector3<*>, L : Lookable<*>> LookableLocation<W, V, L>.toLookableLocation(
    world: W = this.world,
    vector: V = this.vector,
    lookable: L = this.lookable
): LookableLocation<W, V, L> = lookableLocationOf(world, vector, lookable)

fun <W, OV : Vector3I> Location<W, OV>.toLocation3D(): Location<W, Vector3D> =
    map(mappedVector = { this.map({ toDouble() }) })

fun <W, OV : Vector3D> Location<W, OV>.toLocation3I(): Location<W, Vector3I> =
    map(mappedVector = { this.map({ toInt() }) })
