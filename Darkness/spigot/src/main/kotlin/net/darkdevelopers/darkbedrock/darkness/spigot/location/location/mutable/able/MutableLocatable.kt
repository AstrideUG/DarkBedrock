/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.able.Locatable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:02.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableLocatable<L : Location<*, *>> : Locatable<L> {
    override var location: L
}