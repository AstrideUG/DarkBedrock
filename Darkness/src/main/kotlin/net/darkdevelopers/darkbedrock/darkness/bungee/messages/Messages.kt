package net.darkdevelopers.darkbedrock.darkness.bungee.messages

import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 03:14.
 * Last edit 05.07.2018
 */
enum class Messages(private val message: String) {
	NAME("DarkFrame"),
	PREFIX("$PRIMARY$EXTRA$NAME$IMPORTANT | §r"),
	PLAYER_NOT_ONLINE("$PREFIX${TEXT}Der Spieler ist nicht Online"),
	SERVER_NAME("$PRIMARY${EXTRA}DarkBlocks§f$EXTRA.$PRIMARY${EXTRA}Net");

	override fun toString() = this.message
}
