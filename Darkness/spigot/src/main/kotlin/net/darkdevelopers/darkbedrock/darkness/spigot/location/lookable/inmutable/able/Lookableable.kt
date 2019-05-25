/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:03.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface Lookableable<L : Lookable<*>> {
    val lookable: L
}
