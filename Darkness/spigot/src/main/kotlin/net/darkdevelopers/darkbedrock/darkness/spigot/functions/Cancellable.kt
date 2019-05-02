/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

@file:Suppress("unused")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Bukkit
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.entity.*
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.painting.PaintingBreakByEntityEvent
import org.bukkit.event.painting.PaintingBreakEvent
import org.bukkit.event.painting.PaintingPlaceEvent
import org.bukkit.event.player.*
import org.bukkit.event.server.RemoteServerCommandEvent
import org.bukkit.event.server.ServerCommandEvent
import org.bukkit.event.vehicle.*
import org.bukkit.event.weather.LightningStrikeEvent
import org.bukkit.event.weather.ThunderChangeEvent
import org.bukkit.event.weather.WeatherChangeEvent
import org.bukkit.event.world.ChunkUnloadEvent
import org.bukkit.event.world.PortalCreateEvent
import org.bukkit.event.world.StructureGrowEvent
import org.bukkit.event.world.WorldUnloadEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.spigotmc.event.entity.EntityMountEvent

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 06:25.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */

var pluginManager: PluginManager = Bukkit.getPluginManager()
var plugin: Plugin = pluginManager.plugins.first()
var function: (Listener, Event) -> Unit = { _, event -> (event as? Cancellable)?.cancel() }
var priority: EventPriority = EventPriority.NORMAL
private val activeListener: MutableMap<String, Listener> = mutableMapOf()

var cancelAsyncPlayerChat: Boolean = false
    set(value) {
        field = value
        ::cancelAsyncPlayerChat.name.updateEvent<AsyncPlayerChatEvent>(value)
    }
var cancelBlockBreak: Boolean = false
    set(value) {
        field = value
        ::cancelBlockBreak.name.updateEvent<BlockBreakEvent>(value)
    }
var cancelBlockBurn: Boolean = false
    set(value) {
        field = value
        ::cancelBlockBurn.name.updateEvent<BlockBurnEvent>(value)
    }
var cancelBlockDamage: Boolean = false
    set(value) {
        field = value
        ::cancelBlockDamage.name.updateEvent<BlockDamageEvent>(value)
    }
var cancelBlockDispense: Boolean = false
    set(value) {
        field = value
        ::cancelBlockDispense.name.updateEvent<BlockDispenseEvent>(value)
    }
var cancelBlockExplode: Boolean = false
    set(value) {
        field = value
        ::cancelBlockExplode.name.updateEvent<BlockExplodeEvent>(value)
    }
var cancelBlockFade: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFade.name.updateEvent<BlockFadeEvent>(value)
    }
var cancelBlockFrom: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFrom.name.updateEvent<BlockFormEvent>(value)
    }
var cancelBlockFromTo: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFromTo.name.updateEvent<BlockFromToEvent>(value)
    }
var cancelBlockGrow: Boolean = false
    set(value) {
        field = value
        ::cancelBlockGrow.name.updateEvent<BlockGrowEvent>(value)
    }
var cancelBlockIgnite: Boolean = false
    set(value) {
        field = value
        ::cancelBlockIgnite.name.updateEvent<BlockIgniteEvent>(value)
    }
var cancelBlockMultiPlace: Boolean = false
    set(value) {
        field = value
        ::cancelBlockMultiPlace.name.updateEvent<BlockMultiPlaceEvent>(value)
    }
var cancelBlockPhysics: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPhysics.name.updateEvent<BlockPhysicsEvent>(value)
    }
var cancelBlockPiston: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPiston.name.updateEvent<BlockPistonEvent>(value)
    }
var cancelBlockPistonExtend: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPistonExtend.name.updateEvent<BlockPistonExtendEvent>(value)
    }
var cancelBlockPistonRetract: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPistonRetract.name.updateEvent<BlockPistonRetractEvent>(value)
    }
var cancelBlockPlace: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPlace.name.updateEvent<BlockPlaceEvent>(value)
    }
var cancelBlockSpread: Boolean = false
    set(value) {
        field = value
        ::cancelBlockSpread.name.updateEvent<BlockSpreadEvent>(value)
    }
