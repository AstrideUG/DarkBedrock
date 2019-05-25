/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:51.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataMutableVector2<N>(
    override var x: N,
    override var y: N
) : MutableVector2<N>