/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.RESET
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 04.01.2018 18:46.
 * Last edit 06.07.2018
 */
object InventoryUtils {

    fun sortChestInventory(inventory: Inventory, itemStacks: List<ItemStack>) = sortChestInventory(inventory, itemStacks, 0)

    fun sortChestInventory(inventory: Inventory, itemStacks: List<ItemStack>, addSlots: Int) {
        when (itemStacks.size) {
            1 -> inventory.setItem(4 + addSlots, itemStacks[0])
            2 -> {
                inventory.setItem(2 + addSlots, itemStacks[0])
                inventory.setItem(6 + addSlots, itemStacks[1])
            }
            3 -> {
                inventory.setItem(1 + addSlots, itemStacks[0])
                inventory.setItem(4 + addSlots, itemStacks[1])
                inventory.setItem(7 + addSlots, itemStacks[2])
            }
            4 -> {
                inventory.setItem(1 + addSlots, itemStacks[0])
                inventory.setItem(3 + addSlots, itemStacks[1])
                inventory.setItem(5 + addSlots, itemStacks[2])
                inventory.setItem(7 + addSlots, itemStacks[3])
            }
            5 -> {
                inventory.setItem(addSlots, itemStacks[0])
                inventory.setItem(2 + addSlots, itemStacks[1])
                inventory.setItem(4 + addSlots, itemStacks[2])
                inventory.setItem(6 + addSlots, itemStacks[3])
                inventory.setItem(8 + addSlots, itemStacks[4])
            }
            6 -> {
                inventory.setItem(1 + addSlots, itemStacks[0])
                inventory.setItem(2 + addSlots, itemStacks[1])
                inventory.setItem(3 + addSlots, itemStacks[2])
                inventory.setItem(5 + addSlots, itemStacks[3])
                inventory.setItem(6 + addSlots, itemStacks[4])
                inventory.setItem(7 + addSlots, itemStacks[5])
            }
            7 -> {
                inventory.setItem(addSlots, itemStacks[0])
                inventory.setItem(1 + addSlots, itemStacks[1])
                inventory.setItem(2 + addSlots, itemStacks[2])
                inventory.setItem(4 + addSlots, itemStacks[3])
                inventory.setItem(6 + addSlots, itemStacks[4])
                inventory.setItem(7 + addSlots, itemStacks[5])
                inventory.setItem(8 + addSlots, itemStacks[6])
            }
            8 -> {
                inventory.setItem(addSlots, itemStacks[0])
                inventory.setItem(1 + addSlots, itemStacks[1])
                inventory.setItem(2 + addSlots, itemStacks[2])
                inventory.setItem(3 + addSlots, itemStacks[3])
                inventory.setItem(5 + addSlots, itemStacks[4])
                inventory.setItem(6 + addSlots, itemStacks[5])
                inventory.setItem(7 + addSlots, itemStacks[6])
                inventory.setItem(8 + addSlots, itemStacks[7])
            }
            9 -> for (i in itemStacks.indices) inventory.setItem(i + addSlots, itemStacks[i])
        }
    }

    fun getInventorySize(size: Int): Int = if (size <= 9) 9 else if (size <= 18) 18 else if (size <= 27) 27 else if (size <= 36) 36 else if (size <= 45) 45 else 54

    private fun fillGlass(inventory: Inventory, durability: Short) {
        for (i in 0 until inventory.size) {
            if (inventory.getItem(i) == null) {
                setGlass(inventory, i, durability)
            }
        }
    }

    fun setDesign(inventory: Inventory) = InventoryUtils.setDesign(inventory, arrayListOf())

