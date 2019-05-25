/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.able.Vector3able
import net.darkdevelopers.darkbedrock.darkness.spigot.location.worldable.inmutable.Worldable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 12:58.
 * Current Version: 1.0 (09.05.2019 - 25.05.2019)
 */
interface Location<W, V : Vector3<*>> : Worldable<W>, Vector3able<V>
