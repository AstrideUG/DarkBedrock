package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import com.mojang.authlib.GameProfile
import net.darkdevelopers.darkbedrock.darkness.general.utils.setValue
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
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
 * Current Version: 1.0 (22.12.2018 - 08.05.2019)
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
 * Current Version: 1.0 (07.05.2019 - 08.05.2019)
 */
fun Packet<*>.sendToPlayers(players: Collection<Player> = Utils.players): Unit = players.forEach { it.sendPacket(this) }

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
 * Current Version: 1.0 (21.03.2019 - 08.05.2019)
 */
fun Player.sendParticle(
    particleType: EnumParticle,
    location: Location,
    speed: Float,
    amount: Int
): Unit = sendPacket(
    PacketPlayOutWorldParticles(
        particleType,
        true,
        location.x.toFloat(),
        location.y.toFloat(),
        location.z.toFloat(),
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
 * Created by Lars Artmann | LartyHD on 08.05.2019 14:55.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun Player.removeEntity(entityID: Int): Unit = sendPacket(PacketPlayOutEntityDestroy(entityID))

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 14:56.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun Int.removeEntityToPlayers(players: Collection<Player> = Utils.players): Unit =
    PacketPlayOutEntityDestroy(this).sendToPlayers(players)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:14.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun sendTabListInfoToPlayers(
    action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction,
    player: Player,
    players: Collection<Player> = Utils.players
) {
    if (player is CraftPlayer) sendTabListInfoToPlayers(action, player, players)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:14.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun sendTabListInfoToPlayers(
    action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction,
    player: CraftPlayer,
    players: Collection<Player> = Utils.players
): Unit = sendTabListInfoToPlayers(action, player.handle, players)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:11.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun sendTabListInfoToPlayers(
    action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction,
    entityPlayer: EntityPlayer,
    players: Collection<Player> = Utils.players
): Unit = (action toTabListInfoPacket entityPlayer).sendToPlayers(players)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:04.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun Player.sendTabListInfo(action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction, player: Player) {
    if (player is CraftPlayer) sendTabListInfo(action, player)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:07.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun Player.sendTabListInfo(action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction, player: CraftPlayer): Unit =
    sendTabListInfo(action, player.handle)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:06.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
fun Player.sendTabListInfo(action: PacketPlayOutPlayerInfo.EnumPlayerInfoAction, entityPlayer: EntityPlayer): Unit =
    sendPacket(action toTabListInfoPacket entityPlayer)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.05.2019 15:08.
 * Current Version: 1.0 (08.05.2019 - 08.05.2019)
 */
private infix fun PacketPlayOutPlayerInfo.EnumPlayerInfoAction.toTabListInfoPacket(entityPlayer: EntityPlayer): PacketPlayOutPlayerInfo =
    PacketPlayOutPlayerInfo(this, entityPlayer)


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 21:56.
 *
 * run it async
 * @throws IOException
 *
 * Current Version: 1.0 (07.05.2019 - 08.05.2019)
 */
fun Player.changeGameProfile(profile: GameProfile, plugin: Plugin, delay: Long = 1) {

    val properties = profile.properties["textures"]
    val craftPlayer = this as? CraftPlayer ?: return
    craftPlayer.profile.properties.removeAll("textures")
    craftPlayer.profile.properties.putAll("textures", properties)

    entityId.removeEntityToPlayers()
    sendTabListInfoToPlayers(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, craftPlayer)

    plugin.schedule(delay = delay) {
        craftPlayer.handle.health = 0f
        spigot().respawn()
    }

    plugin.schedule(delay = delay + 1) {
        PacketPlayOutNamedEntitySpawn(craftPlayer.handle).sendToPlayers()
        sendTabListInfoToPlayers(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, craftPlayer)
    }


}
