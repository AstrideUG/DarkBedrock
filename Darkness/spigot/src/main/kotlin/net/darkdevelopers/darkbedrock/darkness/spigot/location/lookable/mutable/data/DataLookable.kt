/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.mutable.MutableLookable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:30.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataMutableLookable<N>(
    override var yaw: N,
    override var pitch: N
) : MutableLookable<N>
