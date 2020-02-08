/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.bungee.messages

import net.md_5.bungee.api.ChatColor

enum class Colors constructor(private val chatColor: ChatColor) {
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
	IMPORTANT(ChatColor.DARK_GRAY),
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
	DESIGN(ChatColor.STRIKETHROUGH);

	override fun toString() = this.chatColor.toString()
}
