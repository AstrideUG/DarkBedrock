/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:Suppress("unused", "DEPRECATION")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import org.bukkit.event.Cancellable
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
import org.spigotmc.event.entity.EntityMountEvent

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 06:25.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 *
 */
val cancelFunction: (Cancellable) -> Unit = { it.cancel() }

var cancelAsyncPlayerChat: Boolean = false
    set(value) {
        field = value
        ::cancelAsyncPlayerChat.name.updateEvent<AsyncPlayerChatEvent>(
            value, cancelFunction
        )
    }
var cancelBlockBreak: Boolean = false
    set(value) {
        field = value
        ::cancelBlockBreak.name.updateEvent<BlockBreakEvent>(
            value, cancelFunction
        )
    }
var cancelBlockBurn: Boolean = false
    set(value) {
        field = value
        ::cancelBlockBurn.name.updateEvent<BlockBurnEvent>(
            value, cancelFunction
        )
    }
var cancelBlockDamage: Boolean = false
    set(value) {
        field = value
        ::cancelBlockDamage.name.updateEvent<BlockDamageEvent>(
            value, cancelFunction
        )
    }
var cancelBlockDispense: Boolean = false
    set(value) {
        field = value
        ::cancelBlockDispense.name.updateEvent<BlockDispenseEvent>(
            value, cancelFunction
        )
    }
var cancelBlockExplode: Boolean = false
    set(value) {
        field = value
        ::cancelBlockExplode.name.updateEvent<BlockExplodeEvent>(
            value, cancelFunction
        )
    }
var cancelBlockFade: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFade.name.updateEvent<BlockFadeEvent>(
            value, cancelFunction
        )
    }
var cancelBlockFrom: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFrom.name.updateEvent<BlockFormEvent>(
            value, cancelFunction
        )
    }
var cancelBlockFromTo: Boolean = false
    set(value) {
        field = value
        ::cancelBlockFromTo.name.updateEvent<BlockFromToEvent>(
            value, cancelFunction
        )
    }
var cancelBlockGrow: Boolean = false
    set(value) {
        field = value
        ::cancelBlockGrow.name.updateEvent<BlockGrowEvent>(
            value, cancelFunction
        )
    }
var cancelBlockIgnite: Boolean = false
    set(value) {
        field = value
        ::cancelBlockIgnite.name.updateEvent<BlockIgniteEvent>(
            value, cancelFunction
        )
    }
var cancelBlockMultiPlace: Boolean = false
    set(value) {
        field = value
        ::cancelBlockMultiPlace.name.updateEvent<BlockMultiPlaceEvent>(
            value, cancelFunction
        )
    }
var cancelBlockPhysics: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPhysics.name.updateEvent<BlockPhysicsEvent>(
            value, cancelFunction
        )
    }
var cancelBlockPiston: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPiston.name.updateEvent<BlockPistonEvent>(
            value, cancelFunction
        )
    }
var cancelBlockPistonExtend: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPistonExtend.name.updateEvent<BlockPistonExtendEvent>(
            value, cancelFunction
        )
    }
var cancelBlockPistonRetract: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPistonRetract.name.updateEvent<BlockPistonRetractEvent>(
            value, cancelFunction
        )
    }
var cancelBlockPlace: Boolean = false
    set(value) {
        field = value
        ::cancelBlockPlace.name.updateEvent<BlockPlaceEvent>(
            value, cancelFunction
        )
    }
var cancelBlockSpread: Boolean = false
    set(value) {
        field = value
        ::cancelBlockSpread.name.updateEvent<BlockSpreadEvent>(
            value, cancelFunction
        )
    }
var cancelBrew: Boolean = false
    set(value) {
        field = value
        ::cancelBrew.name.updateEvent<BrewEvent>(
            value, cancelFunction
        )
    }
var cancelChunkUnload: Boolean = false
    set(value) {
        field = value
        ::cancelChunkUnload.name.updateEvent<ChunkUnloadEvent>(
            value, cancelFunction
        )
    }
var cancelCraftItem: Boolean = false
    set(value) {
        field = value
        ::cancelCraftItem.name.updateEvent<CraftItemEvent>(
            value, cancelFunction
        )
    }
var cancelCreatureSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelCreatureSpawn.name.updateEvent<CreatureSpawnEvent>(
            value, cancelFunction
        )
    }
var cancelCreeperPower: Boolean = false
    set(value) {
        field = value
        ::cancelCreeperPower.name.updateEvent<CreeperPowerEvent>(
            value, cancelFunction
        )
    }
var cancelEnchantItem: Boolean = false
    set(value) {
        field = value
        ::cancelEnchantItem.name.updateEvent<EnchantItemEvent>(
            value, cancelFunction
        )
    }
var cancelEntityBlockForm: Boolean = false
    set(value) {
        field = value
        ::cancelEntityBlockForm.name.updateEvent<EntityBlockFormEvent>(
            value, cancelFunction
        )
    }
var cancelEntityBreakDoor: Boolean = false
    set(value) {
        field = value
        ::cancelEntityBreakDoor.name.updateEvent<EntityBreakDoorEvent>(
            value, cancelFunction
        )
    }
var cancelEntityChangeBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityChangeBlock.name.updateEvent<EntityChangeBlockEvent>(
            value, cancelFunction
        )
    }
var cancelEntityCombustByBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombustByBlock.name.updateEvent<EntityCombustByBlockEvent>(
            value, cancelFunction
        )
    }
var cancelEntityCombustByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombustByEntity.name.updateEvent<EntityCombustByEntityEvent>(
            value, cancelFunction
        )
    }
var cancelEntityCombust: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCombust.name.updateEvent<EntityCombustEvent>(
            value, cancelFunction
        )
    }
var cancelEntityCreatePortal: Boolean = false
    set(value) {
        field = value
        ::cancelEntityCreatePortal.name.updateEvent<EntityCreatePortalEvent>(
            value, cancelFunction
        )
    }
var cancelEntityDamageByBlock: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamageByBlock.name.updateEvent<EntityDamageByBlockEvent>(
            value, cancelFunction
        )
    }
var cancelEntityDamageByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamageByEntity.name.updateEvent<EntityDamageByEntityEvent>(
            value, cancelFunction
        )
    }
var cancelEntityDamage: Boolean = false
    set(value) {
        field = value
        ::cancelEntityDamage.name.updateEvent<EntityDamageEvent>(
            value, cancelFunction
        )
    }
var cancelEntityExplode: Boolean = false
    set(value) {
        field = value
        ::cancelEntityExplode.name.updateEvent<EntityExplodeEvent>(
            value, cancelFunction
        )
    }
var cancelEntityInteract: Boolean = false
    set(value) {
        field = value
        ::cancelEntityInteract.name.updateEvent<EntityInteractEvent>(
            value, cancelFunction
        )
    }
var cancelEntityMount: Boolean = false
    set(value) {
        field = value
        ::cancelEntityMount.name.updateEvent<EntityMountEvent>(
            value, cancelFunction
        )
    }
var cancelEntityPortal: Boolean = false
    set(value) {
        field = value
        ::cancelEntityPortal.name.updateEvent<EntityPortalEvent>(
            value, cancelFunction
        )
    }
var cancelEntityPortalExit: Boolean = false
    set(value) {
        field = value
        ::cancelEntityPortalExit.name.updateEvent<EntityPortalExitEvent>(
            value, cancelFunction
        )
    }
var cancelEntityRegainHealth: Boolean = false
    set(value) {
        field = value
        ::cancelEntityRegainHealth.name.updateEvent<EntityRegainHealthEvent>(
            value, cancelFunction
        )
    }
var cancelEntityShootBow: Boolean = false
    set(value) {
        field = value
        ::cancelEntityShootBow.name.updateEvent<EntityShootBowEvent>(
            value, cancelFunction
        )
    }
var cancelEntitySpawn: Boolean = false
    set(value) {
        field = value
        ::cancelEntitySpawn.name.updateEvent<EntitySpawnEvent>(
            value, cancelFunction
        )
    }
var cancelEntityTame: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTame.name.updateEvent<EntityTameEvent>(
            value, cancelFunction
        )
    }
var cancelEntityTarget: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTarget.name.updateEvent<EntityTargetEvent>(
            value, cancelFunction
        )
    }
