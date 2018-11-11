/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 11:28.
 * Last edit 24.08.2018
 */

fun Player.sendTitle(title: String): Player {
	TitleUtils.sendTitle(this, title)
	return this
}

fun Player.sendSubTitle(subTitle: String): Player {
	TitleUtils.sendTitle(this, subTitle)
	return this
}

fun Player.sendTimings(fadeIn: Int, stay: Int, fadeOut: Int): Player {
	TitleUtils.sendTimings(this, fadeIn, stay, fadeOut)
	return this
}

class TitleUtils(private val player: Player) {

	companion object {
		fun sendAllTitle(title: String): Companion {
			Utils.goThroughAllPlayers { sendTitle(it, title) }
			return this
		}

		fun sendAllSubTitle(subtitle: String): Companion {
			Utils.goThroughAllPlayers { sendSubTitle(it, subtitle) }
			return this
		}

		fun sendAllTimings(fadeIn: Int, stay: Int, fadeOut: Int): Companion {
			Utils.goThroughAllPlayers { sendTimings(it, fadeIn, stay, fadeOut) }
			return this
		}

		internal fun sendTitle(player: Player, title: String): Companion {
			Utils.sendPacket(player, PacketPlayOutTitle(
					PacketPlayOutTitle.EnumTitleAction.TITLE,
					IChatBaseComponent.ChatSerializer.a("{\"text\": \"$title\"}")
			))
			return this
		}

		internal fun sendSubTitle(player: Player, subtitle: String): Companion {
			Utils.sendPacket(player, PacketPlayOutTitle(
					PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
					IChatBaseComponent.ChatSerializer.a("{\"text\": \"$subtitle\"}")
			))
			return this
		}

		internal fun sendTimings(player: Player, fadeIn: Int, stay: Int, fadeOut: Int): Companion {
			Utils.sendPacket(player, PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut))
			return this
		}
	}

	@Deprecated("Use Player.sendTitle()")
	fun sendTitle(title: String): TitleUtils {
		sendTitle(player, title)
		return this
	}

	@Deprecated("Use Player.sendSubTitle()")
	fun sendSubTitle(subtitle: String): TitleUtils {
		sendSubTitle(player, subtitle)
		return this
	}

	@Deprecated("Use Player.sendTimings()")
	fun sendTimings(fadeIn: Int, stay: Int, fadeOut: Int): TitleUtils {
		sendTimings(player, fadeIn, stay, fadeOut)
		return this
	}

}