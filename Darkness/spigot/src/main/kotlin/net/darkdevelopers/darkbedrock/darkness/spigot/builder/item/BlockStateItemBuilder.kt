/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IBlockStateItemBuilder
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta

/**
 * Created on 07.03.2019 01:46.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class BlockStateItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IBlockStateItemBuilder {

    override val itemMeta = super.itemMeta as BlockStateMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setBlockState(blockState: BlockState): IBlockStateItemBuilder =
        apply { itemMeta.blockState = blockState }

}