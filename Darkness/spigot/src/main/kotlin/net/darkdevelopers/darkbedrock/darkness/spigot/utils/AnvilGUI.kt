package net.darkdevelopers.darkbedrock.darkness.spigot.utils

/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.cancel
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.listen
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.unregister
import net.minecraft.server.v1_8_R3.*
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.04.2019 15:32.
 * Current Version: 1.0 (19.04.2019 - 02.05.2019)
 */
class AnvilGUI(
    javaPlugin: JavaPlugin,
    val player: Player,
    private val block: (AnvilClickEvent) -> Unit
) {

    private val items: MutableMap<AnvilSlot, ItemStack> = mutableMapOf()
    private var inventory: Inventory? = null
    private val listeners = arrayOf(
        listen<InventoryClickEvent>(javaPlugin) { event ->
            if (event.whoClicked !== player) return@listen
            if (event.inventory != inventory) return@listen
            event.cancel()

            val slot = AnvilSlot.bySlot(event.rawSlot) ?: return@listen
            val clickEvent = AnvilClickEvent(slot, event.currentItem)
            block(clickEvent)

            if (clickEvent.willClose) event.whoClicked.closeInventory()
            if (clickEvent.willDestroy) destroy()
        },
        listen<InventoryCloseEvent>(javaPlugin) { event ->
            if (event.player !== player) return@listen
            if (event.inventory != this@AnvilGUI.inventory) return@listen
            event.inventory.clear()
            destroy()
        },
        listen<PlayerQuitEvent>(javaPlugin) { event ->
            if (event.player === player) destroy()
        }
    )

    fun setSlot(slot: AnvilSlot, item: ItemStack) {
        items[slot] = item
    }

    fun open(title: String = "Repairing") {
        val entity = (player as CraftPlayer).handle

        val container = AnvilContainer(entity)

        //Set the items to the items from the inventory given
        inventory = container.bukkitView.topInventory

        items.forEach { (slot, item) -> inventory?.setItem(slot.slot, item) }

        //Counter stuff that the game uses to keep track of inventories
        val c = entity.nextContainerCounter()

        //Send the packet
        val packetPlayOutOpenWindow = PacketPlayOutOpenWindow(c, "minecraft:anvil", ChatMessage(title), 0)
        entity.playerConnection.sendPacket(packetPlayOutOpenWindow)

        //Set their active container to the container
        entity.activeContainer = container

        //Set their active container window id to that counter stuff
        entity.activeContainer.windowId = c

        //Add the slot listener
        entity.activeContainer.addSlotListener(entity)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun destroy() {
        inventory = null
        listeners.forEach { it.unregister() }
    }

    enum class AnvilSlot(val slot: Int) {

        INPUT_LEFT(0),
        INPUT_RIGHT(1),
        OUTPUT(2);

        companion object {

            fun bySlot(slot: Int): AnvilSlot? = values().find { it.slot == slot }
        }
    }

    inner class AnvilClickEvent(val slot: AnvilSlot, val itemStack: ItemStack?) {

        var willClose: Boolean = true

        var willDestroy: Boolean = true
    }

    private inner class AnvilContainer(entity: EntityHuman) : ContainerAnvil(
        entity.inventory,
        entity.world,
        BlockPosition(0, 0, 0),
        entity
    ) {
        override fun a(entityHuman: EntityHuman): Boolean = true
    }

}
