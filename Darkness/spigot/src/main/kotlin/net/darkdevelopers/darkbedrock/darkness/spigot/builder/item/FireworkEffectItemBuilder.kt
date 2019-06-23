/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IFireworkEffectBuilder
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkEffectMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:46.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class FireworkEffectItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IFireworkEffectBuilder {

    override val itemMeta = super.itemMeta as FireworkEffectMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setEffect(fireworkEffect: FireworkEffect): IFireworkEffectBuilder =
        apply { itemMeta.effect = fireworkEffect }

}