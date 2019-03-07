/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IBlockStateItemBuilder
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:46.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class BlockStateItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IBlockStateItemBuilder {

    override val itemMeta = super.itemMeta as BlockStateMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setBlockState(blockState: BlockState): IBlockStateItemBuilder =
        apply { itemMeta.blockState = blockState }

}