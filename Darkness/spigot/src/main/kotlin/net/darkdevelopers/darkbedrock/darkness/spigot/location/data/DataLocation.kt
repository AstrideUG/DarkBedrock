package net.darkdevelopers.darkbedrock.darkness.spigot.location.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector3D

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:01.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
data class DataLocation<V : Vector3D, L : Lookable>(
    override val world: String,
    override val vector: V,
    override val lookable: L?
) : Location<V, L>

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.05.2019 20:38.
 * Current Version: 1.0 (13.05.2019 - 13.05.2019)
 */
@Suppress("FunctionName")
fun <V : Vector3D> DataLocation(
    world: String,
    vector: V,
    yaw: Float = 0f,
    pitch: Float = 0f
): DataLocation<V, DataLookable> = DataLocation(world, vector, (yaw to pitch).toDataLookable())

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.05.2019 20:38.
 * Current Version: 1.0 (13.05.2019 - 13.05.2019)
 */
@Suppress("FunctionName")
fun DataLocation(
    world: String,
    x: Double,
    y: Double,
    z: Double,
    yaw: Float = 0f,
    pitch: Float = 0f
): DataLocation<DataVector3D, DataLookable> = DataLocation(world, DataVector3D(x, y, z), yaw, pitch)

