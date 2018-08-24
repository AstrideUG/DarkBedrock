package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
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