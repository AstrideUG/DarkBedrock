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
    cancelPlayerDropItem = true
    cancelPlayerPickupItem = true
    cancelFoodLevelChange = true
    cancelInventoryClick = true
    cancelPlayerInteract = true
    cancelEntityDamage = true
    cancelBlockBreak = true
    cancelBlockPlace = true
    cancelBlockBurn = true
    cancelBlockExplode = true
    cancelBlockFrom = true
    cancelBlockFromTo = true
    cancelBlockGrow = true
    cancelBlockPhysics = true
    cancelWeatherChange = true
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 12:05.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun restetCancel() {
    cancelPlayerDropItem = false
    cancelPlayerPickupItem = false
    cancelFoodLevelChange = false
    cancelInventoryClick = false
    cancelPlayerInteract = false
    cancelEntityDamage = false
    cancelBlockBreak = false
    cancelBlockPlace = false
    cancelBlockBurn = false
    cancelBlockExplode = false
    cancelBlockFrom = false
    cancelBlockFromTo = false
    cancelBlockGrow = false
    cancelBlockPhysics = false
    cancelWeatherChange = false
}