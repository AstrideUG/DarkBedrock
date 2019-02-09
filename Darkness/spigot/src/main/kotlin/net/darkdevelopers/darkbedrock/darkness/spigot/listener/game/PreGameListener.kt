/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
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
class PreGameListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        event.joinMessage = null
    }

    @EventHandler
    fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
        //TODO: ADD TEAM INFOS
    }

    @EventHandler
    fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        val from = event.from
        val to = event.to
        if (to.blockX != from.blockX || from.blockZ != to.blockZ)
            event.player.teleport(Location(from.world, from.x, from.y, from.z, to.yaw, to.pitch))
    }

    @EventHandler
    fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    fun onPlayerDropItemEvent(event: PlayerDropItemEvent) = cancel(event)

    @EventHandler
    fun onPlayerPickupItemEvent(event: PlayerPickupItemEvent) = cancel(event)

    @EventHandler
    fun onFoodLevelChangeEvent(event: FoodLevelChangeEvent) = cancel(event)

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) = cancel(event)

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) = cancel(event)

    @EventHandler
    fun onEntityDamageEvent(event: EntityDamageEvent) = cancel(event)

    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) = cancel(event)

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) = cancel(event)

    @EventHandler
    fun onBlockBurnEvent(event: BlockBurnEvent) = cancel(event)

    @EventHandler
    fun onBlockExplodeEvent(event: BlockExplodeEvent) = cancel(event)

    @EventHandler
    fun onBlockFormEvent(event: BlockFormEvent) = cancel(event)

    @EventHandler
    fun onBlockFromToEvent(event: BlockFromToEvent) = cancel(event)

    @EventHandler
    fun onBlockGrowEvent(event: BlockGrowEvent) = cancel(event)

    @EventHandler
    fun onBlockPhysicsEvent(event: BlockPhysicsEvent) = cancel(event)

    @EventHandler
    fun onWeatherChangeEvent(event: WeatherChangeEvent) = cancel(event)
}
