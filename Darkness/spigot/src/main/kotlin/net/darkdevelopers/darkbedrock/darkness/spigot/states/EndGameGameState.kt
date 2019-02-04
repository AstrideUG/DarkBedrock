package net.darkdevelopers.darkbedrock.darkness.spigot.states

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.EndGameCountdown
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.game.EndGameListener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 13:13.
 * Last edit 06.07.2018
 */
class EndGameGameState(override val listener: EndGameListener, override val countdown: EndGameCountdown) : GameState