var cancelBrew: Boolean = false
    set(value) {
        field = value
        ::cancelBrew.name.updateEvent<BrewEvent>(value)
    }
var cancelChunkUnload: Boolean = false
    set(value) {
        field = value
        ::cancelChunkUnload.name.updateEvent<ChunkUnloadEvent>(value)
    }
var cancelCraftItem: Boolean = false
    set(value) {
        field = value
        ::cancelCraftItem.name.updateEvent<CraftItemEvent>(value)
    }
var cancelCreatureSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelCreatureSpawn.name.updateEvent<CreatureSpawnEvent>(value)
    }
var cancelCreeperPower: Boolean = false
    set(value) {
        field = value
        ::cancelCreeperPower.name.updateEvent<CreeperPowerEvent>(value)
    }
var cancelEnchantItem: Boolean = false
    set(value) {
        field = value
        ::cancelEnchantItem.name.updateEvent<EnchantItemEvent>(value)
    }
var cancelEntityBlockForm: Boolean = false
    set(value) {
        field = value
        ::cancelEntityBlockForm.name.updateEvent<EntityBlockFormEvent>(value)
    }
var cancelEntityBreakDoor: Boolean = false
    set(value) {
        field = value
        ::cancelEntityBreakDoor.name.updateEvent<EntityBreakDoorEvent>(value)
    }
var cancelEntityChangeBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityChangeBlock.name.updateEvent<EntityChangeBlockEvent>(value)
    }
var cancelEntityCombustByBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombustByBlock.name.updateEvent<EntityCombustByBlockEvent>(value)
    }
var cancelEntityCombustByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombustByEntity.name.updateEvent<EntityCombustByEntityEvent>(value)
    }
var cancelEntityCombust: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombust.name.updateEvent<EntityCombustEvent>(value)
    }
var cancelEntityCreatePortal: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCreatePortal.name.updateEvent<EntityCreatePortalEvent>(value)
    }
var cancelEntityDamageByBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamageByBlock.name.updateEvent<EntityDamageByBlockEvent>(value)
    }
var cancelEntityDamageByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamageByEntity.name.updateEvent<EntityDamageByEntityEvent>(value)
    }
var cancelEntityDamage: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamage.name.updateEvent<EntityDamageEvent>(value)
    }
var cancelEntityExplode: Boolean = false
    set(value) {
        field = value
        ::cancelEntityExplode.name.updateEvent<EntityExplodeEvent>(value)
    }
var cancelEntityInteract: Boolean = false
    set(value) {
        field = value
        ::cancelEntityInteract.name.updateEvent<EntityInteractEvent>(value)
    }
var cancelEntityMount: Boolean = false
    set(value) {
        field = value
        ::cancelEntityMount.name.updateEvent<EntityMountEvent>(value)
    }
var cancelEntityPortal: Boolean = false
    set(value) {
        field = value
        ::cancelEntityPortal.name.updateEvent<EntityPortalEvent>(value)
    }
var cancelEntityPortalExit: Boolean = false
    set(value) {
        field = value
        ::cancelEntityPortalExit.name.updateEvent<EntityPortalExitEvent>(value)
    }
var cancelEntityRegainHealth: Boolean = false
    set(value) {
        field = value
        ::cancelEntityRegainHealth.name.updateEvent<EntityRegainHealthEvent>(value)
    }
var cancelEntityShootBow: Boolean = false
    set(value) {
        field = value
        ::cancelEntityShootBow.name.updateEvent<EntityShootBowEvent>(value)
    }
var cancelEntitySpawn: Boolean = false
    set(value) {
        field = value
        ::cancelEntitySpawn.name.updateEvent<EntitySpawnEvent>(value)
    }
var cancelEntityTame: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTame.name.updateEvent<EntityTameEvent>(value)
    }
var cancelEntityTarget: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTarget.name.updateEvent<EntityTargetEvent>(value)
    }
var cancelEntityTargetLivingEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTargetLivingEntity.name.updateEvent<EntityTargetLivingEntityEvent>(value)
    }
var cancelEntityTeleport: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTeleport.name.updateEvent<EntityTeleportEvent>(value)
    }
var cancelExplosionPrime: Boolean = false
    set(value) {
        field = value
        ::cancelExplosionPrime.name.updateEvent<ExplosionPrimeEvent>(value)
    }
