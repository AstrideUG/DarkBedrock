/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IMapItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta

/**
 * Created on 07.03.2019 02:05.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class MapItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IMapItemBuilder {

    override val itemMeta = super.itemMeta as MapMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setScaling(value: Boolean): IMapItemBuilder = apply { itemMeta.isScaling = value }

}