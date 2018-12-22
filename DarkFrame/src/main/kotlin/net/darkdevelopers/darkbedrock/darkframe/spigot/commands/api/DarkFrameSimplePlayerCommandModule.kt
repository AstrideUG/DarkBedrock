package net.darkdevelopers.darkbedrock.darkframe.spigot.commands.api

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.SimplePlayerCommandModule

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.12.2018 05:27.
 * Current Version: 1.0 (22.12.2018 - 22.12.2018)
 */
abstract class DarkFrameSimplePlayerCommandModule(defaultCommandName: String) :
    SimplePlayerCommandModule(defaultCommandName) {

    override val command: () -> SimplePlayerCommandModule.PlayerCommand = { PlayerCommand(DarkFrame.instance) }

}