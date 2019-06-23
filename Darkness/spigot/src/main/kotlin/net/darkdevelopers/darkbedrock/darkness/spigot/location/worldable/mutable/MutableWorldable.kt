/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.worldable.mutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.worldable.inmutable.Worldable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:41.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableWorldable<W> : Worldable<W> {
    override var world: W
}