/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import org.bukkit.ChatColor

enum class Colors(val chatColor: ChatColor) {
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
    TEXT(ChatColor.GREEN),
    /**
     * Für Prefixe
     */
    EXTRA(ChatColor.BOLD),
    /**
     * Für Designs
     */
    DESIGN(ChatColor.STRIKETHROUGH),
    RESET(ChatColor.RESET);

    override fun toString() = this.chatColor.toString()
}
