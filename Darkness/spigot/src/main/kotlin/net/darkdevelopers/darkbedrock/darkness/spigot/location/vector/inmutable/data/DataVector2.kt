/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.data

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 01:37.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
data class DataVector2<N>(
    override val x: N,
    override val y: N
) : Vector2<N>