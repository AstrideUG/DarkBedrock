/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 12:05.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun setupCancel() {
    cancelBlockBreak = true
    cancelBlockPlace = true
    cancelBlockBurn = true
    cancelBlockExplode = true
    cancelBlockFrom = true
    cancelBlockFromTo = true
    cancelBlockGrow = true
    cancelBlockPhysics = true
    cancelCraftItem = true
    cancelEntityDamage = true
    cancelFoodLevelChange = true
    cancelInventoryClick = true
    cancelPlayerDropItem = true
    cancelPlayerInteract = true
    cancelPlayerPickupItem = true
    cancelWeatherChange = true
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 12:05.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun resetCancel() {
    cancelBlockBreak = false
    cancelBlockPlace = false
    cancelBlockBurn = false
    cancelBlockExplode = false
    cancelBlockFrom = false
    cancelBlockFromTo = false
    cancelBlockGrow = false
    cancelBlockPhysics = false
    cancelCraftItem = false
    cancelEntityDamage = false
    cancelFoodLevelChange = false
    cancelInventoryClick = false
    cancelPlayerDropItem = false
    cancelPlayerInteract = false
    cancelPlayerPickupItem = false
    cancelWeatherChange = false
}