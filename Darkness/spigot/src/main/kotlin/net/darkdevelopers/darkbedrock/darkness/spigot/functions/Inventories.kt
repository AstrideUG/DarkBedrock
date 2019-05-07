/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.listen
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 13:31.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */

inline fun Inventory.listenTop(
    plugin: Plugin,
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen<InventoryClickEvent>(plugin) { event ->
    if (event.whoClicked.openInventory.topInventory == this) block(event)
}

inline fun Inventory.listenBottom(
    plugin: Plugin,
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen<InventoryClickEvent>(plugin) { event ->
    if (event.whoClicked.openInventory.bottomInventory == this) block(event)
}

inline fun Inventory.listenClicked(
    plugin: Plugin,
    crossinline block: (InventoryClickEvent) -> Unit
): Listener = listen<InventoryClickEvent>(plugin) { event ->
    if (event.clickedInventory == this) block(event)
}