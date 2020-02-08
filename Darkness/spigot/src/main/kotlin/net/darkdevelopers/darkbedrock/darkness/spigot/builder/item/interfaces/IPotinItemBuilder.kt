/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:37.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IPotinItemBuilder : IItemBuilder {

    fun setMainEffect(potionEffectType: PotionEffectType): IItemBuilder

    fun addCustomEffect(potionEffect: PotionEffect, overwrite: Boolean): IItemBuilder

    fun removeCustomEffect(potionEffectType: PotionEffectType): IItemBuilder

    fun clearCustomEffects(): IItemBuilder

}