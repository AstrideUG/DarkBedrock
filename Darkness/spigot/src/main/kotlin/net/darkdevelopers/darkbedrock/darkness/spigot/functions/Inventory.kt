/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.inventory.InventoryBuilder
import org.bukkit.inventory.Inventory

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:03.
 * Last edit 05.06.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:03.
 * Current Version: 1.0 (05.06.2019 - 05.06.2019)
 */
fun Inventory.copy(): Inventory = InventoryBuilder(size, title).build().apply {
    contents = this@copy.contents
}