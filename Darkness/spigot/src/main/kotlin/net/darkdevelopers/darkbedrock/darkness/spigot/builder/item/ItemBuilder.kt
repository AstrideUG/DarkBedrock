/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

@Suppress("unused")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 08:37.
 * Last edit 12.04.2019
 */
open class ItemBuilder(private val itemStack: ItemStack) : IItemBuilder {

    init {
        if (itemStack.type == Material.AIR) throw IllegalArgumentException("itemStack type can not be air")
    }

    protected open val itemMeta: ItemMeta = itemStack.itemMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    /*ItemStack Setter*/
    override fun setType(material: Material) = apply { itemStack.type = material }

    override fun setAmount(amount: Int): IItemBuilder = apply { itemStack.amount = amount }

    override fun setDurability(durability: Short): IItemBuilder = apply { itemStack.durability = durability }
    /*ItemStack Setter*/

    /*ItemStack Add*/
    override fun addEnchantment(enchantment: Enchantment, level: Int): IItemBuilder =
        apply { itemStack.addEnchantment(enchantment, level) }

    override fun addEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder =
        apply { itemStack.addEnchantments(enchantments) }

    override fun addUnsafeEnchantment(enchantment: Enchantment, level: Int): IItemBuilder =
        apply { itemStack.addUnsafeEnchantment(enchantment, level) }

    override fun addUnsafeEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder =
        apply { itemStack.addUnsafeEnchantments(enchantments) }

    override fun addLore(lore: Collection<String>): IItemBuilder = apply {
        if (lore.isEmpty()) return@apply
        val elements = itemMeta.lore ?: mutableListOf()
        setLore(elements + lore)
    }

    override fun addLore(index: Int, lore: Collection<String>): IItemBuilder = apply {
        if (lore.isEmpty()) return@apply
        val elements = itemMeta.lore ?: mutableListOf()
        elements.addAll(index, lore)
        setLore(elements)
    }
    /*ItemStack Add*/

    /*ItemStack Remove*/
    override fun removeEnchantment(enchantment: Enchantment): IItemBuilder =
        apply { itemStack.removeEnchantment(enchantment) }

    override fun removeLore(lore: List<String>): IItemBuilder = apply {
        if (lore.isEmpty()) return@apply
        val elements = itemMeta.lore ?: mutableListOf()
        setLore(elements - lore)
    }

    override fun removeLore(index: Int): IItemBuilder = apply {
        val elements = itemMeta.lore ?: mutableListOf()
        elements.removeAt(index)
        setLore(elements)
    }
    /*ItemStack Remove*/

    /*ItemMeta Setter*/
    override fun setLore(lore: List<String>): IItemBuilder = apply { itemMeta.lore = lore }

    override fun setDisplayName(displayName: String): IItemBuilder = apply { itemMeta.displayName = displayName }

    override fun setUnbreakable(unbreakable: Boolean): IItemBuilder =
        apply { itemMeta.spigot()?.isUnbreakable = unbreakable }
    /*ItemMeta Setter*/

    /*ItemMeta Add*/
    override fun addEnchant(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): IItemBuilder =
        apply { itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction) }

    override fun addItemFlags(vararg itemFlags: ItemFlag): IItemBuilder = apply { itemMeta.addItemFlags(*itemFlags) }
    /*ItemMeta Add*/

    /*ItemMeta Remove*/
    override fun removeEnchant(enchantment: Enchantment): IItemBuilder = apply { itemMeta.removeEnchant(enchantment) }

    override fun removeItemFlags(vararg itemFlags: ItemFlag): IItemBuilder =
        apply { itemMeta.removeItemFlags(*itemFlags) }
    /*ItemMeta Remove*/

    /*ItemMeta Clear*/
    override fun clearEnchants(): IItemBuilder = apply { itemMeta.enchants?.clear() }

    override fun clearItemFlags(): IItemBuilder = apply { itemMeta.itemFlags?.clear() }
    /*ItemMeta Clear*/

    /*ItemMeta Hide*/
    override fun addAllItemFlags(): IItemBuilder = apply { itemMeta.addItemFlags(*ItemFlag.values()) }
    /*ItemMeta Hide*/

    /*
     *Extras
     */
    override fun build(): ItemStack {
        itemStack.itemMeta = itemMeta
        return itemStack
    }

}
