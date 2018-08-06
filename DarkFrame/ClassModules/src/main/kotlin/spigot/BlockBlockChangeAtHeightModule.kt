/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.command.CommandSender
import org.bukkit.event.Cancellable
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 08:47.
 * Last edit 03.08.2018
 */
class BlockBlockChangeAtHeightModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("BlockBlockChangeAtHeightModule", "1.0", "Lars Artmann | LartyHD", "")
    private val config = GsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load()
    private val height = config.getAs<JsonPrimitive>("height")?.asInt
            ?: throw NullPointerException("height can not be null")
    private val blockMessage = config.getAs<JsonPrimitive>("blockMessage")?.asString
            ?: throw NullPointerException("blockMessage can not be null")


    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) = block(event.block.location.blockY, event, event.player)

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) = block(event.block.location.blockY, event, event.player)

    @EventHandler
    fun onPlayerBucketEvent(event: PlayerBucketEmptyEvent) = block(event.blockClicked.location.blockY, event, event.player)

    @EventHandler
    fun onPlayerBucketEvent(event: PlayerBucketFillEvent) = block(event.blockClicked.location.blockY, event, event.player)

    @EventHandler
    fun onEntitySpawnEvent(event: EntitySpawnEvent) {
        if (event.location.blockY >= height) cancel(event)
    }

    private fun block(heightValue: Int, cancellable: Cancellable, sender: CommandSender) {
        if (heightValue >= height) {
            sender.sendMessage(blockMessage)
            cancel(cancellable)
        }
    }
}