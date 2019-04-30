/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Material

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:07.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:08.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
fun Material.isHelmet(): Boolean = name.endsWith("_HELMET")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:09.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
fun Material.isChestplate(): Boolean = name.endsWith("_CHESTPLATE")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:10.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
fun Material.isLeggings(): Boolean = name.endsWith("_LEGGINGS")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:10.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
fun Material.isBoots(): Boolean = name.endsWith("_BOOTS")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 04:11.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */
fun Material.isArmor(): Boolean = isHelmet() || isChestplate() || isLeggings() || isBoots()
