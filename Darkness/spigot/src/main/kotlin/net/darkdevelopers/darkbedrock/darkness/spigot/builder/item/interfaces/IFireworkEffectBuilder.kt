/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

import org.bukkit.FireworkEffect

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:53.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IFireworkEffectBuilder : IItemBuilder {

    fun setEffect(fireworkEffect: FireworkEffect): IFireworkEffectBuilder

}