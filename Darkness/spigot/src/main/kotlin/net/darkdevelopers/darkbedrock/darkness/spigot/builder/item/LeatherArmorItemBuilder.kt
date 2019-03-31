/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.ILeatherArmorItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:47.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class LeatherArmorItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), ILeatherArmorItemBuilder {

    override val itemMeta = super.itemMeta as LeatherArmorMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setColor(color: Color): ILeatherArmorItemBuilder = apply { itemMeta.color = color }

}