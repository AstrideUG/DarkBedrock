/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko 2018
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.interfaces

import net.darkdevelopers.darkbedrock.darkness.general.builder.interfaces.Builder
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.block.banner.Pattern
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * Created by Segdo aka. Dominik Milenko on 11.05.2018 08:38.
 * Project: Darkness
 *
 * Web: https://segdogames.com
 * Mail: segdo@segdogames.com
 */
interface IItemBuilder : Builder<ItemStack> {

    fun setMaterial(material: Material): IItemBuilder

    fun setType(material: Material): IItemBuilder

    fun setAmount(amount: Int): IItemBuilder

    fun setDamage(damage: Short): IItemBuilder

    fun setDurability(durability: Short): IItemBuilder

    fun addEnchantment(enchantment: Enchantment, level: Int): IItemBuilder

    fun addEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder

    fun addUnsafeEnchantment(enchantment: Enchantment, level: Int): IItemBuilder

    fun addUnsafeEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder

    fun addLore(lore: Collection<String>): IItemBuilder

    fun addLore(index: Int, lore: Collection<String>): IItemBuilder

    fun addLore(vararg lore: String): IItemBuilder

    fun addLore(lore: String): IItemBuilder

    fun addLore(index: Int, lore: String): IItemBuilder

    fun removeEnchantment(enchantment: Enchantment): IItemBuilder

    fun removeLore(lore: List<String>): IItemBuilder

    fun removeLore(index: Int): IItemBuilder

    fun setLore(lore: List<String>): IItemBuilder

    fun setLore(lore: String): IItemBuilder

    fun setName(name: String): IItemBuilder

    fun setDisplayName(displayName: String): IItemBuilder

    fun setBreakable(): IItemBuilder

    fun setUnbreakable(): IItemBuilder

    fun setUnbreakable(unbreakable: Boolean): IItemBuilder

    fun addEnchant(enchantment: Enchantment, level: Int): IItemBuilder

    fun addEnchant(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): IItemBuilder

    fun addItemFlags(vararg itemFlags: ItemFlag): IItemBuilder

    fun removeEnchant(enchantment: Enchantment): IItemBuilder

    fun removeItemFlags(vararg itemFlags: ItemFlag): IItemBuilder

    fun clearEnchants(): IItemBuilder

    fun clearItemFlags(): IItemBuilder

    fun addAllItemFlags(): IItemBuilder

    fun setBaseColor(dyeColor: DyeColor): IItemBuilder

    fun setPatterns(patterns: List<Pattern>): IItemBuilder

    fun setPattern(index: Int, pattern: Pattern): IItemBuilder

    fun addPattern(pattern: Pattern): IItemBuilder

    fun removePattern(i: Int): IItemBuilder

    fun setBlockState(blockState: BlockState): IItemBuilder

    fun setTitle(title: String): IItemBuilder

    fun setAuthor(author: String): IItemBuilder

    fun setPage(index: Int, page: String): IItemBuilder

    fun setPages(pages: List<String>): IItemBuilder

    fun setPages(vararg pages: String): IItemBuilder

    fun addPage(vararg page: String): IItemBuilder

    fun setFireworkEffectMetaEffect(fireworkEffect: FireworkEffect): IItemBuilder

    fun setFireworkMetaEffect(fireworkEffect: FireworkEffect): IItemBuilder

    fun setFireworkMetaEffect(vararg fireworkEffect: FireworkEffect): IItemBuilder

    fun setFireworkMetaEffect(fireworkEffect: Iterable<FireworkEffect>): IItemBuilder

    fun setPower(power: Int): IItemBuilder

    fun removeEffect(effect: Int): IItemBuilder

    fun clearEffects(): IItemBuilder

    fun setColor(color: Color): IItemBuilder

    fun setScaling(value: Boolean): IItemBuilder

    fun setMainEffect(potionEffectType: PotionEffectType): IItemBuilder

    fun addCustomEffect(potionEffect: PotionEffect, overwrite: Boolean): IItemBuilder

    fun removeCustomEffect(potionEffectType: PotionEffectType): IItemBuilder

    fun clearCustomEffects(): IItemBuilder

    fun setOwner(owner: String): IItemBuilder

    fun setOwner(url: String, name: String): IItemBuilder

}
