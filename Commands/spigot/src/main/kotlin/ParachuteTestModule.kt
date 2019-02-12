/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleFlightEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.12.2018 08:20.
 * Current Version: 1.0 (28.12.2018 - 28.12.2018)
 */
class ParachuteTestModule : Module, Listener {
    override val description: ModuleDescription

    init {
        this.description =
                ModuleDescription("ParachuteTest", "1.0-SNAPSHOT", "DevSnox", "A parachute test module!", false)
    }

    override fun load() {
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("DarkFrame"))
    }

    override fun start() {

    }

    override fun stop() {
        for (id in parachuteMap.values) {
            Bukkit.getScheduler().cancelTask(id)
        }

        parachuteMap.clear()
    }

    @EventHandler
    fun onInteract(event: PlayerToggleFlightEvent) {
        spawnArmorStand(event.player.location)
        event.player.sendMessage("Spawned ArmorStand")
    }

    companion object {

        private val parachuteMap = HashMap<String, Int>()

        fun spawnArmorStand(location: Location) {
            val armorStand = location.world.spawnEntity(location, EntityType.ARMOR_STAND)
            armorStand.customName = "flying idiot"

            armorStand.velocity.x.plus(1)
            armorStand.velocity.z.minus(1)

            object : BukkitRunnable() {
                override fun run() {
                    // Check if players is even online (because it's a final variable)
                    if (!armorStand.isOnGround || !armorStand.isDead) {
                        // Check if the player is over an specific speed 0 = "zero gravity", 0.0001 = DAMN SLOW fall, 0.1 = very slow fall, 0.2 = slow fall ... and so on
                        if (armorStand.velocity.y < -0.2) {
                            // Keep X and Z movement of the player and dampen his fall in a smooth way
                            armorStand.velocity = Vector(
                                armorStand.velocity.x,
                                armorStand.velocity.y / 1.25,
                                armorStand.velocity.z
                            )
                            // Don't forget to reduce fall distance as well or else, even if the player is falling slow he will crush on the floor with a 32 block or so
                            armorStand.fallDistance = armorStand.fallDistance / 1.25f
                            // Checks if the player is too slow for a parachute (if he only jumps 1 block)
                        }
                        // If player is not online
                    } else {
                        armorStand.remove()
                        cancel()
                    }
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("DarkFrame"), 0L, 1L)
        }

        fun onPlayerInteract(e: PlayerInteractEvent) {
            // Checking right click
            if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
                // Checking for a specific item... I used blaze rod for testing
                if (e.player.itemInHand.type == Material.BLAZE_ROD) {
                    // Create final Player variable which could be needed in Runnable (thats why it's final)
                    val player = e.player
                    // Checking the map for an existing entry (means parachute is already active)
                    if (parachuteMap.containsKey(player.name)) {
                        // Cancel task and remove player from map (means parachute effect will not be active any longer)
                        Bukkit.getScheduler().cancelTask(parachuteMap[player.name] as Int)
                        parachuteMap.remove(player.name)
                    } else {
                        // Player had parachute not active so we create an entry in the map
                        parachuteMap[player.name] = Bukkit.getScheduler()
                            .scheduleSyncRepeatingTask(
                                Bukkit.getPluginManager().getPlugin("DarkFrame"),
                                object : BukkitRunnable() {
                                    override fun run() {
                                        // Check if players is even online (because it's a final variable)
                                        if (player.isOnline) {
                                            // Check if the player is over an specific speed 0 = "zero gravity", 0.0001 = DAMN SLOW fall, 0.1 = very slow fall, 0.2 = slow fall ... and so on
                                            if (player.velocity.y < -0.2) {
                                                // Keep X and Z movement of the player and dampen his fall in a smooth way
                                                player.velocity = Vector(
                                                    player.velocity.x,
                                                    player.velocity.y / 1.25,
                                                    player.velocity.z
                                                )
                                                // Don't forget to reduce fall distance as well or else, even if the player is falling slow he will crush on the floor with a 32 block or so
                                                player.fallDistance = player.fallDistance / 1.25f
                                                // Checks if the player is too slow for a parachute (if he only jumps 1 block)
                                            } else if (player.velocity.y > -0.15) {
                                                // Remove his parachute immediately
                                                Bukkit.getScheduler().cancelTask(parachuteMap[player.name] as Int)
                                                parachuteMap.remove(player.name)
                                            }
                                            // If player is not online
                                        } else {
                                            // Remove his entries
                                            Bukkit.getScheduler().cancelTask(parachuteMap[player.name] as Int)
                                            parachuteMap.remove(player.name)
                                        }
                                    }
                                },
                                0L,
                                2L
                            )
                    }
                }
            }
        }
    }
}
