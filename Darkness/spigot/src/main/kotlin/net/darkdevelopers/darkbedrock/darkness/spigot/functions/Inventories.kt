/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.listen
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
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */

inline fun Inventory.listenTop(
    plugin: Plugin,
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
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen(
    plugin,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick
) { event ->
    if (event.whoClicked.openInventory.topInventory == this) block(event)
}

inline fun Inventory.listenBottom(
    plugin: Plugin,
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
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen(
    plugin,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick
) { event ->
    if (event.whoClicked.openInventory.bottomInventory == this) block(event)
}

inline fun Inventory.listenClicked(
    plugin: Plugin,
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
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen(
    plugin,
    acceptCurrentItem,
    acceptSlot,
    acceptSlotType,
    acceptClick,
    acceptAction,
    acceptCursor,
    acceptHotBarButton,
    acceptIsLeftClick,
    acceptIsRightClick,
    acceptIsShiftClick
) { event ->
    if (event.clickedInventory == this) block(event)
}

inline fun Inventory.listen(
    plugin: Plugin,
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
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen<InventoryClickEvent>(plugin) listener@{ event ->
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

    block(event)
}