/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.SkullItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.SECONDARY
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 17.02.2018  16:20.
 * Last edit 03.05.2019
 */
enum class Items(val itemStack: ItemStack) {

    LEAVE(
        SkullItemBuilder(
            Material.SKULL_ITEM,
            1
        ).setOwner(
            "http://textures.minecraft.net/texture/1b6f1a25b6bc199946472aedb370522584ff6f4e83221e5946bd2e41b5ca13b",
            "MHF_ArrowRight"
        ).setName("${SECONDARY}Zurück zur Lobby").build()
    ),
    CHEST(
        SkullItemBuilder(
            Material.SKULL_ITEM,
            1
        ).setOwner(
            "http://textures.minecraft.net/texture/6f68d509b5d1669b971dd1d4df2e47e19bcb1b33bf1a7ff1dda29bfc6f9ebf",
            "MHF_Chest"
        ).build()
    ),
    TEAMS(ItemBuilder(Material.ENDER_CHEST).setName("${SECONDARY}Teams").build()),
    TELEPORTER(ItemBuilder(Material.COMPASS).setName("${SECONDARY}Teleporter").build()),
    SETTINGS(ItemBuilder(Material.PAPER).setName("${SECONDARY}Settings").build()),
    MAP_VOTE(ItemBuilder(Material.PAPER).setName("${SECONDARY}Map Vote").build()),
    KITS(ItemBuilder(CHEST.itemStack).setName("${SECONDARY}Kits").build()),
    ACHIEVEMENTS(ItemBuilder(CHEST.itemStack).setName("${SECONDARY}Achievements").build());

    val displayName: String get() = itemStack.itemMeta.displayName

    enum class Settings(val itemStack: ItemStack) {

        DECO(ItemBuilder(Material.HOPPER).setName(Items.SETTINGS.displayName).build()),
        MAP_VOTE(ItemBuilder(Items.MAP_VOTE.itemStack).setType(Material.MAP).build()),
        START(ItemBuilder(Material.ENDER_PEARL).setName("${SECONDARY}Start").build()),
        FORCE_MAP(ItemBuilder(Material.PAPER).setName("${SECONDARY}ForceMap").build());

        val displayName: String get() = itemStack.itemMeta.displayName

    }
}
