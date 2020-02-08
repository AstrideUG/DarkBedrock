/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map.worldborder

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.alliases.Vector2D
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:32.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
data class DataWorldBorder(
    override val size: Double = 60_000_000.0,
    override val center: Vector2D = 0.0.toVector2(),
    override val damageBuffer: Double = 0.0,
    override val damageAmount: Double = 0.0,
    override val warningTime: Int = 0,
    override val warningDistance: Int = 0
) : WorldBorder
