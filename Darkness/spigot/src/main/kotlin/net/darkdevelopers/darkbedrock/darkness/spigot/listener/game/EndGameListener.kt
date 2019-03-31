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
    open fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde betreten"
        event.player.teleport(this.endGameLocation)
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
    }

    @EventHandler
    open fun onPlayerMove(event: PlayerMoveEvent) {
        if (event.player.location.blockY < 0) event.player.teleport(endGameLocation)
    }

    @EventHandler
    open fun onPlayerDeath(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    open fun onPlayerRespawn(event: PlayerRespawnEvent) {
        event.respawnLocation = this.endGameLocation
    }

    @EventHandler
    open fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val action = event.action ?: return
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
            if (event.item == Items.LEAVE.itemStack) event.player.kickPlayer("LEAVE")
    }

    @EventHandler
    open fun onPlayerDropItem(event: PlayerDropItemEvent) = event.cancel()

    @EventHandler
    open fun onPlayerPickupItem(event: PlayerPickupItemEvent) = event.cancel()

    @EventHandler
    open fun onFoodLevelChange(event: FoodLevelChangeEvent) = event.cancel()

    @EventHandler
    open fun onInventoryClick(event: InventoryClickEvent) = event.cancel()

    @EventHandler
    open fun onPlayerInteract(event: PlayerInteractEvent) = event.cancel()

    @EventHandler
    open fun onEntityDamage(event: EntityDamageEvent) = event.cancel()

    @EventHandler
    open fun onBlockBreak(event: BlockBreakEvent) = event.cancel()

    @EventHandler
    open fun onBlockPlace(event: BlockPlaceEvent) = event.cancel()

    @EventHandler
    open fun onBlockBurn(event: BlockBurnEvent) = event.cancel()

    @EventHandler
    open fun onBlockExplode(event: BlockExplodeEvent) = event.cancel()

    @EventHandler
    open fun onBlockForm(event: BlockFormEvent) = event.cancel()

    @EventHandler
    open fun onBlockFromTo(event: BlockFromToEvent) = event.cancel()

    @EventHandler
    open fun onBlockGrow(event: BlockGrowEvent) = event.cancel()

    @EventHandler
    open fun onBlockPhysics(event: BlockPhysicsEvent) = event.cancel()

    @EventHandler
    open fun onWeatherChange(event: WeatherChangeEvent) = event.cancel()
}
