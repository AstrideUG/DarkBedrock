/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs

import net.darkdevelopers.darkbedrock.darkness.general.configs.defaultMappings
import net.darkdevelopers.darkbedrock.darkness.general.configs.mapped
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toMaterial
import net.darkdevelopers.darkbedrock.darkness.spigot.location.extensions.BukkitLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.extensions.toBukkitLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.LookableLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.alliases.DefaultLivingLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.serialization.deserialization.toLookableLocation
import org.bukkit.Material

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 13:51.
 * Last edit 05.06.2019
 */

val spigotDefaultMappings = mutableMapOf<Class<out Any>, (Any?) -> Any?>(
    Material::class.java to { any -> any?.mapped<String>()?.toMaterial() },
    LookableLocation::class.java to { any -> any?.mapped<Map<String, *>>()?.toLookableLocation() },
    BukkitLocation::class.java to { any -> any?.mapped<DefaultLivingLocation>()?.toBukkitLocation() }
)

fun initSpigotStaticConfigMappings() {
    defaultMappings += spigotDefaultMappings
}

fun resetSpigotStaticConfigMappings(): Unit = spigotDefaultMappings.keys.forEach { defaultMappings -= it }

