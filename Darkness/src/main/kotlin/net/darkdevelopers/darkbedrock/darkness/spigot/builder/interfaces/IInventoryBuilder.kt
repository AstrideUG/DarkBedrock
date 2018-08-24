/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko 2018
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.interfaces

import net.darkdevelopers.darkbedrock.darkness.general.builder.interfaces.Builder
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Created by Segdo aka. Dominik Milenko on 11.05.2018 08:38.
 * Project: Darkness
 *
 * Web: https://segdogames.com
 * Mail: segdo@segdogames.com
 * Last edit 07.07.2018
 */
interface IInventoryBuilder : Builder<Inventory> {

    fun addItem(itemStack: ItemStack): IInventoryBuilder

    fun setItem(slot: Int, itemStack: ItemStack): IInventoryBuilder

    fun setAll(itemStack: ItemStack): IInventoryBuilder

    fun setRange(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder

    fun fillWith(itemStack: ItemStack): IInventoryBuilder

    fun fillWith(itemStack: ItemStack, start: Int, end: Int): IInventoryBuilder

    fun setDesign(): IInventoryBuilder

    fun setDesign(items: List<ItemStack>): IInventoryBuilder

    fun sortChestInventory(itemStacks: List<ItemStack>, addSlots: Int = 0): IInventoryBuilder

}