var cancelFireworkExplode: Boolean = false
    set(value) {
        field = value
        ::cancelFireworkExplode.name.updateEvent<FireworkExplodeEvent>(value)
    }
var cancelFoodLevelChange: Boolean = false
    set(value) {
        field = value
        ::cancelFoodLevelChange.name.updateEvent<FoodLevelChangeEvent>(value)
    }
var cancelFurnaceBurn: Boolean = false
    set(value) {
        field = value
        ::cancelFurnaceBurn.name.updateEvent<FurnaceBurnEvent>(value)
    }
var cancelFurnaceSmelt: Boolean = false
    set(value) {
        field = value
        ::cancelFurnaceSmelt.name.updateEvent<FurnaceSmeltEvent>(value)
    }
var cancelHangingBreakByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelHangingBreakByEntity.name.updateEvent<HangingBreakByEntityEvent>(value)
    }
var cancelHangingBreak: Boolean = false
    set(value) {
        field = value
        ::cancelHangingBreak.name.updateEvent<HangingBreakEvent>(value)
    }
var cancelHangingPlace: Boolean = false
    set(value) {
        field = value
        ::cancelHangingPlace.name.updateEvent<HangingPlaceEvent>(value)
    }
var cancelHorseJump: Boolean = false
    set(value) {
        field = value
        ::cancelHorseJump.name.updateEvent<HorseJumpEvent>(value)
    }
var cancelInventoryClick: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryClick.name.updateEvent<InventoryClickEvent>(value)
    }
var cancelInventoryCreative: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryCreative.name.updateEvent<InventoryCreativeEvent>(value)
    }
var cancelInventoryDrag: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryDrag.name.updateEvent<InventoryDragEvent>(value)
    }
var cancelInventoryInteract: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryInteract.name.updateEvent<InventoryInteractEvent>(value)
    }
var cancelInventoryMoveItem: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryMoveItem.name.updateEvent<InventoryMoveItemEvent>(value)
    }
var cancelInventoryOpen: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryOpen.name.updateEvent<InventoryOpenEvent>(value)
    }
var cancelInventoryPickupItem: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryPickupItem.name.updateEvent<InventoryPickupItemEvent>(value)
    }
var cancelItemDespawn: Boolean = false
    set(value) {
        field = value
        ::cancelItemDespawn.name.updateEvent<ItemDespawnEvent>(value)
    }
var cancelItemMerge: Boolean = false
    set(value) {
        field = value
        ::cancelItemMerge.name.updateEvent<ItemMergeEvent>(value)
    }
var cancelItemSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelItemSpawn.name.updateEvent<ItemSpawnEvent>(value)
    }
var cancelLeavesDecay: Boolean = false
    set(value) {
        field = value
        ::cancelLeavesDecay.name.updateEvent<LeavesDecayEvent>(value)
    }
var cancelLightningStrike: Boolean = false
    set(value) {
        field = value
        ::cancelLightningStrike.name.updateEvent<LightningStrikeEvent>(value)
    }
var cancelNotePlay: Boolean = false
    set(value) {
        field = value
        ::cancelNotePlay.name.updateEvent<NotePlayEvent>(value)
    }
@Suppress("DEPRECATION")
@Deprecated(
    "This event has been replaced by HangingBreakByEntityEvent",
    ReplaceWith(
        "cancelHangingBreakByEntityEvent",
        "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancelHangingBreakByEntityEvent"
    )
)
var cancelPaintingBreakByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPaintingBreakByEntity.name.updateEvent<PaintingBreakByEntityEvent>(value)
    }
