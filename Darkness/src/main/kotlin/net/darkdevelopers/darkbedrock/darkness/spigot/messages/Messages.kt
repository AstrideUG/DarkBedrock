package net.darkdevelopers.darkbedrock.darkness.spigot.messages

import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 03:14.
 * Last edit 04.07.2018
 */
enum class Messages(var message: String) {
    NAME("DarkFrame"),
    SERVER_NAME("$PRIMARY${EXTRA}Darkblocks§f$EXTRA.$PRIMARY${EXTRA}net$RESET"),
    PREFIX("$PRIMARY$EXTRA$NAME$IMPORTANT | $RESET"),
    PLAYER_NOT_ONLINE("$PREFIX${TEXT}Der Spieler ist nicht Online$RESET");

    override fun toString() = this.message
}
