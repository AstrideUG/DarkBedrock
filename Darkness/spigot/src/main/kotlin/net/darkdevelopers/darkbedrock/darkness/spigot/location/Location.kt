package net.darkdevelopers.darkbedrock.darkness.spigot.location

import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataVector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector3D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toMapTo
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toVector3D
import org.bukkit.Bukkit

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 12:58.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
interface Location<V : Vector3D, L : Lookable> {
    val world: String
    val vector: V
    val lookable: L?
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.05.2019 20:45.
 * Current Version: 1.0 (13.05.2019 - 13.05.2019)
 */
typealias ReadOnlyLocation = Location<*, *>

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:59.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
val ReadOnlyLocation.x: Double get() = vector.x
val ReadOnlyLocation.y: Double get() = vector.y
val ReadOnlyLocation.z: Double get() = vector.z
val ReadOnlyLocation.yaw: Float? get() = lookable?.yaw
val ReadOnlyLocation.pitch: Float? get() = lookable?.pitch
val ReadOnlyLocation.yawOr0: Float get() = yaw ?: 0f
val ReadOnlyLocation.pitchOr0: Float get() = pitch ?: 0f

fun ReadOnlyLocation.toBukkitLocation(): org.bukkit.Location =
    org.bukkit.Location(Bukkit.getWorld(world), x, y, z, yawOr0, pitchOr0)

fun org.bukkit.Location.toLocation(): ReadOnlyLocation = DataLocation(world?.name.toString(), x, y, z, yaw, pitch)

fun Map<String, Any?>.toLocation(
    world: String? = null,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    yaw: Float = 0f,
    pitch: Float = 0f
): ReadOnlyLocation = DataLocation(
    this["world"]?.toString() ?: world.toString(),
    this.toVector3D(x, y, z),
    this["yaw"].toString().toFloatOrNull() ?: yaw,
    this["pitch"].toString().toFloatOrNull() ?: pitch
)

fun ReadOnlyLocation.toMap(
    world: String? = null,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    yaw: Float = 0f,
    pitch: Float = 0f
): Map<String, Any?> = toMap(world, DataVector3D(x, y, z), yaw, pitch)

fun ReadOnlyLocation.toMap(
    defaultWorld: String? = null,
    defaultVector: Vector3D = 0.0.toVector3D(),
    defaultYaw: Float = 0f,
    defaultPitch: Float = 0f
): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
    if (world != defaultWorld) this["world"] = world
    if (vector != defaultVector) vector.toMapTo(defaultVector.x, defaultVector.y, defaultVector.z, destination = this)
    if (yawOr0 != defaultYaw) this["yaw"] = yawOr0
    if (pitchOr0 != defaultPitch) this["pitch"] = pitchOr0
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:37.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
fun ReadOnlyLocation.copy(
    world: String = this.world,
    vector: Vector3D = this.vector,
    lookable: Lookable? = this.lookable
): ReadOnlyLocation = DataLocation(world, vector, lookable)