/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.mutable.MutableLookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable.inmutable.Lookable
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.Vector3

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 08:13.
 * Current Version: 1.0 (25.05.2019 - 25.05.2019)
 */

private typealias Default = String

typealias ReadOnlyMutableLocation<W> = MutableLocation<W, *>
typealias ReadOnlyMutableLookableLocation<W> = MutableLookableLocation<W, *, *>

typealias MutableBlockLocation<W> = MutableLocation<W, Vector3<Int>>
typealias MutableEntityLocation<W> = MutableLookableLocation<W, Vector3<Double>, Lookable<Float>>


typealias DefaultReadOnlyMutableLocation = ReadOnlyMutableLocation<Default>
typealias DefaultReadOnlyMutableLookableLocation = ReadOnlyMutableLookableLocation<Default>

typealias DefaultMutableBlockLocation = MutableBlockLocation<Default>
typealias DefaultMutableEntityLocation = MutableEntityLocation<Default>
