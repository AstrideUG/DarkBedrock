/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.fakeplayer

import com.mojang.authlib.GameProfile
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils.getValueAs
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils.setValue
import net.minecraft.server.v1_8_R3.*
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage
import org.bukkit.entity.Player
import java.util.*

/**
 * Created by LartyHD on 17.01.2018  00:30.
 * Last edit 13.03.2018
 */
internal class FakePlayer(name: String, private val location: Location) {
    private val entityID: Int = Math.ceil(Math.random() * 1000).toInt() + 2000
    private val gameprofile: GameProfile = GameProfile(UUID.randomUUID(), name)

    fun spawn(player: Player) {
        val packet = PacketPlayOutNamedEntitySpawn()
        setValue(packet, "a", this.entityID)
        setValue(packet, "b", this.gameprofile.id)
        setValue(packet, "c", MathHelper.floor(this.location.x * 32.0))
        setValue(packet, "d", MathHelper.floor(this.location.y * 32.0))
        setValue(packet, "e", MathHelper.floor(this.location.z * 32.0))
        setValue(packet, "f", (this.location.yaw * 256.0f / 360.0f).toInt().toByte())
        setValue(packet, "g", (this.location.pitch * 256.0f / 360.0f).toInt().toByte())
        setValue(packet, "getMap", 0)
        val dataWatcher = DataWatcher(null)
        dataWatcher.a(6, 20.toFloat())
        dataWatcher.a(10, 127.toByte())
        setValue(packet, "userdata", dataWatcher)
        sendPacket(player, packet)
    }

    fun spawnAndAddToTabList(player: Player) {
        val packet = PacketPlayOutNamedEntitySpawn()
        setValue(packet, "a", this.entityID)
        setValue(packet, "b", this.gameprofile.id)
        setValue(packet, "c", MathHelper.floor(this.location.x * 32.0))
        setValue(packet, "d", MathHelper.floor(this.location.y * 32.0))
        setValue(packet, "e", MathHelper.floor(this.location.z * 32.0))
        setValue(packet, "f", (this.location.yaw * 256.0f / 360.0f).toInt().toByte())
        setValue(packet, "g", (this.location.pitch * 256.0f / 360.0f).toInt().toByte())
        setValue(packet, "getMap", 0)
        val dataWatcher = DataWatcher(null)
        dataWatcher.a(6, 20.toFloat())
        dataWatcher.a(10, 127.toByte())
        setValue(packet, "userdata", dataWatcher)
        addToTabList(player)
        sendPacket(player, packet)
    }

    fun destroyAndRemoveFromTabList(player: Player) {
        val packet = PacketPlayOutEntityDestroy(this.entityID)
        removeFromTabList(player)
        sendPacket(player, packet)
    }

    fun destroy(player: Player) {
        val packet = PacketPlayOutEntityDestroy(this.entityID)
        sendPacket(player, packet)
    }

    private fun addToTabList(player: Player) {
        val packet = PacketPlayOutPlayerInfo()
        val data = packet.PlayerInfoData(this.gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(this.gameprofile.name)[0])
        val players = getValueAs<MutableList<PacketPlayOutPlayerInfo.PlayerInfoData>>(packet, "b") ?: return

        players.add(data)
        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER)
        setValue(packet, "b", players)
        sendPacket(player, packet)
    }

    private fun removeFromTabList(player: Player) {
        val packet = PacketPlayOutPlayerInfo()
        val data = packet.PlayerInfoData(this.gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString(this.gameprofile.name)[0])
        val players = getValueAs<MutableList<PacketPlayOutPlayerInfo.PlayerInfoData>>(packet, "b") ?: return

        players.add(data)
        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER)
        setValue(packet, "b", players)
        sendPacket(player, packet)
    }

    private fun sendPacket(player: Player, packet: Packet<*>) {
        (player as CraftPlayer).handle.playerConnection.sendPacket(packet)
    }
}
