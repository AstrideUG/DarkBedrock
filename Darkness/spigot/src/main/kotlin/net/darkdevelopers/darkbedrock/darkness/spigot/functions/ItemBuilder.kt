/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IItemBuilder
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:28.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.05.2019 19:28.
 * Current Version: 1.0 (12.05.2019 - 12.05.2019)
 */
fun IItemBuilder.glow(): IItemBuilder = addEnchant(Enchantment.LUCK, 10).addItemFlags(ItemFlag.HIDE_ENCHANTS)
