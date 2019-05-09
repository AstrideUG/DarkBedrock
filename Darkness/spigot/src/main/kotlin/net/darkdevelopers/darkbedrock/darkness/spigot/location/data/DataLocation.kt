package net.darkdevelopers.darkbedrock.darkness.spigot.location.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector3D

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:01.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
data class DataLocation(
    override val world: String,
    override val vector: Vector3D,
    override val lookable: Lookable?
) : Location {

    constructor(
        world: String,
        vector: Vector3D,
        yaw: Float = 0f,
        pitch: Float = 0f
    ) : this(world, vector, (yaw to pitch).toDataLookable())

    constructor(
        world: String,
        x: Double,
        y: Double,
        z: Double,
        yaw: Float = 0f,
        pitch: Float = 0f
    ) : this(world, DataVector3D(y, x, z), yaw, pitch)

}