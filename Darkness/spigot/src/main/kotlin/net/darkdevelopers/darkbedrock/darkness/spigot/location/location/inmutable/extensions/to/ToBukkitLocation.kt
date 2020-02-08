/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.to

import net.darkdevelopers.darkbedrock.darkness.spigot.aliases.BukkitLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toBukkitWorld
import net.darkdevelopers.darkbedrock.darkness.spigot.location.extensions.toLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultBlockLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.x
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.y
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.z

/*
 * Created on 24.06.2019 09:38.
 * @author Lars Artmann | LartyHD
 */

fun DefaultBlockLocation.toBukkitLocation(): BukkitLocation = BukkitLocation(
    world.toBukkitWorld(), x.toDouble(), y.toDouble(), z.toDouble()
)

fun BukkitLocation.toDefaultBlockLocation(): DefaultBlockLocation = toLocation().toLocation3I()