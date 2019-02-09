/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.interfaces.IItemBuilder
import org.apache.commons.codec.binary.Base64
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.block.banner.Pattern
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

@Suppress("unused")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 08:37.
 * Last edit 07.07.2018
 */
data class ItemBuilder(private val itemStack: ItemStack) : IItemBuilder {

    private val itemMeta: ItemMeta? = itemStack.itemMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    constructor(material: Material) : this(ItemStack(material))

    constructor(material: Material, amount: Int) : this(ItemStack(material, amount))

    constructor(material: Material, damage: Short) : this(ItemStack(material, 1, damage))

    constructor(material: Material, amount: Int, damage: Short) : this(ItemStack(material, amount, damage))

    override fun setType(material: Material) = setMaterial(material)

    override fun setMaterial(material: Material): IItemBuilder {
        itemStack.type = material
        return this
    }

    override fun setAmount(amount: Int): IItemBuilder {
        itemStack.amount = amount
        return this
    }

    override fun setDamage(damage: Short) = setDurability(damage)

    override fun setDurability(durability: Short): IItemBuilder {
        itemStack.durability = durability
        return this
    }
    /*ItemStack Setter*/

    /*ItemStack Add*/
    override fun addEnchantment(enchantment: Enchantment, level: Int): IItemBuilder {
        itemStack.addEnchantment(enchantment, level)
        return this
    }

    override fun addEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder {
        itemStack.addEnchantments(enchantments)
        return this
    }

    override fun addUnsafeEnchantment(enchantment: Enchantment, level: Int): IItemBuilder {
        itemStack.addUnsafeEnchantment(enchantment, level)
        return this
    }

    override fun addUnsafeEnchantments(enchantments: Map<Enchantment, Int>): IItemBuilder {
        itemStack.addUnsafeEnchantments(enchantments)
        return this
    }

