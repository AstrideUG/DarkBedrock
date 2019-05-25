/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.able.Vector2able
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector2

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:44.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableVector2able<V : MutableVector2<*>> : Vector2able<V> {
    override val vector: V
}