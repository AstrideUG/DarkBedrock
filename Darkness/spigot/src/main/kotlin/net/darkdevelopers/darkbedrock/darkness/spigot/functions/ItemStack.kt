/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.04.2019 02:37.
 * Current Version: 1.0 (30.04.2019 - 30.04.2019)
 */

fun ItemStack.copy(
    type: Material? = null,
    amount: Int? = null,
    durability: Short? = null,
    itemMeta: ItemMeta? = null
): ItemStack = clone().apply {
    if (type != null) this.type = type
    if (amount != null) this.amount = amount
    if (durability != null) this.durability = durability
    if (itemMeta != null) this.itemMeta = itemMeta
}
