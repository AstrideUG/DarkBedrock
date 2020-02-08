/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IFireworkItemBuilder
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta

/**
 * Created on 07.03.2019 02:07.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class FireworkItemItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IFireworkItemBuilder {

    override val itemMeta = super.itemMeta as FireworkMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setFireworkMetaEffect(fireworkEffect: Iterable<FireworkEffect>): IFireworkItemBuilder =
        apply { itemMeta.addEffects(fireworkEffect) }

    override fun setPower(power: Int): IFireworkItemBuilder = apply { itemMeta.power = power }

    override fun removeEffect(effect: Int): IFireworkItemBuilder = apply { itemMeta.removeEffect(effect) }

    override fun clearEffects(): IFireworkItemBuilder = apply { itemMeta.clearEffects() }
}