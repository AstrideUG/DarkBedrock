/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:05.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataMutableLocation<W, V : Vector3<*>>(
    override var world: W,
    override var vector: V
) : MutableLocation<W, V>
