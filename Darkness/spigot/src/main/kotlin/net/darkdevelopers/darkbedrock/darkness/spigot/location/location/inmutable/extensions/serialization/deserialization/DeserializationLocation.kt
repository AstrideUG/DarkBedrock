/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.deserialization

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.locationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.serialization.toLookableF
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.extensions.to.toLookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.serialization.deserialization.toVector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:41.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

fun Map<String, Any?>.toLocation(
    world: String? = "world",
    vector: Vector3<Double> = 0.0.toVector3()
): Location<String, Vector3<Double>> = locationOf(
    this["world"]?.toString() ?: world.toString(),
    this.toVector3D(vector)
)

fun Map<String, Any?>.toLookableLocation(
    world: String? = "world",
    vector: Vector3<Double> = 0.0.toVector3(),
    lookable: Lookable<Float> = 0f.toLookable()
): LookableLocation<String, Vector3<Double>, Lookable<Float>> = lookableLocationOf(
    this.toLocation(world, vector),
    this.toLookableF(lookable)
)

