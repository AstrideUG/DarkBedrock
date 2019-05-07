package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import com.mojang.authlib.GameProfile
import net.darkdevelopers.darkbedrock.darkness.general.utils.setValue
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players
import net.minecraft.server.v1_8_R3.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.IOException
import java.util.*

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 04:55.
 * Current Version: 1.0 (22.12.2018 - 07.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 04:56.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
fun String.toPlayer(): Player? = try {
    Bukkit.getPlayer(UUID.fromString(this))
} catch (ex: IllegalArgumentException) {
    Bukkit.getPlayer(this)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 01:40.
 * Current Version: 1.0 (21.03.2019 - 06.05.2019)
 */
fun Player.sendPacket(packet: Packet<*>) {
//    try {
//        val entityPlayer = this.javaClass.getMethod("getHandle").invoke(this)
//        val get = entityPlayer.javaClass.getField("playerConnection").get(entityPlayer)
//        get.javaClass.getMethod("sendPacket").invoke(packet)
//    } catch (ex: Exception) {
//        ex.printStackTrace()
//    }
    (this as CraftPlayer).handle.playerConnection.sendPacket(packet)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 21:02.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
fun Packet<*>.sendToPlayers(): Unit = players.forEach { it.sendPacket(this) }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 01:40.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendActionBar(message: String): Unit =
    sendPacket(PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"), 2.toByte()))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.04.2019 18:37.
 * Current Version: 1.0 (01.04.2019 - 05.04.2019)
 */
fun Player.sendHeaderAndFooter(header: String, footer: String) {
    val packet = PacketPlayOutPlayerListHeaderFooter(IChatBaseComponent.ChatSerializer.a("""{"translate":"$header"}"""))
    packet.setValue("b", IChatBaseComponent.ChatSerializer.a("""{"translate":"$footer"}"""))
    sendPacket(packet)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 01:44.
 * Current Version: 1.0 (21.03.2019 - 06.05.2019)
 */
fun sendAllParticle(particleType: EnumParticle, loc: Location, speed: Float, amount: Int): Unit =
    players.forEach { it.sendParticle(particleType, loc, speed, amount) }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 01:44.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendParticle(particleType: EnumParticle, loc: Location, speed: Float, amount: Int): Unit = player.sendPacket(
    PacketPlayOutWorldParticles(
        particleType,
        true,
        loc.x.toFloat(),
        loc.y.toFloat(),
        loc.z.toFloat(),
        0F,
        0F,
        0F,
        speed,
        amount,
        0
    )
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 21:56.
 *
 * run it async
 * @throws IOException
 *
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
fun Player.changeGameProfile(profile: GameProfile, plugin: Plugin) {

    val oldExp = exp
    val oldHealth = health
    val oldFoodLevel = foodLevel
    val oldLocation = location

    val properties = profile.properties["textures"]
    val craftPlayer = this as? CraftPlayer ?: return
    craftPlayer.profile.properties.removeAll("textures")
    craftPlayer.profile.properties.putAll("textures", properties)

    PacketPlayOutEntityDestroy(entityId).sendToPlayers()
    PacketPlayOutPlayerInfo(
        PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
        craftPlayer.handle
    ).sendToPlayers()

    plugin.schedule {
        craftPlayer.handle.health = 0f
        spigot().respawn()
    }

    plugin.schedule(delay = 1) {
        PacketPlayOutNamedEntitySpawn(craftPlayer.handle).sendToPlayers()
        PacketPlayOutPlayerInfo(
            PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
            craftPlayer.handle
        ).sendToPlayers()

        exp = oldExp
        health = oldHealth
        foodLevel = oldFoodLevel
        teleport(oldLocation)
    }


}
