/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:43.
 * Last edit 25.05.2019
 */
interface Vector2able<V : Vector2<*>> {
    val vector: V
}