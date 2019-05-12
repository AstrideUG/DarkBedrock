/*
 * © Copyright - MineWar.net | Lars Artmann aka. LartyHD 2017
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.region

import net.darkdevelopers.darkbedrock.darkness.spigot.location.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.toLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.*

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 07.08.2017 03:10.
 * Last edit 09.05.2019
 */
class Region @Deprecated("Will be changed to data class", ReplaceWith("Region.of(world, pos1, pos2)")) constructor(
    val world: String,
    pos1: Vector3D,
    pos2: Vector3D
) {

    val min: Vector3D = pos1 min pos2
    val max: Vector3D = pos1 max pos2

    @Suppress("DEPRECATION", "MemberVisibilityCanBePrivate")
    companion object {
        fun of(pos1: org.bukkit.Location, pos2: org.bukkit.Location): Region = of(pos1.toLocation(), pos2.toLocation())
        fun of(pos1: Location, pos2: Location): Region {
            if (pos1.world != pos2.world) throw IllegalArgumentException("pos1 world and pos2 world must be the same")
            return of(pos1.world, pos1.vector, pos2.vector)
        }

        fun of(world: String, pos1: Vector3D, pos2: Vector3D): Region = Region(world, pos1, pos2)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Will be changed to data class", ReplaceWith("Region.of(pos1, pos2)"))
    constructor(pos1: org.bukkit.Location, pos2: org.bukkit.Location) : this(
        pos1.world.name,
        pos1.toLocation().vector,
        pos2.toLocation().vector
    ) {
        if (world != pos2.world.name) throw IllegalArgumentException("pos1 world and pos2 world must be the same")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Region) return false

        if (world != other.world) return false
        if (min != other.min) return false
        if (max != other.max) return false

        return true
    }

    override fun hashCode(): Int {
        var result = world.hashCode()
        result = 31 * result + min.hashCode()
        result = 31 * result + max.hashCode()
        return result
    }

    override fun toString(): String = "Region(world='$world', min=$min, max=$max)"

}

fun Region.isInside(vector3D: Vector3D): Boolean = vector3D.isInside(min, max)
fun Region.isInside(location: Location): Boolean = location.world == world && isInside(location.vector)
fun Region.isInside(location: org.bukkit.Location): Boolean = isInside(location.toLocation())

fun Region.toMap(
    defaultWorld: String? = null,
    defaultMin: Vector3D = 0.toVector3D(),
    defaultMax: Vector3D = 0.toVector3D()
): Map<String, Any?> = toMapTo(
    defaultWorld,
    defaultMin,
    defaultMax,
    mutableMapOf()
)

fun <D : MutableMap<String, Any?>> Region.toMapTo(
    defaultWorld: String? = null,
    defaultMin: Vector3D = 0.toVector3D(),
    defaultMax: Vector3D = 0.toVector3D(),
    destination: D
): Map<String, Any?> = destination.apply {
    if (world == defaultWorld) this["world"] = world
    if (min == defaultMin) min.toMap().also { if (it.isNotEmpty()) this["pos1"] = it }
    if (max == defaultMax) max.toMap().also { if (it.isNotEmpty()) this["pos2"] = it }
}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.toRegion(
    defaultWorld: String? = null,
    defaultPos1: Vector3D? = null,
    defaultPos2: Vector3D? = null
): Region? {
    return Region.of(
        this["world"]?.toString() ?: defaultWorld.toString(),
        (this["pos1"] as? Map<String, Any?>)?.toVector3D() ?: defaultPos1 ?: return null,
        (this["pos2"] as? Map<String, Any?>)?.toVector3D() ?: defaultPos2 ?: return null
    )
}
