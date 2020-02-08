/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.ILeatherArmorItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

/**
 * Created on 07.03.2019 01:47.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class LeatherArmorItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), ILeatherArmorItemBuilder {

    override val itemMeta = super.itemMeta as LeatherArmorMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setColor(color: Color): ILeatherArmorItemBuilder = apply { itemMeta.color = color }

}