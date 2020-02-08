/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import org.bukkit.enchantments.Enchantment

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:39.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IEnchantmentStorageItemBuilder : IItemBuilder {

    fun addStoredEnchant(
        enchantment: Enchantment,
        level: Int,
        ignoreLevelRestriction: Boolean = false
    ): IEnchantmentStorageItemBuilder

    fun removeStoredEnchant(enchantment: Enchantment): IEnchantmentStorageItemBuilder

}