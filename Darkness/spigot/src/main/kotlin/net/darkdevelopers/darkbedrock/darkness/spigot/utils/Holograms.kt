/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendPacket
import net.minecraft.server.v1_8_R3.EntityArmorStand
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.bukkit.entity.Player
import kotlin.concurrent.thread
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers


@Suppress("MemberVisibilityCanBePrivate", "unused")
/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 09.07.2018 10:45.
 * Last edit 12.05.2019
 */
class Holograms(
    private val lines: Array<String>,
    private val location: Location,
    private val players: Collection<Player> = allPlayers
) {
    private val armorStands = mutableSetOf<EntityArmorStand>()

    init {
        lines.reverse()
        create()
    }

    fun show(time: Long): Unit = players.forEach { show(it, time) }

    fun show(player: Player, time: Long) {
        show(player)
        thread {
            Thread.sleep(time)
            hide(player)
        }
    }

    fun show(): Unit = players.forEach(::show)

    fun hide(): Unit = players.forEach(::hide)

    fun show(player: Player): Unit = armorStands.forEach { player.sendPacket(PacketPlayOutSpawnEntityLiving(it)) }

    fun hide(player: Player): Unit = armorStands.forEach { player.sendPacket(PacketPlayOutEntityDestroy(it.id)) }

    private fun create() {
        val clone = location.clone()
        val world = clone.world as? CraftWorld
        if (world == null) {
            System.err.println("[Holograms] World of location ($clone) is null")
            return
        }
        lines.forEach {
            if (it.isNotEmpty()) {
                val entity = EntityArmorStand(world.handle, clone.x, clone.y, clone.z).apply {
                    customName = it
                    customNameVisible = true
                    isInvisible = true
                    setGravity(false)
                }
                armorStands += entity
            }
            clone.add(0.0, DISTANCE, 0.0)
        }
    }

    companion object {
        private const val DISTANCE = 0.25
    }
}
