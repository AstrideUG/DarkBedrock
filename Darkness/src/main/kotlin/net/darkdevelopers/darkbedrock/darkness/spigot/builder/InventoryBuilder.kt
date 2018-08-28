/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko 2018
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.interfaces.IInventoryBuilder
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
 * @author Lars Artmann | LartyHD & Segdo aka. Dominik Milenko
 * Last edit 24.08.2018
 */
class InventoryBuilder(private val inventory: Inventory) : IInventoryBuilder {

    constructor(type: InventoryType) : this(null, type)

    constructor(slots: Int) : this(null, slots)

    constructor(type: InventoryType, name: String) : this(null, type, saveTitle(name))

    constructor(slots: Int, name: String) : this(null, slots, saveTitle(name))

    constructor(owner: InventoryHolder?, type: InventoryType) : this(Bukkit.createInventory(owner, type))

    constructor(owner: InventoryHolder?, type: InventoryType, name: String) : this(Bukkit.createInventory(owner, type, saveTitle(name)))

    constructor(owner: InventoryHolder?, slots: Int) : this(Bukkit.createInventory(owner, slots))

    constructor(owner: InventoryHolder?, slots: Int, name: String) : this(Bukkit.createInventory(owner, slots, saveTitle(name)))

    override fun addItem(itemStack: ItemStack): IInventoryBuilder {
        inventory.addItem(itemStack)
        return this
    }

    override fun setItem(slot: Int, itemStack: ItemStack): IInventoryBuilder {
        inventory.setItem(slot, itemStack)
        return this
    }

    override fun setAll(itemStack: ItemStack): IInventoryBuilder {
        for (i in 0 until inventory.size - 1) inventory.setItem(i, itemStack)
        return this
    }

    override fun setRange(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder {
        if (start < 0 || end > inventory.size) throw IndexOutOfBoundsException("range out of inventory size")
        for (i in start until end) inventory.setItem(i, itemStack)
        return this
    }

    override fun fillWith(itemStack: ItemStack): IInventoryBuilder {
        for (i in 0 until inventory.size - 1)
            if (inventory.getItem(i) != null && inventory.getItem(i).type != Material.AIR)
                inventory.setItem(i, itemStack)
        return this
    }

    override fun fillWith(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder {
        if (start < 0 || end > inventory.size) throw IndexOutOfBoundsException("range out of inventory size")
        for (i in start until end - 1)
            if (inventory.getItem(i) != null && inventory.getItem(i).type != Material.AIR)
                inventory.setItem(i, itemStack)
        return this
    }

    override fun setDesign() = setDesign(arrayListOf())

    override fun setDesign(items: List<ItemStack>): IInventoryBuilder {
        inventory.setDesign(items)
        return this
    }

    override fun sortChestInventory(itemStacks: List<ItemStack>, addSlots: Int): IInventoryBuilder {
        inventory.sortChestInventory(itemStacks, addSlots)
        return this
    }

    override fun build() = inventory

    companion object {
        private fun saveTitle(title: String) = if (title.length > 32) {
            System.err.println("Title ($title) is longer then 32 characters")
            title.substring(0, 31)
        } else title
    }
}


