package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.minecraft.server.v1_8_R3.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import java.awt.SystemColor.text
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 10:24.
 * Last edit 06.07.2018
 */
//TODO: WorldBoarder Packets
object Utils {
    @Suppress("MemberVisibilityCanBePrivate")
//    val players = Bukkit.getOnlinePlayers()
    fun getPlayers(): MutableCollection<out Player> = Bukkit.getOnlinePlayers()

    fun goThroughAllPlayers(lambda: (Player) -> Unit) = getPlayers().forEach { lambda(it) }

    fun sendTitle(player: Player, title: String): Utils {
        sendPacket(player, PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\": \"$title\"}")
        ))
        return this
    }

    fun sendSubTitle(player: Player, subtitle: String): Utils {
        sendPacket(player, PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\": \"$subtitle\"}")
        ))
        return this
    }

    fun sendTimings(player: Player, fadeIn: Int, stay: Int, fadeOut: Int): Utils {
        sendPacket(player, PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut))
        return this
    }

    fun sendPlayerParticle(player: Player, particleType: EnumParticle, loc: Location, speed: Float, amount: Int) =
            sendPacket(player, PacketPlayOutWorldParticles(particleType, true, loc.x.toFloat(), loc.y.toFloat(), loc.z.toFloat(), 0F, 0F, 0F, speed, amount, 0))

    @Suppress("MemberVisibilityCanBePrivate")
    fun sendPacket(player: Player, packet: Packet<*>) =
            (player as CraftPlayer).handle.playerConnection.sendPacket(packet)


    fun randomLook(location: Location): Location {
        val random = Random()
        var yaw = 0
        when (random.nextInt(3)) {
            0 -> yaw = 0
            1 -> yaw = 90
            2 -> yaw = 180
            3 -> yaw = -90
        }
        return Location(location.world, location.x, location.y, location.z, yaw.toFloat(), location.pitch)
    }


    fun sendActionBar(player: Player) =
            sendPacket(player, PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$text\"}"), 2.toByte()))


    fun getTime(time: Long): String {
        var remainingTime = ""
        var seconds = time
        var minutes = 0L
        var hours = 0L
        var days = 0L
        var years = 0L
        while (time >= 60) {
            seconds -= 60
            minutes++
        }
        while (minutes >= 60) {
            minutes -= 60
            hours++
        }
        while (hours >= 24) {
            hours -= 24
            days++
        }
        while (days >= 365) {
            days -= 365
            years++
        }
        if (years == 1L) remainingTime = "$remainingTime${IMPORTANT}ein$TEXT Jahr " else if (years != 0L) remainingTime = "$remainingTime$IMPORTANT$years$TEXT Jahre "
        if (days == 1L) remainingTime = "$remainingTime${IMPORTANT}ein$TEXT Tag " else if (days != 0L) remainingTime = "$remainingTime$IMPORTANT$days$TEXT Tage "
        if (hours == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Stunde " else if (hours != 0L) remainingTime = "$remainingTime$IMPORTANT$hours$TEXT Stunden "
        if (minutes == 1L) remainingTime = "$remainingTime${IMPORTANT}eine$TEXT Minute " else if (minutes != 0L) remainingTime = "$remainingTime$IMPORTANT$minutes$TEXT Minuten "
        if (seconds == 1L) remainingTime = "$remainingTime$IMPORTANT$seconds$TEXT Sekunde " else if (time != 0L) remainingTime = "$remainingTime$IMPORTANT$seconds$TEXT Sekunden "
        return if (remainingTime.isEmpty()) "${IMPORTANT}0 ${TEXT}Sekunden " else remainingTime.substring(0, remainingTime.length - 1)
    }
}