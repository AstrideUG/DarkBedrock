/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.cancel
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.listen
import org.bukkit.entity.HumanEntity
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 13:31.
 * Current Version: 1.0 (07.05.2019 - 08.05.2019)
 */

inline fun Iterable<Inventory>.listenTop(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.topInventory
    if (onlyCheckName) {
        if (inventory.name !in map { it.name }) return@listenInventories
        event.blockIfIsShift(cancel)
    } else {
        if (event.clickedInventory !in this) return@listenInventories
        event.blockIfIsShift(cancel)
        if (inventory !in this) return@listenInventories
    }

    block(event)
}

inline fun Iterable<Inventory>.listenBottom(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.bottomInventory
    if (onlyCheckName) {
        if (inventory.name !in map { it.name }) return@listenInventories
        event.blockIfIsShift(cancel)
    } else {
        if (event.clickedInventory !in this) return@listenInventories
        event.blockIfIsShift(cancel)
        if (inventory !in this) return@listenInventories
    }

    block(event)
}

inline fun Iterable<Inventory>.listenClicked(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { true },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    if (onlyCheckName) {
        if (event.clickedInventory.name !in map { it.name }) return@listenInventories
    } else if (event.clickedInventory !in this) return@listenInventories

    block(event)
}

inline fun Inventory.listenTop(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.topInventory
    if (onlyCheckName) {
        if (inventory.name != name) return@listenInventories
        event.blockIfIsShift(cancel)
    } else {
        if (event.clickedInventory != this) return@listenInventories
        event.blockIfIsShift(cancel)
        if (inventory != this) return@listenInventories
    }

    block(event)
}

inline fun Inventory.listenBottom(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.bottomInventory
    if (onlyCheckName) {
        if (inventory.name != name) return@listenInventories
        event.blockIfIsShift(cancel)
    } else {
        if (event.clickedInventory != this) return@listenInventories
        event.blockIfIsShift(cancel)
        if (inventory != this) return@listenInventories
    }

    block(event)
}

inline fun Inventory.listenClicked(
    plugin: Plugin,
    onlyCheckName: Boolean = false,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { true },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.clickedInventory
    if (onlyCheckName) {
        if (inventory.name != name) return@listenInventories
    } else if (event.clickedInventory != this) return@listenInventories

    block(event)
}

inline fun Iterable<String>.listenTop(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.topInventory
    if (event.clickedInventory.name !in this) return@listenInventories
    event.blockIfIsShift(cancel)
    if (inventory.name !in this) return@listenInventories

    block(event)

}

inline fun Iterable<String>.listenBottom(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.bottomInventory
    if (event.clickedInventory.name !in this) return@listenInventories
    event.blockIfIsShift(cancel)
    if (inventory.name !in this) return@listenInventories

    block(event)
}

inline fun Iterable<String>.listenClicked(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { true },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    if (event.clickedInventory.name !in this) return@listenInventories
    block(event)

}

inline fun String.listenTop(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.topInventory
    if (event.clickedInventory.name != this) return@listenInventories
    event.blockIfIsShift(cancel)
    if (inventory.name != this) return@listenInventories

    block(event)

}

inline fun String.listenBottom(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { false },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    val inventory = event.whoClicked.openInventory.bottomInventory
    if (event.clickedInventory.name != this) return@listenInventories
    event.blockIfIsShift(cancel)
    if (inventory.name != this) return@listenInventories

    block(event)

}

inline fun String.listenClicked(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { true },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listenInventories(
    plugin,
    acceptWhoClicked,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick,
    cancel
) { event ->

    if (event.clickedInventory.name != this) return@listenInventories
    block(event)

}

inline fun listenInventories(
    plugin: Plugin,
    crossinline acceptWhoClicked: (HumanEntity) -> Boolean = { true },
    crossinline acceptCurrentItem: (ItemStack?) -> Boolean = { it != null },
    crossinline acceptSlot: (Int) -> Boolean = { true },
    crossinline acceptSlotType: (InventoryType.SlotType) -> Boolean = { true },
    crossinline acceptClick: (ClickType) -> Boolean = { true },
    crossinline acceptAction: (InventoryAction) -> Boolean = { true },
    crossinline acceptCursor: (ItemStack?) -> Boolean = { true },
    crossinline acceptHotBarButton: (Int) -> Boolean = { true },
    crossinline acceptIsLeftClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsRightClick: (Boolean) -> Boolean = { true },
    crossinline acceptIsShiftClick: (Boolean) -> Boolean = { true },
    cancel: Boolean = false,
    crossinline block: (InventoryClickEvent) -> Unit = {}
): Listener = listen<InventoryClickEvent>(plugin) listener@{ event ->
    if (!acceptWhoClicked(event.whoClicked)) return@listener
    if (!acceptCurrentItem(event.currentItem)) return@listener
    if (!acceptSlot(event.slot)) return@listener
    if (!acceptSlotType(event.slotType)) return@listener
    if (!acceptClick(event.click)) return@listener
    if (!acceptAction(event.action)) return@listener
    if (!acceptCursor(event.cursor)) return@listener
    if (!acceptHotBarButton(event.hotbarButton)) return@listener
    if (!acceptIsLeftClick(event.isLeftClick)) return@listener
    if (!acceptIsRightClick(event.isRightClick)) return@listener
    if (!acceptIsShiftClick(event.isShiftClick)) return@listener
    if (cancel) event.cancel()

    block(event)
}

fun InventoryClickEvent.blockIfIsShift(cancel: Boolean) {
    if (cancel && isShiftClick) cancel()
}