package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.minecraft.server.v1_8_R3.Packet
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 10:24.
 * Last edit 13.01.2019
 */
//TODO: WorldBoarder Packets
object Utils {

    val players: Collection<Player> get() = Bukkit.getOnlinePlayers()

    inline fun goThroughAllPlayers(lambda: (Player) -> Unit) = players.forEach { lambda(it) }

    @Suppress("MemberVisibilityCanBePrivate")
    @Deprecated(
        "",
        ReplaceWith("player.sendPacket(packet)", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendPacket")
    )
    fun sendPacket(player: Player?, packet: Packet<*>) =
        (player as CraftPlayer).handle.playerConnection.sendPacket(packet)


    private fun broadcast(message: String) {
        Bukkit.getConsoleSender().sendMessage(message)
        Utils.goThroughAllPlayers { it.sendMessage(message) }
    }

    fun getTime(inputTime: Long): String {
        val time = Math.abs(inputTime)
        val sec = time % 60
        val min = time / 60 % 60
        val hour = time / 3600 % 24
        val day = time / 86400
        var remainingTime = ""
        if (day == 1L) remainingTime += "${IMPORTANT}ein$TEXT Tag "
        else if (day != 0L) remainingTime += "$IMPORTANT$day$TEXT Tage "
        if (hour == 1L) remainingTime += "${IMPORTANT}eine$TEXT Stunde "
        else if (hour != 0L) remainingTime += "$IMPORTANT$hour$TEXT Stunden "
        if (min == 1L) remainingTime += "${IMPORTANT}eine$TEXT Minute "
        else if (min != 0L) remainingTime += "$IMPORTANT$min$TEXT Minuten "
        if (sec == 1L) remainingTime += "$IMPORTANT$sec$TEXT Sekunde "
        else if (time != 0L) remainingTime += "$IMPORTANT$sec$TEXT Sekunden "
        return if (remainingTime.isBlank()) "${IMPORTANT}0 ${TEXT}Sekunden " else remainingTime.dropLast(1)
    }

}