var cancelEntityTargetLivingEntity: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTargetLivingEntity.name.updateEvent<EntityTargetLivingEntityEvent>(
            value, cancelFunction
        )
    }
var cancelEntityTeleport: Boolean = false
    set(value) {
        field = value
        ::cancelEntityTeleport.name.updateEvent<EntityTeleportEvent>(
            value, cancelFunction
        )
    }
var cancelExplosionPrime: Boolean = false
    set(value) {
        field = value
        ::cancelExplosionPrime.name.updateEvent<ExplosionPrimeEvent>(
            value, cancelFunction
        )
    }
var cancelFireworkExplode: Boolean = false
    set(value) {
        field = value
        ::cancelFireworkExplode.name.updateEvent<FireworkExplodeEvent>(
            value, cancelFunction
        )
    }
var cancelFoodLevelChange: Boolean = false
    set(value) {
        field = value
        ::cancelFoodLevelChange.name.updateEvent<FoodLevelChangeEvent>(
            value, cancelFunction
        )
    }
var cancelFurnaceBurn: Boolean = false
    set(value) {
        field = value
        ::cancelFurnaceBurn.name.updateEvent<FurnaceBurnEvent>(
            value, cancelFunction
        )
    }
var cancelFurnaceSmelt: Boolean = false
    set(value) {
        field = value
        ::cancelFurnaceSmelt.name.updateEvent<FurnaceSmeltEvent>(
            value, cancelFunction
        )
    }
var cancelHangingBreakByEntity: Boolean = false
    set(value) {
        field = value
        ::cancelHangingBreakByEntity.name.updateEvent<HangingBreakByEntityEvent>(
            value, cancelFunction
        )
    }
var cancelHangingBreak: Boolean = false
    set(value) {
        field = value
        ::cancelHangingBreak.name.updateEvent<HangingBreakEvent>(
            value, cancelFunction
        )
    }
var cancelHangingPlace: Boolean = false
    set(value) {
        field = value
        ::cancelHangingPlace.name.updateEvent<HangingPlaceEvent>(
            value, cancelFunction
        )
    }
var cancelHorseJump: Boolean = false
    set(value) {
        field = value
        ::cancelHorseJump.name.updateEvent<HorseJumpEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryClick: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryClick.name.updateEvent<InventoryClickEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryCreative: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryCreative.name.updateEvent<InventoryCreativeEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryDrag: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryDrag.name.updateEvent<InventoryDragEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryInteract: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryInteract.name.updateEvent<InventoryInteractEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryMoveItem: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryMoveItem.name.updateEvent<InventoryMoveItemEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryOpen: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryOpen.name.updateEvent<InventoryOpenEvent>(
            value, cancelFunction
        )
    }
var cancelInventoryPickupItem: Boolean = false
    set(value) {
        field = value
        ::cancelInventoryPickupItem.name.updateEvent<InventoryPickupItemEvent>(
            value, cancelFunction
        )
    }
var cancelItemDespawn: Boolean = false
    set(value) {
        field = value
        ::cancelItemDespawn.name.updateEvent<ItemDespawnEvent>(
            value, cancelFunction
        )
    }
var cancelItemMerge: Boolean = false
    set(value) {
        field = value
        ::cancelItemMerge.name.updateEvent<ItemMergeEvent>(
            value, cancelFunction
        )
    }
var cancelItemSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelItemSpawn.name.updateEvent<ItemSpawnEvent>(
            value, cancelFunction
        )
    }
var cancelLeavesDecay: Boolean = false
    set(value) {
        field = value
        ::cancelLeavesDecay.name.updateEvent<LeavesDecayEvent>(
            value, cancelFunction
        )
    }
var cancelLightningStrike: Boolean = false
    set(value) {
        field = value
        ::cancelLightningStrike.name.updateEvent<LightningStrikeEvent>(
            value, cancelFunction
        )
    }
var cancelNotePlay: Boolean = false
    set(value) {
        field = value
        ::cancelNotePlay.name.updateEvent<NotePlayEvent>(
            value, cancelFunction
        )
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
        ::cancelPaintingBreakByEntity.name.updateEvent<PaintingBreakByEntityEvent>(
            value, cancelFunction
        )
    }
