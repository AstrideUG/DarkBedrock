package net.darkdevelopers.darkbedrock.darkness.spigot.states

import net.darkdevelopers.darkbedrock.darkness.spigot.countdowns.Countdown
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces.Listener


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.07.2018 09:20.
 * Last edit 06.07.2018
 */
interface GameState {

    val listener: Listener
    val countdown: Countdown?

}