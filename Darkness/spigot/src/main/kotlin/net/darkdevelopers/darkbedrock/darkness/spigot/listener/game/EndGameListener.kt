/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
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
 * Last edit 06.07.2018
 */
class EndGameListener(javaPlugin: JavaPlugin, private val endGameLocation: Location) : Listener(javaPlugin) {

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
    fun onPlayerDropItem(event: PlayerDropItemEvent) = cancel(event)

    @EventHandler
    fun onPlayerPickupItem(event: PlayerPickupItemEvent) = cancel(event)

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) = cancel(event)

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) = cancel(event)

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) = cancel(event)

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) = cancel(event)

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) = cancel(event)

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) = cancel(event)

    @EventHandler
    fun onBlockBurn(event: BlockBurnEvent) = cancel(event)

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) = cancel(event)

    @EventHandler
    fun onBlockForm(event: BlockFormEvent) = cancel(event)

    @EventHandler
    fun onBlockFromTo(event: BlockFromToEvent) = cancel(event)

    @EventHandler
    fun onBlockGrow(event: BlockGrowEvent) = cancel(event)

    @EventHandler
    fun onBlockPhysics(event: BlockPhysicsEvent) = cancel(event)

    @EventHandler
    fun onWeatherChange(event: WeatherChangeEvent) = cancel(event)
}
