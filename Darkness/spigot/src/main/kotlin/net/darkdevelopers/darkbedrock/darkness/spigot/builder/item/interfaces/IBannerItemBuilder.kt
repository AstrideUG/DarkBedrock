/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import org.bukkit.DyeColor
import org.bukkit.block.banner.Pattern

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:34.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IBannerItemBuilder : IItemBuilder {

    fun setBaseColor(dyeColor: DyeColor): IBannerItemBuilder

    fun setPatterns(patterns: List<Pattern>): IBannerItemBuilder

    fun setPattern(index: Int, pattern: Pattern): IBannerItemBuilder

    fun addPattern(pattern: Pattern): IBannerItemBuilder

    fun removePattern(i: Int): IBannerItemBuilder

}