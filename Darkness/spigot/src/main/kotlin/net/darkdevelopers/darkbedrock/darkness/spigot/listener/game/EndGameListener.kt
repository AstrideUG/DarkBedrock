/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Items
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.block.*
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.*
import org.bukkit.event.weather.WeatherChangeEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by LartyHD on 29.11.2017  14:06.
 * Last edit 27.03.2019
 */
open class EndGameListener(javaPlugin: JavaPlugin, private val endGameLocation: Location) : Listener(javaPlugin) {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde betreten"
        event.player.teleport(this.endGameLocation)
    }

    @EventHandler
    fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        if (event.player.location.blockY < 0) event.player.teleport(endGameLocation)
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    fun onAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        event.respawnLocation = this.endGameLocation
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val action = event.action ?: return
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
            if (event.item == Items.LEAVE.itemStack) event.player.kickPlayer("LEAVE")
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) = event.cancel()

    @EventHandler
    fun onPlayerPickupItem(event: PlayerPickupItemEvent) = event.cancel()

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) = event.cancel()

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) = event.cancel()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) = event.cancel()

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) = event.cancel()

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) = event.cancel()

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) = event.cancel()

    @EventHandler
    fun onBlockBurn(event: BlockBurnEvent) = event.cancel()

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) = event.cancel()

    @EventHandler
    fun onBlockForm(event: BlockFormEvent) = event.cancel()

    @EventHandler
    fun onBlockFromTo(event: BlockFromToEvent) = event.cancel()

    @EventHandler
    fun onBlockGrow(event: BlockGrowEvent) = event.cancel()

    @EventHandler
    fun onBlockPhysics(event: BlockPhysicsEvent) = event.cancel()

    @EventHandler
    fun onWeatherChange(event: WeatherChangeEvent) = event.cancel()
}
