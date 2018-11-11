package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 10:24.
 * Last edit 20.08.2018
 */
//TODO: WorldBoarder Packets
object Utils {
	@Suppress("MemberVisibilityCanBePrivate")
	fun getPlayers(): MutableCollection<out Player> = Bukkit.getOnlinePlayers()

	inline fun goThroughAllPlayers(lambda: (Player) -> Unit) = getPlayers().forEach { lambda(it) }

	@Deprecated("", ReplaceWith("TitleUtils(player)", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.TitleUtils"), DeprecationLevel.ERROR)
	fun getTitleBuilder(player: Player) = TitleUtils(player)

	@Deprecated("", ReplaceWith("TitleUtils(player.sendTitle(title)", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils", "org.bukkit.entity.Player", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.TitleUtils"), DeprecationLevel.ERROR)
	fun sendTitle(player: Player?, title: String): Utils {
		sendPacket(player, PacketPlayOutTitle(
				PacketPlayOutTitle.EnumTitleAction.TITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"$title\"}")
		))
		return this
	}

	@Deprecated("", ReplaceWith("TitleUtils(player).sendSubTitle(title)", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils", "org.bukkit.entity.Player", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.TitleUtils"), DeprecationLevel.ERROR)
	fun sendSubTitle(player: Player, subtitle: String): Utils {
		sendPacket(player, PacketPlayOutTitle(
				PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"$subtitle\"}")
		))
		return this
	}


	@Deprecated("", ReplaceWith("Utils.getTitleBuilder(player).sendTimings(title)", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils", "org.bukkit.entity.Player", "net.darkdevelopers.darkbedrock.darkness.spigot.utils.TitleUtils"), DeprecationLevel.ERROR)
	fun sendTimings(player: Player, fadeIn: Int, stay: Int, fadeOut: Int): Utils {
		sendPacket(player, PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut))
		return this
	}

	fun sendAllPlayerParticle(player: Player, particleType: EnumParticle, loc: Location, speed: Float, amount: Int) = goThroughAllPlayers { sendPlayerParticle(player, particleType, loc, speed, amount) }

	@Suppress("MemberVisibilityCanBePrivate")
	fun sendPlayerParticle(player: Player, particleType: EnumParticle, loc: Location, speed: Float, amount: Int) =
			sendPacket(player, PacketPlayOutWorldParticles(particleType, true, loc.x.toFloat(), loc.y.toFloat(), loc.z.toFloat(), 0F, 0F, 0F, speed, amount, 0))

	@Suppress("MemberVisibilityCanBePrivate")
	fun sendPacket(player: Player?, packet: Packet<*>) =
			(player as CraftPlayer).handle.playerConnection.sendPacket(packet)


	fun Location.randomLook(): Location {
		val random = Random()
		var yaw = 0
		when (random.nextInt(3)) {
			0 -> yaw = 0
			1 -> yaw = 90
			2 -> yaw = 180
			3 -> yaw = -90
		}
		return Location(world, x, y, z, yaw.toFloat(), pitch)
	}

	private fun broadcast(message: String) {
		Bukkit.getConsoleSender().sendMessage(message)
		Utils.goThroughAllPlayers { it.sendMessage(message) }
	}

//    fun sendActionBar(player: Player, message: String) =
//            sendPacket(player, PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"), 2.toByte()))

	fun Player.sendActionBar(message: String) =
			sendPacket(this, PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"), 2.toByte()))


	fun getTime(inputTime: Long): String {
		val time = Math.abs(inputTime)
		val sec = time % 60
		val min = time / 60 % 60
		val hour = time / 3600 % 24
		val day = time / 86400
		var remainingTime = ""
		if (day == 1L) remainingTime = "$remainingTime${IMPORTANT}ein$TEXT Tag " else if (day != 0L) remainingTime = "$remainingTime$IMPORTANT$day$TEXT Tage "
		if (hour == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Stunde " else if (hour != 0L) remainingTime = "$remainingTime$IMPORTANT$hour$TEXT Stunden "
		if (min == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Minute " else if (min != 0L) remainingTime = "$remainingTime$IMPORTANT$min$TEXT Minuten "
		if (sec == 1L) remainingTime = "$remainingTime$IMPORTANT$sec$TEXT Sekunde " else if (time != 0L) remainingTime = "$remainingTime$IMPORTANT$sec$TEXT Sekunden "
		return if (remainingTime.isBlank()) "${IMPORTANT}0 ${TEXT}Sekunden " else remainingTime.substring(0, remainingTime.length - 1)
	}
}