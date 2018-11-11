package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.minecraft.server.v1_8_R3.EntityArmorStand
import net.minecraft.server.v1_8_R3.EntityLiving
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.bukkit.entity.Player
import kotlin.concurrent.thread


@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 09.07.2018 10:45.
 * Last edit 24.08.2018
 */
class Holograms(private val lines: Array<String>, private val location: Location) {
	private val armorStands = mutableSetOf<EntityArmorStand>()

	init {
		lines.reverse()
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

	fun hide(player: Player) = armorStands.forEach { Utils.sendPacket(player, PacketPlayOutEntityDestroy(it.id)) }

	private fun create() {
		val clone = location.clone()
		lines.forEach {
			if (it != "") armorStands.add(EntityArmorStand((clone.world as CraftWorld).handle, clone.x, clone.y, clone.z).apply {
				customName = it
				customNameVisible = true
				isInvisible = true
				setGravity(false)
			})
			clone.add(0.0, DISTANCE, 0.0)
		}
	}

	companion object {
		private const val DISTANCE = 0.25
	}
}
