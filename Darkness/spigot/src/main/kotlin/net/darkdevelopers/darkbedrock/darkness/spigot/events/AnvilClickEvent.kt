/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events

import net.darkdevelopers.darkbedrock.darkness.spigot.utils.AnvilGUI
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 04:10.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
class AnvilClickEvent(val anvilGUI: AnvilGUI, val slot: AnvilGUI.AnvilSlot, val itemStack: ItemStack?) : Event() {

    var willClose: Boolean = true

    var willDestroy: Boolean = true

    override fun getHandlers(): HandlerList = handlerList

    companion object {
        @JvmStatic //Important for Bukkit due to the Java ByteCode
        val handlerList: HandlerList = HandlerList()
    }

}
