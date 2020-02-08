/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:17.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
interface MutableLocation<W, V : Vector3<*>> : Location<W, V> {
    override var world: W
    override var vector: V
}