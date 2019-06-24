/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IBannerItemBuilder
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:45.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class BannerItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IBannerItemBuilder {

    override val itemMeta = super.itemMeta as BannerMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setBaseColor(dyeColor: DyeColor): BannerItemBuilder = apply { itemMeta.baseColor = dyeColor }

    override fun setPatterns(patterns: List<Pattern>): BannerItemBuilder = apply { itemMeta.patterns = patterns }

    override fun setPattern(index: Int, pattern: Pattern): BannerItemBuilder =
        apply { itemMeta.setPattern(index, pattern) }

    override fun addPattern(pattern: Pattern): BannerItemBuilder = apply { itemMeta.addPattern(pattern) }

    override fun removePattern(i: Int): BannerItemBuilder = apply { itemMeta.removePattern(i) }

}