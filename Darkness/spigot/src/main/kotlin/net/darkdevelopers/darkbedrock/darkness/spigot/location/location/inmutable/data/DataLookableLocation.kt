/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:44.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataLookableLocation<W, V : Vector3<*>, L : Lookable<*>>(
    override val world: W,
    override val vector: V,
    override val lookable: L
) : LookableLocation<W, V, L>