/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("ItemStackUtils")

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

fun ItemStack.equals(itemStack: ItemStack, ignoreAmount: Boolean = false): Boolean {
    val first = if (ignoreAmount) itemStack.copy(amount = 1) else itemStack
    val second = if (ignoreAmount) this.copy(amount = 1) else this
    return first == second
}
