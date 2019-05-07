/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.general.functions.count
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.copy
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.RESET
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 04.01.2018 18:46.
 * Last edit 30.04.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 03:21.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
const val line: Int = 9
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 04:05.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
private const val dark = 15
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 04:08.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
private const val white = 0

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 06:53.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun getInventorySize(size: Int): Int = line * ((0..5).find { size <= line * it } ?: 6)

//TODO Edit name
fun Inventory.sortChestInventory(itemStacks: List<ItemStack>, addSlots: Int = 0) {
    when (itemStacks.size) {
        1 -> setItem(4 + addSlots, itemStacks[0])
        2 -> (2..6 step 4).withIndex().forEach { setItem(it.value + addSlots, itemStacks[it.index - 1]) }
        3 -> (1..7 step 3).withIndex().forEach { setItem(it.value + addSlots, itemStacks[it.index - 1]) }
        4 -> (1..7 step 2).withIndex().forEach { setItem(it.value + addSlots, itemStacks[it.index - 1]) }
        5 -> for (i in 0..4) setItem(i * 2 + addSlots, itemStacks[i])
        6 -> {
            for (i in 0..2) setItem(i + 1 + addSlots, itemStacks[i])
            for (i in 3..5) setItem(i + 2 + addSlots, itemStacks[i])
        }
        7 -> {
            for (i in 0..2) setItem(i + addSlots, itemStacks[i])
            setItem(4 + addSlots, itemStacks[3])
            for (i in 6..8) setItem(i + addSlots, itemStacks[i - 2])
        }
        8 -> {
            for (i in 0 until 4) setItem(i + addSlots, itemStacks[i])
            for (i in 5 until 8) setItem(i + addSlots, itemStacks[i])
        }
        9 -> for (i in itemStacks.indices) setItem(i + addSlots, itemStacks[i])
    }
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 03:59.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
@Suppress("NON_EXHAUSTIVE_WHEN")
fun Inventory.setDesign() {
    when (type) {
        InventoryType.HOPPER -> {
            wall(5)
            fillGlass(white)
            return
        }
        InventoryType.DISPENSER -> {
            wall(3)
            wall(3, 2)
        }
        InventoryType.CHEST -> frame()
        InventoryType.PLAYER -> frame(line)
    }
    //FILL GRAY GLASS
    fillGlass(7)
}

fun Inventory.fillGlass(durability: Number) {
    for (i in 0 until size) if (getItem(i) == null) setGlass(i, durability)
}

fun Inventory.setGlass(slot: Int, durability: Number): Unit = setItem(
    slot,
    ItemBuilder(Material.STAINED_GLASS_PANE).setName(RESET.toString()).setDurability(durability.toShort()).build()
)

fun Inventory.count(itemStack: ItemStack): Int =
    filter { it.copy(amount = 1) == itemStack.copy(amount = 1) }.count { it.amount }

fun Inventory.count(material: Material): Int = filter { it?.type == material }.count { it.amount }

fun Player.removeItemInHand(): Unit =
    if (itemInHand == null || itemInHand.amount <= 1) itemInHand = null else itemInHand.amount = itemInHand.amount - 1

fun Player.removeItems(itemStack: ItemStack) {
    inventory.removeItems(itemStack)
    updateInventory()
}

fun Inventory.removeItems(itemStack: ItemStack) {
    var cost = itemStack.amount
    forEach { item ->
        if (item.copy(amount = 1) != itemStack.copy(amount = 1)) return@forEach
        val amount = item.amount
        if (amount >= cost) {
            item.amount -= cost
            return
        }
        cost -= amount
        remove(item)
    }

}

fun Player.removeItems(material: Material, cost: Int) {
    inventory.removeItems(material, cost)
    updateInventory()
}

fun Inventory.removeItems(material: Material, costs: Int) {
    var cost = costs
    forEach { item ->
        if (item?.type != material) return@forEach
        val amount = item.amount
        if (amount >= cost) {
            item.amount -= cost
            return
        }
        cost -= amount
        remove(item)
    }
}

private fun Inventory.frame(add: Int = 0) {
    boarder(add)
    walls()
}

private fun Inventory.walls(line0: Int = line) {
    val iterations = size / line0 - 2
    for (i in 1..iterations) wall(add = i)
}

private fun Inventory.wall(line0: Int = line, add: Int = 0) {
    val slot = (add * line0) //+ line0
    setGlass(slot, dark)
    setGlass(size - slot - 1, dark)
}

private fun Inventory.boarder(add: Int = 0) {
    val size1 = size + add
    if (size1 < 9) return
    if (size1 >= 18) boarderLine(add)
    boarderLine(size - line)
}

private fun Inventory.boarderLine(add: Int = 0) {
    loop@ for (i in 0 until line) {
        val durability = when (i) {
            1, 7 -> dark
            in 3..5 -> white
            else -> continue@loop
        }
        setGlass(add + i, durability)
    }
}
