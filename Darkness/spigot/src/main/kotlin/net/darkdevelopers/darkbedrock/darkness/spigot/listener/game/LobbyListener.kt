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
import org.bukkit.entity.Player
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
 * Last edit 15.08.2018
 */
abstract class LobbyListener protected constructor(javaPlugin: JavaPlugin, private val lobbyLocation: Location) :
    Listener(javaPlugin) {

    init {
        lobbyLocation.world.apply {
            setSpawnLocation(lobbyLocation.blockX, lobbyLocation.blockY, lobbyLocation.blockZ)
            time = 6000
            setGameRuleValue("spawnRadius", "0")
            setGameRuleValue("doDaylightCycle", "false")
            setGameRuleValue("doMobSpawning", "false")
            setGameRuleValue("doFireTick", "false")
            weatherDuration = -1
            isThundering = false
            setStorm(false)
            isAutoSave = false
//            difficulty = Difficulty.PEACEFUL
        }

    }

    @EventHandler
    open fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        event.joinMessage = "${Messages.PREFIX}$IMPORTANT${player.displayName}$TEXT hat die Runde betreten"
        player.teleport(lobbyLocation)
        setJoinItems(player)
    }

    @EventHandler
    open fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = "${Messages.PREFIX}$IMPORTANT${event.player.displayName}$TEXT hat die Runde verlassen"
    }

    @EventHandler
    open fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        if (event.player.location.blockY < 0) event.player.teleport(lobbyLocation)
    }

    @EventHandler
    open fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        event.deathMessage = null
        event.keepInventory = true
    }

    @EventHandler
    open fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val action = event.action ?: return
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            /*final Player player = event.getPlayer();
                    if (item.getType() == Material.ENDER_CHEST) {
                        player.updateInventory();
                        gameController.getLobbyCountdown().forEach(countdown -> {
                            if (countdown.getSeconds() > 10) {
                                player.sendMessage("${Messages.PREFIX} +TEXT + "Du kannst erst in den letzten " +IMPORTANT + "10 Sekunden" +TEXT + " dein " +IMPORTANT + "Team" +TEXT + " wählen");
                            }
                        });
                    } else */
            if (event.item == Items.LEAVE.itemStack) event.player.kickPlayer("LEAVE")
        }
    }

    @EventHandler
    open fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
    }

    @EventHandler
    open fun onPlayerRespawnEvent(event: PlayerRespawnEvent) {
        event.respawnLocation = lobbyLocation
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

    protected abstract fun setJoinItems(player: Player)
}