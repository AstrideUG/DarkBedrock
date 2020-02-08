/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:06.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataMutableLookableLocation<W, V : Vector3<*>, L : Lookable<*>>(
    override var world: W,
    override var vector: V,
    override var lookable: L
) : MutableLookableLocation<W, V, L>