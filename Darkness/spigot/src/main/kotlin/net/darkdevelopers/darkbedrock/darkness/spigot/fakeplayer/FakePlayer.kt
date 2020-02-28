/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.fakeplayer

import com.mojang.authlib.GameProfile
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils.setValue
import net.darkdevelopers.darkbedrock.darkness.general.utils.getValue
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendPacket
import net.minecraft.server.v1_8_R3.*
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage
import org.bukkit.entity.Player
import java.util.*
import kotlin.math.ceil

@Suppress("unused")
class FakePlayer(name: String, private val location: Location) {
    private val entityID: Int = ceil(Math.random() * 1000).toInt() + 2000
    private val gameProfile: GameProfile = GameProfile(UUID.randomUUID(), name)

    fun spawnPacket(player: Player): Unit = player.sendPacket(spawnPacket())

    fun spawnAndAddToTabList(player: Player) {
        val packet = spawnPacket()
        addToTabList(player)
        player.sendPacket(packet)
    }

    fun destroyAndRemoveFromTabList(player: Player) {
        val packet = PacketPlayOutEntityDestroy(this.entityID)
        removeFromTabList(player)
        player.sendPacket(packet)
    }

    fun destroy(player: Player): Unit =
        player.sendPacket(PacketPlayOutEntityDestroy(this.entityID))

    private fun addToTabList(player: Player) =
        sendTabList(player, PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER)

    private fun removeFromTabList(player: Player) =
        sendTabList(player, PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER)

    private fun spawnPacket(): PacketPlayOutNamedEntitySpawn {
        val packet = PacketPlayOutNamedEntitySpawn()
        setValue(packet, "a", this.entityID)
        setValue(packet, "b", this.gameProfile.id)
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
        return packet
    }

    private fun sendTabList(player: Player, infoAction: PacketPlayOutPlayerInfo.EnumPlayerInfoAction) {
        val packet = PacketPlayOutPlayerInfo()
        val data = packet.PlayerInfoData(
            this.gameProfile,
            1,
            EnumGamemode.NOT_SET,
            CraftChatMessage.fromString(this.gameProfile.name)[0]
        )
        @Suppress("UNCHECKED_CAST")
        val players =
            packet.getValue("b") as? MutableList<PacketPlayOutPlayerInfo.PlayerInfoData> ?: return
        players.add(data)
        setValue(packet, "a", infoAction)
        setValue(packet, "b", players)
        player.sendPacket(packet)
    }

}