@Suppress("DEPRECATION")
@Deprecated(
    "This event has been replaced by HangingBreakEvent",
    ReplaceWith("cancelHangingBreak", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancelHangingBreak")
)
var cancelPaintingBreak: Boolean = false
    set(value) {
        field = value
        ::cancelPaintingBreak.name.updateEvent<PaintingBreakEvent>(value)
    }
@Suppress("DEPRECATION")
@Deprecated(
    "This event has been replaced by HangingPlaceEvent",
    ReplaceWith("cancelHangingPlace", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancelHangingPlace")
)
var cancelPaintingPlace: Boolean = false
    set(value) {
        field = value
        ::cancelPaintingPlace.name.updateEvent<PaintingPlaceEvent>(value)
    }
var cancelPigZap: Boolean = false
    set(value) {
        field = value
        ::cancelPigZap.name.updateEvent<PigZapEvent>(value)
    }
var cancelPlayerAchievementAwarded: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerAchievementAwarded.name.updateEvent<PlayerAchievementAwardedEvent>(value)
    }
var cancelPlayerAnimation: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerAnimation.name.updateEvent<PlayerAnimationEvent>(value)
    }
var cancelPlayerArmorStandManipulate: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerArmorStandManipulate.name.updateEvent<PlayerArmorStandManipulateEvent>(value)
    }
var cancelPlayerBedEnter: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBedEnter.name.updateEvent<PlayerBedEnterEvent>(value)
    }
var cancelPlayerBucketEmpty: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucketEmpty.name.updateEvent<PlayerBucketEmptyEvent>(value)
    }
var cancelPlayerBucket: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucket.name.updateEvent<PlayerBucketEvent>(value)
    }
var cancelPlayerBucketFill: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucketFill.name.updateEvent<PlayerBucketFillEvent>(value)
    }
@Suppress("DEPRECATION")
@Deprecated(
    "Listening to this event forces chat to wait for the main thread, delaying chat messages.",
    ReplaceWith(
        "cancelAsyncPlayerChat",
        "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancelAsyncPlayerChat"
    )
)
var cancelPlayerChat: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerChat.name.updateEvent<PlayerChatEvent>(value)
    }
var cancelPlayerCommandPreprocess: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerCommandPreprocess.name.updateEvent<PlayerCommandPreprocessEvent>(value)
    }
var cancelPlayerDropItem: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerDropItem.name.updateEvent<PlayerDropItemEvent>(value)
    }
var cancelPlayerEditBook: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerEditBook.name.updateEvent<PlayerEditBookEvent>(value)
    }
var cancelPlayerFish: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerFish.name.updateEvent<PlayerFishEvent>(value)
    }
var cancelPlayerGameModeChange: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerGameModeChange.name.updateEvent<PlayerGameModeChangeEvent>(value)
    }
var cancelPlayerInteractAtEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteractAtEntity.name.updateEvent<PlayerInteractAtEntityEvent>(value)
    }
var cancelPlayerInteractEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteractEntity.name.updateEvent<PlayerInteractEntityEvent>(value)
    }
var cancelPlayerInteract: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteract.name.updateEvent<PlayerInteractEvent>(value)
    }
var cancelPlayerItemConsume: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemConsume.name.updateEvent<PlayerItemConsumeEvent>(value)
    }
var cancelPlayerItemDamage: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemDamage.name.updateEvent<PlayerItemDamageEvent>(value)
    }
var cancelPlayerItemHeld: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemHeld.name.updateEvent<PlayerItemHeldEvent>(value)
    }
var cancelPlayerKick: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerKick.name.updateEvent<PlayerKickEvent>(value)
    }
var cancelPlayerLeashEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerLeashEntity.name.updateEvent<PlayerLeashEntityEvent>(value)
    }
var cancelPlayerMove: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerMove.name.updateEvent<PlayerMoveEvent>(value)
    }
var cancelPlayerPickupItem: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerPickupItem.name.updateEvent<PlayerPickupItemEvent>(value)
    }
var cancelPlayerPortal: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerPortal.name.updateEvent<PlayerPortalEvent>(value)
    }
var cancelPlayerShearEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerShearEntity.name.updateEvent<PlayerShearEntityEvent>(value)
    }
var cancelPlayerStatisticIncrement: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerStatisticIncrement.name.updateEvent<PlayerStatisticIncrementEvent>(value)
    }
var cancelPlayerTeleport: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerTeleport.name.updateEvent<PlayerTeleportEvent>(value)
    }
var cancelPlayerToggleFlight: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleFlight.name.updateEvent<PlayerToggleFlightEvent>(value)
    }
