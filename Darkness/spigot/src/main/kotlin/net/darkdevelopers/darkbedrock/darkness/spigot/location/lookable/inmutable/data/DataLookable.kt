/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 13:23.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
data class DataLookable<N>(
    override val yaw: N,
    override val pitch: N
) : Lookable<N>
