/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.ItemBuilder
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
 * Last edit 24.08.2018
 */
object InventoryUtils {
    fun hasItems(itemStacks: Array<ItemStack>, material: Material): Int {
        var count = 0
        itemStacks.forEach { if (it.type == material) count += it.amount }
        return count
    }

    fun getInventorySize(size: Int): Int =
        if (size <= 9) 9 else if (size <= 18) 18 else if (size <= 27) 27 else if (size <= 36) 36 else if (size <= 45) 45 else 54

}

fun Player.removeItemInHand() =
    if (itemInHand == null || itemInHand.amount == 1) itemInHand = null else itemInHand.amount = itemInHand.amount - 1

//TODO Update The SetItems
fun Inventory.sortChestInventory(itemStacks: List<ItemStack>, addSlots: Int = 0) {
    when (itemStacks.size) {
        1 -> setItem(4 + addSlots, itemStacks[0])
        2 -> {
            setItem(2 + addSlots, itemStacks[0])
            setItem(6 + addSlots, itemStacks[1])
        }
        3 -> {
            setItem(1 + addSlots, itemStacks[0])
            setItem(4 + addSlots, itemStacks[1])
            setItem(7 + addSlots, itemStacks[2])
        }
        4 -> {
            setItem(1 + addSlots, itemStacks[0])
            setItem(3 + addSlots, itemStacks[1])
            setItem(5 + addSlots, itemStacks[2])
            setItem(7 + addSlots, itemStacks[3])
        }
        5 -> {
            for (i in 0..2) setItem(i * 2 + addSlots, itemStacks[i])
            for (i in 3..4) setItem(i * 2 + addSlots, itemStacks[i])
        }
        6 -> {
            for (i in 0..2) setItem(i + 1 + addSlots, itemStacks[i])
            for (i in 3..5) setItem(i + 2 + addSlots, itemStacks[i])
        }
        7 -> {
            setItem(addSlots, itemStacks[0])
            setItem(1 + addSlots, itemStacks[1])
            setItem(2 + addSlots, itemStacks[2])
            setItem(4 + addSlots, itemStacks[3])
            setItem(6 + addSlots, itemStacks[4])
            setItem(7 + addSlots, itemStacks[5])
            setItem(8 + addSlots, itemStacks[6])
        }
        8 -> {
            setItem(addSlots, itemStacks[0])
            setItem(1 + addSlots, itemStacks[1])
            setItem(2 + addSlots, itemStacks[2])
            setItem(3 + addSlots, itemStacks[3])
            setItem(5 + addSlots, itemStacks[4])
            setItem(6 + addSlots, itemStacks[5])
            setItem(7 + addSlots, itemStacks[6])
            setItem(8 + addSlots, itemStacks[7])
        }
        9 -> for (i in itemStacks.indices) setItem(i + addSlots, itemStacks[i])
    }
}

fun Inventory.setDesign() = setDesign(arrayListOf())

