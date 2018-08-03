/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 02:59.
 * Last edit 03.08.2018
 */
class MiniBossModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("MiniBossModule", "1.0", "Lars Artmann | LartyHD", "")
    private val types = arrayListOf(ItemStack(Material.LEATHER_HELMET), ItemStack(Material.CHAINMAIL_HELMET), ItemStack(Material.GOLD_HELMET), ItemStack(Material.IRON_HELMET), ItemStack(Material.DIAMOND_HELMET))
    private val random = Random()


    @EventHandler
    fun onEntitySpawnEvent(event: EntitySpawnEvent) {
        if (event.isCancelled ||
                event.entityType == EntityType.ZOMBIE ||
                event.entityType == EntityType.ARMOR_STAND ||
                event.entityType == EntityType.DROPPED_ITEM) return
        cancel(event)
        (event.location.world.spawnEntity(event.location, EntityType.ZOMBIE) as Zombie).apply {
            isBaby = true
            customName = "§4§lMiniBoss"
            equipment.apply {
                helmet = types[random()].clone()
                chestplate = types[random()].clone()
                leggings = types[random()].clone()
                boots = types[random()].clone()
                itemInHand = ItemStack(Material.IRON_SWORD)
            }
            maxHealth = (random.nextInt(100) + 20).toDouble()
            health = maxHealth
            activePotionEffects.add(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Int.MAX_VALUE, random.nextInt(3), false, false))
            isCustomNameVisible = true
        }
    }

    private fun random() = random.nextInt(5)

}