package net.darkdevelopers.darkbedrock.darkness.spigot.utils

/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.minecraft.server.v1_8_R3.*
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.04.2019 15:32.
 * Current Version: 1.0 (19.04.2019 - 19.04.2019)
 */
class AnvilGUI(val player: Player, javaPlugin: JavaPlugin, private val block: (AnvilClickEvent) -> Unit) {

    private val items: MutableMap<AnvilSlot, ItemStack> = mutableMapOf()
    private var inventory: Inventory? = null
    private val listener: Listener = object : Listener(javaPlugin) {
        @EventHandler
        fun onInventoryClick(event: InventoryClickEvent) {
            if (event.whoClicked !== player) return
            if (event.inventory != inventory) return
            event.cancel()

            val item = event.currentItem
            var name: String? = null

            if (item != null && item.hasItemMeta() && item.itemMeta.hasDisplayName()) name = item.itemMeta.displayName

            val clickEvent = AnvilClickEvent(AnvilSlot.bySlot(event.rawSlot) ?: return, name)
            block(clickEvent)

            if (clickEvent.willClose) event.whoClicked.closeInventory()
            if (clickEvent.willDestroy) destroy()
        }

        @EventHandler
        fun onInventoryClose(event: InventoryCloseEvent) {

            if (event.player !== player) return
            if (event.inventory != this@AnvilGUI.inventory) return
            event.inventory.clear()
            destroy()
        }

        @EventHandler
        fun onPlayerQuit(event: PlayerQuitEvent) {
            if (event.player === player) destroy()
        }
    }

    fun setSlot(slot: AnvilSlot, item: ItemStack) {
        items[slot] = item
    }

    fun open() {
        val entity = (player as CraftPlayer).handle

        val container = AnvilContainer(entity)

        //Set the items to the items from the inventory given
        inventory = container.bukkitView.topInventory

        for (slot in items.keys) inventory?.setItem(slot.slot, items[slot])

        //Counter stuff that the game uses to keep track of inventories
        val c = entity.nextContainerCounter()

        //Send the packet
        val packetPlayOutOpenWindow = PacketPlayOutOpenWindow(c, "minecraft:anvil", ChatMessage("Repairing"), 0)
        entity.playerConnection.sendPacket(packetPlayOutOpenWindow)

        //Set their active container to the container
        entity.activeContainer = container

        //Set their active container window id to that counter stuff
        entity.activeContainer.windowId = c

        //Add the slot listener
        entity.activeContainer.addSlotListener(entity)
    }

    fun destroy() {
        inventory = null
        listener.unregister()
    }

    private inner class AnvilContainer(entity: EntityHuman) :
        ContainerAnvil(entity.inventory, entity.world, BlockPosition(0, 0, 0), entity) {

        override fun a(entityhuman: EntityHuman): Boolean = true
    }

    enum class AnvilSlot(val slot: Int) {
        INPUT_LEFT(0),
        INPUT_RIGHT(1),
        OUTPUT(2);

        companion object {
            fun bySlot(slot: Int): AnvilSlot? = values().find { it.slot == slot }
        }
    }

    inner class AnvilClickEvent(val slot: AnvilSlot, val name: String?) {

        var willClose = true
        var willDestroy = true

    }

}