@Suppress("DEPRECATION")
@Deprecated(
    "This event has been replaced by HangingBreakEvent",
    ReplaceWith(
        "cancelHangingBreak",
        "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.getCancelHangingBreak"
    )
)
var cancelPaintingBreak: Boolean = false
    set(value) {
        field = value
        ::cancelPaintingBreak.name.updateEvent<PaintingBreakEvent>(
            value, cancelFunction
        )
    }
@Suppress("DEPRECATION")
@Deprecated(
    "This event has been replaced by HangingPlaceEvent",
    ReplaceWith(
        "cancelHangingPlace",
        "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.getCancelHangingPlace"
    )
)
var cancelPaintingPlace: Boolean = false
    set(value) {
        field = value
        ::cancelPaintingPlace.name.updateEvent<PaintingPlaceEvent>(
            value, cancelFunction
        )
    }
var cancelPigZap: Boolean = false
    set(value) {
        field = value
        ::cancelPigZap.name.updateEvent<PigZapEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerAchievementAwarded: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerAchievementAwarded.name.updateEvent<PlayerAchievementAwardedEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerAnimation: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerAnimation.name.updateEvent<PlayerAnimationEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerArmorStandManipulate: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerArmorStandManipulate.name.updateEvent<PlayerArmorStandManipulateEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerBedEnter: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBedEnter.name.updateEvent<PlayerBedEnterEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerBucketEmpty: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucketEmpty.name.updateEvent<PlayerBucketEmptyEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerBucket: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucket.name.updateEvent<PlayerBucketEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerBucketFill: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerBucketFill.name.updateEvent<PlayerBucketFillEvent>(
            value, cancelFunction
        )
    }
@Suppress("DEPRECATION")
@Deprecated(
    "Listening to this event forces chat to wait for the main thread, delaying chat messages.",
    ReplaceWith(
        "cancelAsyncPlayerChat",
        "net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.getCancelAsyncPlayerChat"
    )
)
var cancelPlayerChat: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerChat.name.updateEvent<PlayerChatEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerCommandPreprocess: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerCommandPreprocess.name.updateEvent<PlayerCommandPreprocessEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerDropItem: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerDropItem.name.updateEvent<PlayerDropItemEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerEditBook: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerEditBook.name.updateEvent<PlayerEditBookEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerFish: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerFish.name.updateEvent<PlayerFishEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerGameModeChange: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerGameModeChange.name.updateEvent<PlayerGameModeChangeEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerInteractAtEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteractAtEntity.name.updateEvent<PlayerInteractAtEntityEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerInteractEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteractEntity.name.updateEvent<PlayerInteractEntityEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerInteract: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerInteract.name.updateEvent<PlayerInteractEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerItemConsume: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemConsume.name.updateEvent<PlayerItemConsumeEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerItemDamage: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemDamage.name.updateEvent<PlayerItemDamageEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerItemHeld: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerItemHeld.name.updateEvent<PlayerItemHeldEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerKick: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerKick.name.updateEvent<PlayerKickEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerLeashEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerLeashEntity.name.updateEvent<PlayerLeashEntityEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerMove: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerMove.name.updateEvent<PlayerMoveEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerPickupItem: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerPickupItem.name.updateEvent<PlayerPickupItemEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerPortal: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerPortal.name.updateEvent<PlayerPortalEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerShearEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerShearEntity.name.updateEvent<PlayerShearEntityEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerStatisticIncrement: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerStatisticIncrement.name.updateEvent<PlayerStatisticIncrementEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerTeleport: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerTeleport.name.updateEvent<PlayerTeleportEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerToggleFlight: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleFlight.name.updateEvent<PlayerToggleFlightEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerToggleSneak: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleSneak.name.updateEvent<PlayerToggleSneakEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerToggleSprint: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerToggleSprint.name.updateEvent<PlayerToggleSprintEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerUnleashEntity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerUnleashEntity.name.updateEvent<PlayerUnleashEntityEvent>(
            value, cancelFunction
        )
    }
var cancelPlayerVelocity: Boolean = false
    set(value) {
        field = value
        ::cancelPlayerVelocity.name.updateEvent<PlayerVelocityEvent>(
            value, cancelFunction
        )
    }
