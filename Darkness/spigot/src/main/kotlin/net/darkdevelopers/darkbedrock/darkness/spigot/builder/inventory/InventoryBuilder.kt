/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.inventory

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.inventory.interfaces.IInventoryBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.setDesign
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.sortChestInventory
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

@Suppress("unused")
/**
 * Created by Segdo aka. Dominik Milenko on 11.05.2018 08:46.
 * @author Lars Artmann | LartyHD
 * Last edit 21.03.2019
 */
class InventoryBuilder(private val inventory: Inventory) : IInventoryBuilder {

    constructor(type: InventoryType) : this(null, type)

    constructor(slots: Int) : this(null, slots)

    constructor(type: InventoryType, name: String) : this(null, type, saveTitle(name))

    constructor(slots: Int, name: String) : this(null, slots, saveTitle(name))

    constructor(owner: InventoryHolder?, type: InventoryType) : this(Bukkit.createInventory(owner, type))

    constructor(owner: InventoryHolder?, type: InventoryType, name: String) : this(
        Bukkit.createInventory(owner, type, saveTitle(name))
    )

    constructor(owner: InventoryHolder?, slots: Int) : this(Bukkit.createInventory(owner, slots))

    constructor(owner: InventoryHolder?, slots: Int, name: String) : this(
        Bukkit.createInventory(owner, slots, saveTitle(name))
    )

    override fun addItem(itemStack: ItemStack): IInventoryBuilder = apply { inventory.addItem(itemStack) }

    override fun setItem(slot: Int, itemStack: ItemStack): IInventoryBuilder =
        apply { inventory.setItem(slot, itemStack) }

    override fun setAll(itemStack: ItemStack): IInventoryBuilder = apply {
        for (i in inventory.contents.indices) inventory.setItem(i, itemStack)
    }

    override fun setRange(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder = apply {
        if (start < 0 || end > inventory.size) throw IndexOutOfBoundsException("range out of inventory size")
        for (i in start until end) inventory.setItem(i, itemStack)
    }

    override fun fillWith(itemStack: ItemStack): IInventoryBuilder = apply {
        for (i in inventory.contents.indices) inventory.checkedSet(itemStack, i)
    }

    override fun fillWith(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder = apply {
        if (start < 0 || end > inventory.size) throw IndexOutOfBoundsException("range out of inventory size")
        for (i in start until end - 1) inventory.checkedSet(itemStack, i)
    }

    override fun sortChestInventory(itemStacks: List<ItemStack>, addSlots: Int): IInventoryBuilder =
        apply { inventory.sortChestInventory(itemStacks, addSlots) }

    override fun setDesign(): IInventoryBuilder = apply { inventory.setDesign() }

    override fun build() = inventory

    companion object {

        private fun saveTitle(title: String) = if (title.length > 32) {
            System.err.println("Title ($title) is longer then 32 characters")
            title.take(32)
        } else title

    }

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 05:01.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
private fun Inventory.checkedSet(itemStack: ItemStack, i: Int) {
    if (getItem(i) != null || getItem(i).type == Material.AIR) setItem(i, itemStack)
}



