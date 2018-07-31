package net.darkdevelopers.darkbedrock.darkframe.spigot

import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.ModulesCommand
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.events.listener.EventsListener
import net.darkdevelopers.darkbedrock.darkness.spigot.plugin.DarkPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 03.07.2018
 */
class DarkFrame : DarkPlugin() {

    private lateinit var moduleManager: ClassJavaModuleManager

    init {
        instance = this
    }

    override fun onEnable() = onEnable {
        EventsListener(this)
        moduleManager = ClassJavaModuleManager(dataFolder)
        ModulesCommand(this, mapOf(Pair("Class", moduleManager.classModuleManager), Pair("Java", moduleManager.javaModuleManager)))
    }

    companion object {
        lateinit var instance: DarkFrame
            private set
    }

}
