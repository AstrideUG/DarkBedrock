/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import org.bukkit.FireworkEffect

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:36.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IFireworkItemBuilder : IItemBuilder {

    fun setFireworkMetaEffect(fireworkEffect: Iterable<FireworkEffect>): IFireworkItemBuilder

    fun setFireworkMetaEffect(vararg fireworkEffect: FireworkEffect): IFireworkItemBuilder =
        apply { setFireworkMetaEffect(fireworkEffect.toList()) }

    fun setPower(power: Int): IFireworkItemBuilder

    fun removeEffect(effect: Int): IFireworkItemBuilder

    fun clearEffects(): IFireworkItemBuilder

}