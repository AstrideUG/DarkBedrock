/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

import io.netty.buffer.Unpooled
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendPacket
import net.minecraft.server.v1_8_R3.PacketDataSerializer
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload
import net.minecraft.server.v1_8_R3.StatisticList
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 16:08.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
fun Player.openBook(book: ItemStack, addStats: Boolean = false) {
    val hand: ItemStack? = player.itemInHand
    try {
        player.itemInHand = book
        val packet = PacketPlayOutCustomPayload("MC|BOpen", PacketDataSerializer(Unpooled.buffer()))
        player.sendPacket(packet)
    } catch (ex: Exception) {
        ex.printStackTrace()
    } finally {
        player.itemInHand = hand
    }
    if (addStats) (player as CraftHumanEntity).handle.b(StatisticList.USE_ITEM_COUNT[387])
}