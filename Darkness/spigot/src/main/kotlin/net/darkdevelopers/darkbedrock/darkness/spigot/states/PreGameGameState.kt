package net.darkdevelopers.darkbedrock.darkness.spigot.states

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.PreGameCountdown
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.game.PreGameListener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 13:13.
 * Last edit 06.07.2018
 */
class PreGameGameState(override val listener: PreGameListener, override val countdown: PreGameCountdown) : GameState