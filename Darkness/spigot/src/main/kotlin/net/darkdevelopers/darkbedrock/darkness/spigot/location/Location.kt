package net.darkdevelopers.darkbedrock.darkness.spigot.location

import com.google.gson.JsonNull
import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.JsonArray
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toJsonObject
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toJsonPrimitive
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
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
interface Location {
    val world: String
    val vector: Vector3D
    val lookable: Lookable?
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:59.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
val Location.x: Double get() = vector.x
val Location.y: Double get() = vector.y
val Location.z: Double get() = vector.z
val Location.yaw: Float? get() = lookable?.yaw
val Location.pitch: Float? get() = lookable?.pitch
val Location.yawOr0: Float get() = yaw ?: 0f
val Location.pitchOr0: Float get() = pitch ?: 0f

fun Location.toBukkitLocation(): org.bukkit.Location =
    org.bukkit.Location(Bukkit.getWorld(world), x, y, z, yawOr0, pitchOr0)

fun org.bukkit.Location.toLocation(): Location = DataLocation(world?.name.toString(), x, y, z, yaw, pitch)

fun Map<String, Any?>.toLocation(
    world: String? = null,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    yaw: Float = 0f,
    pitch: Float = 0f
): Location = DataLocation(
    this["world"]?.toString() ?: world.toString(),
    this.toVector3D(x, y, z),
    this["yaw"].toString().toFloatOrNull() ?: yaw,
    this["pitch"].toString().toFloatOrNull() ?: pitch
)

fun Location.toMap(
    world: String? = null,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    yaw: Float = 0f,
    pitch: Float = 0f
): Map<String, Any?> = toMap(world, DataVector3D(x, y, z), yaw, pitch)

fun Location.toMap(
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

fun Location.toJsonObject(serializeNull: Boolean = false): JsonObject = toMap().mapNotNull { (key, value) ->
    val jsonElement = when (value) {
        null -> if (serializeNull) JsonNull.INSTANCE else null
        is Iterable<*> -> JsonArray(value.mapNotNull { it?.toJsonPrimitive() })
        //TODO     is GameMap<String, Any?> -> JsonObject()
        else -> value.toJsonPrimitive()
    }
    jsonElement ?: return@mapNotNull null
    key to jsonElement
}.toMap().toJsonObject()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:37.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Location.copy(
    world: String = this.world,
    vector: Vector3D = this.vector,
    lookable: Lookable? = this.lookable
): DataLocation = DataLocation(world, vector, lookable)