var cancelPlayerToggleSneak: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleSneak.name.updateEvent<PlayerToggleSneakEvent>(value)
    }
var cancelPlayerToggleSprint: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleSprint.name.updateEvent<PlayerToggleSprintEvent>(value)
    }
var cancelPlayerUnleashEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerUnleashEntity.name.updateEvent<PlayerUnleashEntityEvent>(value)
    }
var cancelPlayerVelocity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerVelocity.name.updateEvent<PlayerVelocityEvent>(value)
    }
var cancelPortalCreate: Boolean = false
    set(value) {
        field = value
        ::cancelPortalCreate.name.updateEvent<PortalCreateEvent>(value)
    }
var cancelPotionSplash: Boolean = false
    set(value) {
        field = value
        ::cancelPotionSplash.name.updateEvent<PotionSplashEvent>(value)
    }
var cancelPrepareItemEnchant: Boolean = false
    set(value) {
        field = value
        ::cancelPrepareItemEnchant.name.updateEvent<PrepareItemEnchantEvent>(value)
    }
var cancelProjectileLaunch: Boolean = false
    set(value) {
        field = value
        ::cancelProjectileLaunch.name.updateEvent<ProjectileLaunchEvent>(value)
    }
var cancelRemoteServerCommand: Boolean = false
    set(value) {
        field = value
        ::cancelRemoteServerCommand.name.updateEvent<RemoteServerCommandEvent>(value)
    }
var cancelServerCommand: Boolean = false
    set(value) {
        field = value
        ::cancelServerCommand.name.updateEvent<ServerCommandEvent>(value)
    }
var cancelSheepDyeWool: Boolean = false
    set(value) {
        field = value
        ::cancelSheepDyeWool.name.updateEvent<SheepDyeWoolEvent>(value)
    }
var cancelSheepRegrowWool: Boolean = false
    set(value) {
        field = value
        ::cancelSheepRegrowWool.name.updateEvent<SheepRegrowWoolEvent>(value)
    }
var cancelSignChange: Boolean = false
    set(value) {
        field = value
        ::cancelSignChange.name.updateEvent<SignChangeEvent>(value)
    }
var cancelSlimeSplit: Boolean = false
    set(value) {
        field = value
        ::cancelSlimeSplit.name.updateEvent<SlimeSplitEvent>(value)
    }
var cancelSpawnerSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelSpawnerSpawn.name.updateEvent<SpawnerSpawnEvent>(value)
    }
var cancelStructureGrow: Boolean = false
    set(value) {
        field = value
        ::cancelStructureGrow.name.updateEvent<StructureGrowEvent>(value)
    }
var cancelThunderChange: Boolean = false
    set(value) {
        field = value
        ::cancelThunderChange.name.updateEvent<ThunderChangeEvent>(value)
    }
var cancelVehicleDamage: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleDamage.name.updateEvent<VehicleDamageEvent>(value)
    }
var cancelVehicleDestroy: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleDestroy.name.updateEvent<VehicleDestroyEvent>(value)
    }
var cancelVehicleEnter: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleEnter.name.updateEvent<VehicleEnterEvent>(value)
    }
var cancelVehicleEntityCollision: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleEntityCollision.name.updateEvent<VehicleEntityCollisionEvent>(value)
    }
var cancelVehicleExit: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleExit.name.updateEvent<VehicleExitEvent>(value)
    }
var cancelWeatherChange: Boolean = false
    set(value) {
        field = value
        ::cancelWeatherChange.name.updateEvent<WeatherChangeEvent>(value)
    }
var cancelWorldUnload: Boolean = false
    set(value) {
        field = value
        ::cancelWorldUnload.name.updateEvent<WorldUnloadEvent>(value)
    }

private inline fun <reified E : Event> String.updateEvent(value: Boolean) {
    val listener = activeListener[this]
    if (listener != null && !value) listener.unregister()
    else if (listener == null && value) addEvent<E>()
}

private inline fun <reified E : Event> String.addEvent() {
    activeListener[this] = registerBlocking<E>()
}

private inline fun <reified E : Event> registerBlocking(): Listener = object : Listener {}.apply {
    pluginManager.registerEvent(
        E::class.java,
        this,
        priority,
        function,
        plugin
    )
}

