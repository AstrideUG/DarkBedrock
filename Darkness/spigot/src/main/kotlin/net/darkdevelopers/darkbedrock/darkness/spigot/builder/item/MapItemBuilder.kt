/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IMapItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 02:05.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class MapItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IMapItemBuilder {

    override val itemMeta = super.itemMeta as MapMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setScaling(value: Boolean): IMapItemBuilder = apply { itemMeta.isScaling = value }

}