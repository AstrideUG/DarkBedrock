/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.Location

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:49.
 * Last edit 25.05.2019
 */
interface Locatable<L : Location<*, *>> {
    val location: L
}