/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import org.bukkit.ChatColor

enum class Colors(var chatColor: ChatColor) {
    /**
     * Wird bei SEHR wichtigen Sachen verwendet
     */
    PRIMARY(ChatColor.AQUA),
    /**
     * Wird bei Inventar-Namen, Item-Namen und Namen für Entitys verwendet
     */
    SECONDARY(ChatColor.BLUE),
    /**
     * Wird bei wichtigen Sachen (vor allem in Texten) verwendet
     */
    IMPORTANT(ChatColor.WHITE),
    /**
     * Wird bei Texten verwendet
     */
    TEXT(ChatColor.GRAY),
    /**
     * Für Prefixe
     */
    EXTRA(ChatColor.BOLD),
    /**
     * Für Designs
     */
    DESIGN(ChatColor.STRIKETHROUGH),
    RESET(ChatColor.RESET);

    override fun toString(): String = this.chatColor.toString()
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 10:19.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun ChatColor?.orImportant(): ChatColor = this ?: Colors.IMPORTANT.chatColor
