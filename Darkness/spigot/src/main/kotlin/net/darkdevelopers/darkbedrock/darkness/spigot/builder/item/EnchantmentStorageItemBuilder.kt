/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IEnchantmentStorageItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 02:10.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class EnchantmentStorageItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IEnchantmentStorageItemBuilder {

    override val itemMeta = super.itemMeta as EnchantmentStorageMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun addStoredEnchant(
        enchantment: Enchantment,
        level: Int,
        ignoreLevelRestriction: Boolean
    ): IEnchantmentStorageItemBuilder = apply { itemMeta.addStoredEnchant(enchantment, level, ignoreLevelRestriction) }

    override fun removeStoredEnchant(enchantment: Enchantment): IEnchantmentStorageItemBuilder =
        apply { itemMeta.removeStoredEnchant(enchantment) }

}
