/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.able

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.able.Vector3able
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.mutable.MutableVector3

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 07:46.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */
interface MutableVector3able<V : MutableVector3<*>> : Vector3able<V> {
    override val vector: V
}