    override fun addLore(lore: Collection<String>): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.addAll(lore)
        itemMeta?.lore = itemLore
        return this
    }

    override fun addLore(index: Int, lore: Collection<String>): IItemBuilder {
        if (itemMeta?.lore === null) itemMeta?.lore = mutableListOf()
        itemMeta?.lore?.addAll(index, lore)
        itemMeta?.lore = itemMeta?.lore
        return this
    }

    override fun addLore(vararg lore: String): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.addAll(lore)
        itemMeta?.lore = itemLore
        return this
    }

    override fun addLore(lore: String): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.add(lore)
        itemMeta?.lore = itemLore
        return this
    }

    override fun addLore(index: Int, lore: String): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.add(index, lore)
        itemMeta?.lore = itemLore
        return this
    }
    /*ItemStack Add*/

    /*ItemStack Remove*/
    override fun removeEnchantment(enchantment: Enchantment): IItemBuilder {
        itemStack.removeEnchantment(enchantment)
        return this
    }

    override fun removeLore(lore: List<String>): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.removeAll(lore)
        itemMeta?.lore = itemLore
        return this
    }

    override fun removeLore(index: Int): IItemBuilder {
        val itemLore = itemMeta?.lore ?: mutableListOf()
        itemLore.removeAt(index)
        itemMeta?.lore = itemLore
        return this
    }
    /*ItemStack Remove*/

    /*
     *ItemMeta Setter
     */
    override fun setLore(lore: List<String>): IItemBuilder {
        itemMeta?.lore = lore
        return this
    }

    override fun setLore(lore: String): IItemBuilder {
        itemMeta?.lore = listOf(lore)
        return this
    }

    override fun setName(name: String) = setDisplayName(name)

    @Suppress("MemberVisibilityCanBePrivate")
    override fun setDisplayName(displayName: String): IItemBuilder {
        itemMeta?.displayName = displayName
        return this
    }

    override fun setBreakable(): IItemBuilder {
        itemMeta?.spigot()?.isUnbreakable = false
        return this
    }

    override fun setUnbreakable() = setUnbreakable(true)

    override fun setUnbreakable(unbreakable: Boolean): IItemBuilder {
        itemMeta?.spigot()?.isUnbreakable = unbreakable
        return this
    }
    /*
     *ItemMeta Setter
     */

    /*
     *ItemMeta Add
     */
    override fun addEnchant(enchantment: Enchantment, level: Int): IItemBuilder {
        itemMeta?.addEnchant(enchantment, level, true)
        return this
    }

    override fun addEnchant(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): IItemBuilder {
        itemMeta?.addEnchant(enchantment, level, ignoreLevelRestriction)
        return this
    }

    override fun addItemFlags(vararg itemFlags: ItemFlag): IItemBuilder {
        itemMeta?.addItemFlags(*itemFlags)
        return this
    }
    /*
     *ItemMeta Add
     */

    /*
     *ItemMeta Remove
     */
    override fun removeEnchant(enchantment: Enchantment): IItemBuilder {
        itemMeta?.removeEnchant(enchantment)
        return this
    }

    override fun removeItemFlags(vararg itemFlags: ItemFlag): IItemBuilder {
        itemMeta?.removeItemFlags(*itemFlags)
        return this
    }
    /*
     *ItemMeta Remove
     */

    /*
     *ItemMeta Clear
     */
    override fun clearEnchants(): IItemBuilder {
        itemMeta?.enchants?.clear()
        return this
    }

    override fun clearItemFlags(): IItemBuilder {
        itemMeta?.itemFlags?.clear()
        return this
    }
    /*
     *ItemMeta Clear
     */

    /*
     *ItemMeta Hide
     */
    override fun addAllItemFlags(): IItemBuilder {
        itemMeta?.addItemFlags(*ItemFlag.values())
        return this
    }
    /*
     *ItemMeta Hide
     */

    /*
     *BannerMeta Setter
     */
    override fun setBaseColor(dyeColor: DyeColor): IItemBuilder = ignoreClassCastException {
        (itemMeta as BannerMeta).baseColor = dyeColor
    }

    override fun setPatterns(patterns: List<Pattern>): IItemBuilder = ignoreClassCastException {
        (itemMeta as BannerMeta).patterns = patterns
    }

    override fun setPattern(index: Int, pattern: Pattern): IItemBuilder = ignoreClassCastException {
        (itemMeta as BannerMeta).setPattern(index, pattern)
    }
    /*
     *BannerMeta Setter
     */

    /*
     *BannerMeta Add
     */
    override fun addPattern(pattern: Pattern): IItemBuilder = ignoreClassCastException {
        (itemMeta as BannerMeta).addPattern(pattern)
    }
    /*
     *BannerMeta Add
     */

    /*
     *BannerMeta Remove
     */
    override fun removePattern(i: Int): IItemBuilder = ignoreClassCastException {
        (itemMeta as BannerMeta).removePattern(i)
    }
    /*
     *BannerMeta Remove
     */

    /*
     *BlockStateMeta Setter
     */
    override fun setBlockState(blockState: BlockState): IItemBuilder = ignoreClassCastException {
        (itemMeta as BlockStateMeta).blockState = blockState
    }
    /*
     *BlockStateMeta Setter
     */

    /*
     *BookMeta Setter
     */
    override fun setTitle(title: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).title = title
    }

    override fun setAuthor(author: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).author = author
    }

    override fun setPage(index: Int, page: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).setPage(index, page)
    }

    override fun setPages(pages: List<String>): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).pages = pages
    }

    override fun setPages(vararg pages: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).setPages(*pages)

    }
    /*
     *BookMeta Setter
     */

    /*
     *BookMeta Add
     */
    override fun addPage(vararg page: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as BookMeta).addPage(*page)
    }
    /*
     *BookMeta Add
     */

    /*
     *FireworkEffectMeta Setter
     */
    override fun setFireworkEffectMetaEffect(fireworkEffect: FireworkEffect): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkEffectMeta).effect = fireworkEffect
    }
    /*
     *FireworkEffectMeta Setter
     */

    /*
     *FireworkMeta Setter
     */
    override fun setFireworkMetaEffect(fireworkEffect: FireworkEffect): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkMeta).addEffect(fireworkEffect)
    }

    override fun setFireworkMetaEffect(vararg fireworkEffect: FireworkEffect): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkMeta).addEffects(*fireworkEffect)
    }

    override fun setFireworkMetaEffect(fireworkEffect: Iterable<FireworkEffect>): IItemBuilder =
        ignoreClassCastException {
            (itemMeta as FireworkMeta).addEffects(fireworkEffect)

        }

    override fun setPower(power: Int): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkMeta).power = power
    }
    /*
     *FireworkMeta Setter
     */

    /*
     *FireworkMeta Remove
     */
    override fun removeEffect(effect: Int): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkMeta).removeEffect(effect)
    }
    /*
     *FireworkMeta Remove
     */

    /*
     *FireworkMeta Clear
     */
    override fun clearEffects(): IItemBuilder = ignoreClassCastException {
        (itemMeta as FireworkMeta).clearEffects()
    }
    /*
     *FireworkMeta Clear
     */

    /*
     *LeatherArmorMeta Setter
     */
    override fun setColor(color: Color): IItemBuilder = ignoreClassCastException {
        (itemMeta as LeatherArmorMeta).color = color
    }
    /*
     *LeatherArmorMeta Setter
     */

    /*
     *MapMeta Setter
     */
    override fun setScaling(value: Boolean): IItemBuilder = ignoreClassCastException {
        (itemMeta as MapMeta).isScaling = value
    }
    /*
     *MapMeta Setter
     */

    /*
     *PotionMeta Setter
     */
    override fun setMainEffect(potionEffectType: PotionEffectType): IItemBuilder = ignoreClassCastException {
        (itemMeta as PotionMeta).setMainEffect(potionEffectType)
    }
    /*
     *PotionMeta Setter
     */

    /*
     *PotionMeta Add
     */
    override fun addCustomEffect(potionEffect: PotionEffect, overwrite: Boolean): IItemBuilder =
        ignoreClassCastException {
            (itemMeta as PotionMeta).addCustomEffect(potionEffect, overwrite)
        }
    /*
     *PotionMeta Add
     */

    /*
     *PotionMeta Remove
     */
    override fun removeCustomEffect(potionEffectType: PotionEffectType): IItemBuilder = ignoreClassCastException {
        (itemMeta as PotionMeta).removeCustomEffect(potionEffectType)
    }
    /*
     *PotionMeta Remove
     */

    /*
     *PotionMeta Clear
     */
    override fun clearCustomEffects(): IItemBuilder = ignoreClassCastException {
        (itemMeta as PotionMeta).clearCustomEffects()
    }
    /*
     *PotionMeta Clear
     */

    /*
     *SkullMeta Setter
     */
    override fun setOwner(owner: String): IItemBuilder = ignoreClassCastException {
        (itemMeta as SkullMeta).owner = owner
    }

    override fun setOwner(url: String, name: String): IItemBuilder = ignoreClassCastException {
        if (url.isEmpty()) return@ignoreClassCastException
        val gameProfile = GameProfile(UUID.randomUUID(), name)
        gameProfile.properties.put(
            "textures",
            Property(
                "textures",
                String(Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).toByteArray()))
            )
        )
        ReflectUtils.setValue(itemMeta ?: return@ignoreClassCastException, "profile", gameProfile)
    }
    /*
     *SkullMeta Setter
     */

    /*
     *Extras
     */
    override fun build(): ItemStack {
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    private fun ignoreClassCastException(lambda: () -> Unit): IItemBuilder = try {
        lambda()
        this
    } catch (ignored: ClassCastException) {
        this
    }

}
