/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IPotinItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:48.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class PotionItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IPotinItemBuilder {

    override val itemMeta = super.itemMeta as PotionMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setMainEffect(potionEffectType: PotionEffectType): IItemBuilder =
        apply { itemMeta.setMainEffect(potionEffectType) }

    override fun addCustomEffect(potionEffect: PotionEffect, overwrite: Boolean): IItemBuilder =
        apply { itemMeta.addCustomEffect(potionEffect, overwrite) }

    override fun removeCustomEffect(potionEffectType: PotionEffectType): IItemBuilder =
        apply { itemMeta.removeCustomEffect(potionEffectType) }

    override fun clearCustomEffects(): IItemBuilder = apply { itemMeta.clearCustomEffects() }

}
