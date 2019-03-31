/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel
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
 * Last edit 27.03.2019
 */
open class PreGameListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    @EventHandler
    open fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        event.joinMessage = null
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
        //TODO: ADD TEAM INFOS
    }

    @EventHandler
    open fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        val from = event.from
        val to = event.to
        if (to.blockX != from.blockX || from.blockZ != to.blockZ)
            event.player.teleport(Location(from.world, from.x, from.y, from.z, to.yaw, to.pitch))
    }

    @EventHandler
    open fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    open fun onPlayerDropItemEvent(event: PlayerDropItemEvent) = event.cancel()

    @EventHandler
    open fun onPlayerPickupItemEvent(event: PlayerPickupItemEvent) = event.cancel()

    @EventHandler
    open fun onFoodLevelChangeEvent(event: FoodLevelChangeEvent) = event.cancel()

    @EventHandler
    open fun onInventoryClickEvent(event: InventoryClickEvent) = event.cancel()

    @EventHandler
    open fun onPlayerInteractEvent(event: PlayerInteractEvent) = event.cancel()

    @EventHandler
    open fun onEntityDamageEvent(event: EntityDamageEvent) = event.cancel()

    @EventHandler
    open fun onBlockBreakEvent(event: BlockBreakEvent) = event.cancel()

    @EventHandler
    open fun onBlockPlaceEvent(event: BlockPlaceEvent) = event.cancel()

    @EventHandler
    open fun onBlockBurnEvent(event: BlockBurnEvent) = event.cancel()

    @EventHandler
    open fun onBlockExplodeEvent(event: BlockExplodeEvent) = event.cancel()

    @EventHandler
    open fun onBlockFormEvent(event: BlockFormEvent) = event.cancel()

    @EventHandler
    open fun onBlockFromToEvent(event: BlockFromToEvent) = event.cancel()

    @EventHandler
    open fun onBlockGrowEvent(event: BlockGrowEvent) = event.cancel()

    @EventHandler
    open fun onBlockPhysicsEvent(event: BlockPhysicsEvent) = event.cancel()

    @EventHandler
    open fun onWeatherChangeEvent(event: WeatherChangeEvent) = event.cancel()
}