    fun setDesign(inventory: Inventory, items: List<ItemStack>) {
        when (inventory.type) {
            InventoryType.HOPPER -> when (items.size) {
                0 -> {
                    setGlass(inventory, 0, 15.toShort())
                    setGlass(inventory, 1, 0.toShort())
                    setGlass(inventory, 2, 0.toShort())
                    setGlass(inventory, 3, 0.toShort())
                    setGlass(inventory, 4, 15.toShort())
                }
                1 -> {
                    setGlass(inventory, 0, 15.toShort())
                    setGlass(inventory, 1, 0.toShort())
                    inventory.setItem(2, items[0])
                    setGlass(inventory, 3, 0.toShort())
                    setGlass(inventory, 4, 15.toShort())
                }
                2 -> {
                    setGlass(inventory, 0, 15.toShort())
                    inventory.setItem(1, items[0])
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[1])
                    setGlass(inventory, 4, 15.toShort())
                }
                3 -> {
                    setGlass(inventory, 0, 15.toShort())
                    for (i in 1 until items.size + 1) {
                        inventory.setItem(i, items[i])
                    }
                    setGlass(inventory, 4, 15.toShort())
                }
                4 -> {
                    inventory.setItem(0, items[0])
                    inventory.setItem(1, items[1])
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[2])
                    inventory.setItem(4, items[3])
                }
                5 -> for (i in items.indices) {
                    inventory.setItem(i, items[i])
                }
            }
            InventoryType.DISPENSER -> when (items.size) {
                0 -> {
                    setGlass(inventory, 0, 0.toShort())
                    setGlass(inventory, 1, 7.toShort())
                    setGlass(inventory, 2, 0.toShort())
                    setGlass(inventory, 3, 7.toShort())
                    setGlass(inventory, 4, 7.toShort())
                    setGlass(inventory, 5, 7.toShort())
                    setGlass(inventory, 6, 0.toShort())
                    setGlass(inventory, 7, 7.toShort())
                    setGlass(inventory, 8, 0.toShort())
                }
                1 -> {
                    setGlass(inventory, 0, 0.toShort())
                    setGlass(inventory, 1, 7.toShort())
                    setGlass(inventory, 2, 0.toShort())
                    setGlass(inventory, 3, 7.toShort())
                    inventory.setItem(0, items[0])
                    setGlass(inventory, 5, 7.toShort())
                    setGlass(inventory, 6, 0.toShort())
                    setGlass(inventory, 7, 7.toShort())
                    setGlass(inventory, 8, 0.toShort())
                }
                2 -> {
                    setGlass(inventory, 0, 0.toShort())
                    setGlass(inventory, 1, 7.toShort())
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[0])
                    setGlass(inventory, 4, 7.toShort())
                    inventory.setItem(5, items[1])
                    setGlass(inventory, 6, 0.toShort())
                    setGlass(inventory, 7, 7.toShort())
                    setGlass(inventory, 8, 0.toShort())
                }
                3 -> {
                    setGlass(inventory, 0, 0.toShort())
                    setGlass(inventory, 1, 7.toShort())
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[0])
                    inventory.setItem(4, items[1])
                    inventory.setItem(5, items[2])
                    setGlass(inventory, 6, 0.toShort())
                    setGlass(inventory, 7, 7.toShort())
                    setGlass(inventory, 8, 0.toShort())
                }
                4 -> {
                    setGlass(inventory, 0, 0.toShort())
                    inventory.setItem(1, items[0])
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[1])
                    setGlass(inventory, 4, 7.toShort())
                    inventory.setItem(5, items[2])
                    setGlass(inventory, 6, 0.toShort())
                    inventory.setItem(7, items[3])
                    setGlass(inventory, 8, 0.toShort())
                }
                5 -> {
                    setGlass(inventory, 0, 0.toShort())
                    inventory.setItem(1, items[0])
                    setGlass(inventory, 2, 0.toShort())
                    inventory.setItem(3, items[1])
                    inventory.setItem(4, items[2])
                    inventory.setItem(5, items[3])
                    setGlass(inventory, 6, 0.toShort())
                    inventory.setItem(7, items[4])
                    setGlass(inventory, 8, 0.toShort())
                }
                6 -> {
                    for (i in 0..2) {
                        inventory.setItem(i, items[i])
                    }
                    setGlass(inventory, 3, 7.toShort())
                    setGlass(inventory, 4, 0.toShort())
                    setGlass(inventory, 5, 7.toShort())
                    for (i in 5 until items.size) {
                        inventory.setItem(i, items[i])
                    }
                }
                7 -> {
                    for (i in 0..2) {
                        inventory.setItem(i, items[i])
                    }
                    setGlass(inventory, 3, 7.toShort())
                    inventory.setItem(4, items[4])
                    setGlass(inventory, 5, 7.toShort())
                    for (i in 5 until items.size) {
                        inventory.setItem(i, items[i])
                    }
                }
                8 -> {
                    for (i in 0..3) {
                        inventory.setItem(i, items[i])
                    }
                    setGlass(inventory, 4, 0.toShort())
                    for (i in 4 until items.size) {
                        inventory.setItem(i, items[i])
                    }
                }
                9 -> for (i in items.indices) {
                    inventory.setItem(i, items[i])
                }
            }
            InventoryType.CHEST -> when (inventory.size) {
                9 -> when (items.size) {
                    0 -> {
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                    }
                }
                18 -> when (items.size) {
                    0 -> {
                        //ZEILE 1
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9, 0.toShort())
                        setGlass(inventory, 10, 15.toShort())
                        setGlass(inventory, 12, 0.toShort())
                        setGlass(inventory, 13, 0.toShort())
                        setGlass(inventory, 14, 0.toShort())
                        setGlass(inventory, 16, 15.toShort())
                        setGlass(inventory, 17, 0.toShort())
                    }
                }//ZEILE 2
                27 -> when (items.size) {
                    0 -> {
                        //ZEILE 1
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9, 15.toShort())
                        setGlass(inventory, 17, 15.toShort())
                        //ZEILE 2
                        //ZEILE 3
                        setGlass(inventory, 18, 0.toShort())
                        setGlass(inventory, 19, 15.toShort())
                        setGlass(inventory, 21, 0.toShort())
                        setGlass(inventory, 22, 0.toShort())
                        setGlass(inventory, 23, 0.toShort())
                        setGlass(inventory, 25, 15.toShort())
                        setGlass(inventory, 26, 0.toShort())
                    }
                }//ZEILE 3
                36 -> when (items.size) {
                    0 -> {
                        //ZEILE 1
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9, 15.toShort())
                        setGlass(inventory, 17, 15.toShort())
                        //ZEILE 2
                        //ZEILE 3
                        setGlass(inventory, 18, 15.toShort())
                        setGlass(inventory, 26, 15.toShort())
                        //ZEILE 3
                        //ZEILE 4
                        setGlass(inventory, 27, 0.toShort())
                        setGlass(inventory, 28, 15.toShort())
                        setGlass(inventory, 30, 0.toShort())
                        setGlass(inventory, 31, 0.toShort())
                        setGlass(inventory, 32, 0.toShort())
                        setGlass(inventory, 34, 15.toShort())
                        setGlass(inventory, 35, 0.toShort())
                    }
                }//ZEILE 4
                45 -> when (items.size) {
                    0 -> {
                        //ZEILE 1
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9, 15.toShort())
                        setGlass(inventory, 17, 15.toShort())
                        //ZEILE 2
                        //ZEILE 3
                        setGlass(inventory, 18, 15.toShort())
                        setGlass(inventory, 26, 15.toShort())
                        //ZEILE 3
                        //ZEILE 4
                        setGlass(inventory, 27, 15.toShort())
                        setGlass(inventory, 35, 15.toShort())
                        //ZEILE 4
                        //ZEILE 5
                        setGlass(inventory, 36, 0.toShort())
                        setGlass(inventory, 37, 15.toShort())
                        setGlass(inventory, 39, 0.toShort())
                        setGlass(inventory, 40, 0.toShort())
                        setGlass(inventory, 41, 0.toShort())
                        setGlass(inventory, 43, 15.toShort())
                        setGlass(inventory, 44, 0.toShort())
                    }
                }//ZEILE 5
                54 -> when (items.size) {
                    0 -> {
                        //ZEILE 1
                        setGlass(inventory, 0, 0.toShort())
                        setGlass(inventory, 1, 15.toShort())
                        setGlass(inventory, 3, 0.toShort())
                        setGlass(inventory, 4, 0.toShort())
                        setGlass(inventory, 5, 0.toShort())
                        setGlass(inventory, 7, 15.toShort())
                        setGlass(inventory, 8, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9, 15.toShort())
                        setGlass(inventory, 17, 15.toShort())
                        //ZEILE 2
                        //ZEILE 3
                        setGlass(inventory, 18, 15.toShort())
                        setGlass(inventory, 26, 15.toShort())
                        //ZEILE 3
                        //ZEILE 4
                        setGlass(inventory, 27, 15.toShort())
                        setGlass(inventory, 35, 15.toShort())
                        //ZEILE 4
                        //ZEILE 5
                        setGlass(inventory, 36, 15.toShort())
                        setGlass(inventory, 44, 15.toShort())
                        //ZEILE 5
                        //ZEILE 6
                        setGlass(inventory, 45, 0.toShort())
                        setGlass(inventory, 46, 15.toShort())
                        setGlass(inventory, 48, 0.toShort())
                        setGlass(inventory, 49, 0.toShort())
                        setGlass(inventory, 50, 0.toShort())
                        setGlass(inventory, 52, 15.toShort())
                        setGlass(inventory, 53, 0.toShort())
                    }
                }//ZEILE 6
            }
            InventoryType.PLAYER -> {
                when (items.size) {
                    0 -> {
                        val i = 9
                        //ZEILE 1
                        setGlass(inventory, i, 0.toShort())
                        setGlass(inventory, 1 + i, 15.toShort())
                        setGlass(inventory, 3 + i, 0.toShort())
                        setGlass(inventory, 4 + i, 0.toShort())
                        setGlass(inventory, 5 + i, 0.toShort())
                        setGlass(inventory, 7 + i, 15.toShort())
                        setGlass(inventory, 8 + i, 0.toShort())
                        //ZEILE 1
                        //ZEILE 2
                        setGlass(inventory, 9 + i, 15.toShort())
                        setGlass(inventory, 17 + i, 15.toShort())
                        //ZEILE 2
                        //ZEILE 3
                        setGlass(inventory, 18 + i, 0.toShort())
                        setGlass(inventory, 19 + i, 15.toShort())
                        setGlass(inventory, 21 + i, 0.toShort())
                        setGlass(inventory, 22 + i, 0.toShort())
                        setGlass(inventory, 23 + i, 0.toShort())
                        setGlass(inventory, 25 + i, 15.toShort())
                        setGlass(inventory, 26 + i, 0.toShort())
                    }
                }//ZEILE 3
                //FILL GRAY GALSS
                for (i in 9 until inventory.size) {
                    if (inventory.getItem(i) == null) {
                        setGlass(inventory, i, 7.toShort())
                    }
                }
                //FILL GRAY GALSS
                return
            }
            else -> {
                /*ignored*/
            }
        }
        //FILL GRAY GALSS
        fillGlass(inventory, 7.toShort())
        //FILL GRAY GALSS
    }

    private fun setGlass(inventory: Inventory, slot: Int, durability: Short) {
        inventory.setItem(slot, ItemBuilder(Material.STAINED_GLASS_PANE).setName(RESET.toString()).setDurability(durability).build())
    }

    fun hasItems(player: Player, material: Material): Int {
        return hasItems(player.inventory, material)
    }

    fun hasItems(inventory: Inventory, material: Material): Int {
        return hasItems(inventory.contents, material)
    }

    fun hasItems(itemStacks: Array<ItemStack>, material: Material): Int {
        var count = 0
        for (item in itemStacks) if (item.type == material) count += item.amount
        return count
    }

    fun removeItems(player: Player, material: Material, cost: Int) {
        removeItems(player.inventory, material, cost)
        player.updateInventory()
    }

    fun removeItems(inventory: Inventory, material: Material, costs: Int) {
        var cost = costs
        for (item in inventory.contents) {
            if (item != null && item.type == material) {
                if (item.amount >= cost) {
                    if (item.amount - cost < 1) {
                        inventory.remove(item)
                    } else {
                        item.amount = item.amount - cost
                    }
                    return
                }
                if (item.amount < cost) {
                    cost -= item.amount
                    inventory.remove(item)
                }
            }
        }
    }

    fun removeItemInHand(player: Player) = if (player.itemInHand == null || player.itemInHand.amount == 1) player.itemInHand = null else player.itemInHand.amount = player.itemInHand.amount - 1
}