fun Inventory.setDesign(items: List<ItemStack>) {
    when (type) {
        InventoryType.HOPPER -> when (items.size) {
            0 -> {
                setGlass(0, 15)
                setGlass(1, 0)
                setGlass(2, 0)
                setGlass(3, 0)
                setGlass(4, 15)
            }
            1 -> {
                setGlass(0, 15)
                setGlass(1, 0)
                setItem(2, items[0])
                setGlass(3, 0)
                setGlass(4, 15)
            }
            2 -> {
                setGlass(0, 15)
                setItem(1, items[0])
                setGlass(2, 0)
                setItem(3, items[1])
                setGlass(4, 15)
            }
            3 -> {
                setGlass(0, 15)
                for (i in 1 until items.size + 1) setItem(i, items[i])
                setGlass(4, 15)
            }
            4 -> {
                setItem(0, items[0])
                setItem(1, items[1])
                setGlass(2, 0)
                setItem(3, items[2])
                setItem(4, items[3])
            }
            5 -> for (i in items.indices) setItem(i, items[i])
        }
        InventoryType.DISPENSER -> when (items.size) {
            0 -> {
                setGlass(0, 0)
                setGlass(1, 7)
                setGlass(2, 0)
                for (i in 3..5) setGlass(i, 7)
                setGlass(6, 0)
                setGlass(7, 7)
                setGlass(8, 0)
            }
            1 -> {
                setGlass(0, 0)
                setGlass(1, 7)
                setGlass(2, 0)
                setGlass(3, 7)
                setItem(0, items[0])
                setGlass(5, 7)
                setGlass(6, 0)
                setGlass(7, 7)
                setGlass(8, 0)
            }
            2 -> {
                setGlass(0, 0)
                setGlass(1, 7)
                setGlass(2, 0)
                setItem(3, items[0])
                setGlass(4, 7)
                setItem(5, items[1])
                setGlass(6, 0)
                setGlass(7, 7)
                setGlass(8, 0)
            }
            3 -> {
                setGlass(0, 0)
                setGlass(1, 7)
                setGlass(2, 0)
                setItem(3, items[0])
                setItem(4, items[1])
                setItem(5, items[2])
                setGlass(6, 0)
                setGlass(7, 7)
                setGlass(8, 0)
            }
            4 -> {
                setGlass(0, 0)
                setItem(1, items[0])
                setGlass(2, 0)
                setItem(3, items[1])
                setGlass(4, 7)
                setItem(5, items[2])
                setGlass(6, 0)
                setItem(7, items[3])
                setGlass(8, 0)
            }
            5 -> {
                setGlass(0, 0)
                setItem(1, items[0])
                setGlass(2, 0)
                setItem(3, items[1])
                setItem(4, items[2])
                setItem(5, items[3])
                setGlass(6, 0)
                setItem(7, items[4])
                setGlass(8, 0)
            }
            6 -> {
                for (i in 0..2) setItem(i, items[i])
                setGlass(3, 7)
                setGlass(4, 0)
                setGlass(5, 7)
                for (i in 5 until items.size) setItem(i, items[i])
            }
            7 -> {
                for (i in 0..2) setItem(i, items[i])
                setGlass(3, 7)
                setItem(4, items[4])
                setGlass(5, 7)
                for (i in 5 until items.size) setItem(i, items[i])
            }
            8 -> {
                for (i in 0..3) setItem(i, items[i])
                setGlass(4, 0)
                for (i in 4 until items.size) setItem(i, items[i])
            }
            9 -> for (i in items.indices) setItem(i, items[i])
        }
        InventoryType.CHEST -> when (size) {
            9 -> when (items.size) {
                0 -> {
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                }
            }
            18 -> when (items.size) {
                0 -> {
                    //ZEILE 1
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9, 0)
                    setGlass(10, 15)
                    setGlass(12, 0)
                    setGlass(13, 0)
                    setGlass(14, 0)
                    setGlass(16, 15)
                    setGlass(17, 0)
                }
            }//ZEILE 2
            27 -> when (items.size) {
                0 -> {
                    //ZEILE 1
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9, 15)
                    setGlass(17, 15)
                    //ZEILE 2
                    //ZEILE 3
                    setGlass(18, 0)
                    setGlass(19, 15)
                    setGlass(21, 0)
                    setGlass(22, 0)
                    setGlass(23, 0)
                    setGlass(25, 15)
                    setGlass(26, 0)
                }
            }//ZEILE 3
            36 -> when (items.size) {
                0 -> {
                    //ZEILE 1
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9, 15)
                    setGlass(17, 15)
                    //ZEILE 2
                    //ZEILE 3
                    setGlass(18, 15)
                    setGlass(26, 15)
                    //ZEILE 3
                    //ZEILE 4
                    setGlass(27, 0)
                    setGlass(28, 15)
                    setGlass(30, 0)
                    setGlass(31, 0)
                    setGlass(32, 0)
                    setGlass(34, 15)
                    setGlass(35, 0)
                }
            }//ZEILE 4
            45 -> when (items.size) {
                0 -> {
                    //ZEILE 1
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9, 15)
                    setGlass(17, 15)
                    //ZEILE 2
                    //ZEILE 3
                    setGlass(18, 15)
                    setGlass(26, 15)
                    //ZEILE 3
                    //ZEILE 4
                    setGlass(27, 15)
                    setGlass(35, 15)
                    //ZEILE 4
                    //ZEILE 5
                    setGlass(36, 0)
                    setGlass(37, 15)
                    setGlass(39, 0)
                    setGlass(40, 0)
                    setGlass(41, 0)
                    setGlass(43, 15)
                    setGlass(44, 0)
                }
            }//ZEILE 5
            54 -> when (items.size) {
                0 -> {
                    //ZEILE 1
                    setGlass(0, 0)
                    setGlass(1, 15)
                    setGlass(3, 0)
                    setGlass(4, 0)
                    setGlass(5, 0)
                    setGlass(7, 15)
                    setGlass(8, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9, 15)
                    setGlass(17, 15)
                    //ZEILE 2
                    //ZEILE 3
                    setGlass(18, 15)
                    setGlass(26, 15)
                    //ZEILE 3
                    //ZEILE 4
                    setGlass(27, 15)
                    setGlass(35, 15)
                    //ZEILE 4
                    //ZEILE 5
                    setGlass(36, 15)
                    setGlass(44, 15)
                    //ZEILE 5
                    //ZEILE 6
                    setGlass(45, 0)
                    setGlass(46, 15)
                    setGlass(48, 0)
                    setGlass(49, 0)
                    setGlass(50, 0)
                    setGlass(52, 15)
                    setGlass(53, 0)
                }
            }//ZEILE 6
        }
        InventoryType.PLAYER -> {
            when (items.size) {
                0 -> {
                    val i = 9
                    //ZEILE 1
                    setGlass(i, 0)
                    setGlass(1 + i, 15)
                    setGlass(3 + i, 0)
                    setGlass(4 + i, 0)
                    setGlass(5 + i, 0)
                    setGlass(7 + i, 15)
                    setGlass(8 + i, 0)
                    //ZEILE 1
                    //ZEILE 2
                    setGlass(9 + i, 15)
                    setGlass(17 + i, 15)
                    //ZEILE 2
                    //ZEILE 3
                    setGlass(18 + i, 0)
                    setGlass(19 + i, 15)
                    setGlass(21 + i, 0)
                    setGlass(22 + i, 0)
                    setGlass(23 + i, 0)
                    setGlass(25 + i, 15)
                    setGlass(26 + i, 0)
                }
            }//ZEILE 3
            //FILL GRAY GALSS
            fillGlass(7)
            //FILL GRAY GALSS
            return
        }
        else -> {
            /*ignored*/
        }
    }
    //FILL GRAY GALSS
    fillGlass(7)
    //FILL GRAY GALSS
}

fun Inventory.fillGlass(durability: Short) {
    for (i in 0 until size) if (getItem(i) == null) setGlass(i, durability)
}

fun Inventory.setGlass(slot: Int, durability: Int) = setGlass(slot, durability.toShort())

fun Inventory.setGlass(slot: Int, durability: Short) =
    setItem(slot, ItemBuilder(Material.STAINED_GLASS_PANE).setName(RESET.toString()).setDurability(durability).build())

fun Inventory.hasItems(material: Material) = InventoryUtils.hasItems(contents, material)

fun Player.removeItems(material: Material, cost: Int) {
    inventory.removeItems(material, cost)
    updateInventory()
}

fun Inventory.removeItems(material: Material, costs: Int) {
    var cost = costs
    for (item in contents) {
        if (item != null && item.type == material) {
            val amount = item.amount
            if (amount >= cost) {
                if (amount - cost < 1) remove(item) else item.amount -= cost
                return
            }
            if (amount < cost) {
                cost -= amount
                remove(item)
            }
        }
    }
}
