package net.darkdevelopers.darkbedrock.darkness.spigot.location

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.Vector3D

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:17.
 * Current Version: 1.0 (09.05.2019 - 13.05.2019)
 */
interface MutableLocation<V : Vector3D, L : Lookable> : Location<V, L> {
    override var world: String
    override var vector: V
    override var lookable: L?
}