var cancelPortalCreate: Boolean = false
    set(value) {
        field = value
        ::cancelPortalCreate.name.updateEvent<PortalCreateEvent>(
            value, cancelFunction
        )
    }
var cancelPotionSplash: Boolean = false
    set(value) {
        field = value
        ::cancelPotionSplash.name.updateEvent<PotionSplashEvent>(
            value, cancelFunction
        )
    }
var cancelPrepareItemEnchant: Boolean = false
    set(value) {
        field = value
        ::cancelPrepareItemEnchant.name.updateEvent<PrepareItemEnchantEvent>(
            value, cancelFunction
        )
    }
var cancelProjectileLaunch: Boolean = false
    set(value) {
        field = value
        ::cancelProjectileLaunch.name.updateEvent<ProjectileLaunchEvent>(
            value, cancelFunction
        )
    }
var cancelRemoteServerCommand: Boolean = false
    set(value) {
        field = value
        ::cancelRemoteServerCommand.name.updateEvent<RemoteServerCommandEvent>(
            value, cancelFunction
        )
    }
var cancelServerCommand: Boolean = false
    set(value) {
        field = value
        ::cancelServerCommand.name.updateEvent<ServerCommandEvent>(
            value, cancelFunction
        )
    }
var cancelSheepDyeWool: Boolean = false
    set(value) {
        field = value
        ::cancelSheepDyeWool.name.updateEvent<SheepDyeWoolEvent>(
            value, cancelFunction
        )
    }
var cancelSheepRegrowWool: Boolean = false
    set(value) {
        field = value
        ::cancelSheepRegrowWool.name.updateEvent<SheepRegrowWoolEvent>(
            value, cancelFunction
        )
    }
var cancelSignChange: Boolean = false
    set(value) {
        field = value
        ::cancelSignChange.name.updateEvent<SignChangeEvent>(
            value, cancelFunction
        )
    }
var cancelSlimeSplit: Boolean = false
    set(value) {
        field = value
        ::cancelSlimeSplit.name.updateEvent<SlimeSplitEvent>(
            value, cancelFunction
        )
    }
var cancelSpawnerSpawn: Boolean = false
    set(value) {
        field = value
        ::cancelSpawnerSpawn.name.updateEvent<SpawnerSpawnEvent>(
            value, cancelFunction
        )
    }
var cancelStructureGrow: Boolean = false
    set(value) {
        field = value
        ::cancelStructureGrow.name.updateEvent<StructureGrowEvent>(
            value, cancelFunction
        )
    }
var cancelThunderChange: Boolean = false
    set(value) {
        field = value
        ::cancelThunderChange.name.updateEvent<ThunderChangeEvent>(
            value, cancelFunction
        )
    }
var cancelVehicleDamage: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleDamage.name.updateEvent<VehicleDamageEvent>(
            value, cancelFunction
        )
    }
var cancelVehicleDestroy: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleDestroy.name.updateEvent<VehicleDestroyEvent>(
            value, cancelFunction
        )
    }
var cancelVehicleEnter: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleEnter.name.updateEvent<VehicleEnterEvent>(
            value, cancelFunction
        )
    }
var cancelVehicleEntityCollision: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleEntityCollision.name.updateEvent<VehicleEntityCollisionEvent>(
            value, cancelFunction
        )
    }
var cancelVehicleExit: Boolean = false
    set(value) {
        field = value
        ::cancelVehicleExit.name.updateEvent<VehicleExitEvent>(
            value, cancelFunction
        )
    }
var cancelWeatherChange: Boolean = false
    set(value) {
        field = value
        ::cancelWeatherChange.name.updateEvent<WeatherChangeEvent>(
            value, cancelFunction
        )
    }
var cancelWorldUnload: Boolean = false
    set(value) {
        field = value
        ::cancelWorldUnload.name.updateEvent<WorldUnloadEvent>(
            value, cancelFunction
        )
    }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:06.
 * Current Version: 1.0 (15.02.2019 - 02.05.2019)
 */
fun Cancellable.cancel(): Unit = cancel(true)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.02.2019 04:07.
 * Current Version: 1.0 (15.02.2019 - 02.05.2019)
 */
fun Cancellable.cancel(value: Boolean) {
    isCancelled = value
}

