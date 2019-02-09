package net.darkdevelopers.darkbedrock.darkness.spigot.states

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.SaveTimeCountdown
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.game.SaveTimeListener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 13:13.
 * Last edit 06.07.2018
 */
class SaveGameGameState(override val listener: SaveTimeListener, override val countdown: SaveTimeCountdown) : GameState