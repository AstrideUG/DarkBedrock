package net.darkdevelopers.darkbedrock.darkness.spigot.states

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.LobbyCountdown
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.game.LobbyListener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 13:13.
 * Last edit 06.07.2018
 */
class LobbyGameState(override val listener: LobbyListener, override val countdown: LobbyCountdown) : GameState