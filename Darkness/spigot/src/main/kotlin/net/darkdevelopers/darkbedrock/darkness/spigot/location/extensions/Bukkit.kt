/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.location.extensions

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.*
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.locationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import org.bukkit.Bukkit

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.05.2019 09:58.
 * Last edit 25.05.2019
 */

typealias BukkitLocation = org.bukkit.Location

fun DefaultLivingLocation.toBukkitLocation(): BukkitLocation =
    BukkitLocation(Bukkit.getWorld(world), x, y, z, yaw, pitch)

fun BukkitLocation.toLocation(): DefaultEntityLocation = locationOf(world?.name.toString(), x, y, z)
fun BukkitLocation.toLookableLocation(): DefaultLivingLocation = lookableLocationOf(toLocation(), yaw, pitch)
