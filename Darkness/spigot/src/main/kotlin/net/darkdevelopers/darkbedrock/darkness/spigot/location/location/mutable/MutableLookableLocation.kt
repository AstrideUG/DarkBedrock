/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:12.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableLookableLocation<W, V : Vector3<*>, L : Lookable<*>>
    : MutableLocation<W, V>, LookableLocation<W, V, L> {
    override var lookable: L
}