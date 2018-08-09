package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.minecraft.server.v1_8_R3.EntityLiving
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import kotlin.concurrent.thread


/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 09.07.2018 10:45.
 * Last edit 09.07.2018
 */
class Holograms(private val lines: Array<String>, private val location: Location) {
    private val armorStands = mutableSetOf<ArmorStand>()

    init {
        create()
    }

    fun show(time: Long) = Utils.goThroughAllPlayers { show(it, time) }

    fun show(player: Player, time: Long) {
        show(player)
        thread {
            Thread.sleep(time)
            hide(player)
        }
    }

    fun show() = Utils.goThroughAllPlayers { show(it) }

    fun hide() = Utils.goThroughAllPlayers { hide(it) }

    fun show(player: Player) = armorStands.forEach { Utils.sendPacket(player, PacketPlayOutSpawnEntityLiving(it as EntityLiving)) }

    fun hide(player: Player) = armorStands.forEach { Utils.sendPacket(player, PacketPlayOutEntityDestroy(it.entityId)) }

    private fun create() = lines.forEach {
        armorStands.add((location.world.spawnEntity(location, EntityType.ARMOR_STAND) as? ArmorStand
                ?: throw  NullPointerException("armorStand can not be null")).apply {
            customName = it
            isCustomNameVisible = true
            isVisible = false
            setGravity(false)
        })
        location.add(0.0, DISTANCE, 0.0)
    }

    companion object {
        private const val DISTANCE = 0.25
    }
}