/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko 2018
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import net.darkdevelopers.darkbedrock.darkness.general.builder.interfaces.Builder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 08:38.
 * Current Version: 1.0 (11.05.2018 - 07.03.2019)
 */
interface IItemBuilder : Builder<ItemStack> {

    /*ItemStack Setter*/
    fun setMaterial(material: Material): IItemBuilder = setType(material)

    fun setType(material: Material): IItemBuilder

    fun setAmount(amount: Int): IItemBuilder

    fun setDurability(durability: Short): IItemBuilder

    fun setDamage(damage: Short): IItemBuilder = setDurability(damage)
    /*ItemStack Setter*/

    fun addEnchantment(enchantment: Enchantment, level: Int): IItemBuilder

    fun addEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder

    fun addUnsafeEnchantment(enchantment: Enchantment, level: Int): IItemBuilder

    fun addUnsafeEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder

    fun addLore(lore: Collection<String>): IItemBuilder

    fun addLore(vararg lore: String): IItemBuilder = addLore(lore.toList())

    fun addLore(index: Int, lore: Collection<String>): IItemBuilder

    fun addLore(index: Int, vararg lore: String): IItemBuilder = addLore(index, lore.toList())

    fun removeEnchantment(enchantment: Enchantment): IItemBuilder

    fun removeLore(lore: List<String>): IItemBuilder

    fun removeLore(vararg lore: String): IItemBuilder = removeLore(lore.toList())

    fun removeLore(index: Int): IItemBuilder

    fun setLore(lore: List<String>): IItemBuilder

    fun setLore(vararg lore: String): IItemBuilder = setLore(lore.toList())

    fun setName(name: String): IItemBuilder = apply { setDisplayName(name) }

    fun setDisplayName(displayName: String): IItemBuilder

    fun setUnbreakable(unbreakable: Boolean = true): IItemBuilder

    fun addEnchant(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean = true): IItemBuilder

    fun addItemFlags(vararg itemFlags: ItemFlag): IItemBuilder

    fun removeEnchant(enchantment: Enchantment): IItemBuilder

    fun removeItemFlags(vararg itemFlags: ItemFlag): IItemBuilder

    fun clearEnchants(): IItemBuilder

    fun clearItemFlags(): IItemBuilder

    fun addAllItemFlags(): IItemBuilder

}
