/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:44.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface Vector3able<V : Vector3<*>> {
    val vector: V
}