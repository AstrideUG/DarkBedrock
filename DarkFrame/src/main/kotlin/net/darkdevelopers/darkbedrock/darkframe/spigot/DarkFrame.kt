package net.darkdevelopers.darkbedrock.darkframe.spigot

import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.ModulesCommand
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.events.listener.EventsListener
import net.darkdevelopers.darkbedrock.darkness.spigot.plugin.DarkPlugin
import org.bukkit.Bukkit
import java.lang.reflect.Field

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 24.08.2018
 */
class DarkFrame : DarkPlugin() {

    //    internal val injector: Injector = Guice.createInjector(InjectorModule())
    private lateinit var moduleManager: ClassJavaModuleManager

    init {
        KotlinVersion
//        if (!KotlinVersion.CURRENT.isAtLeast(1, 2, 61)) throw IllegalStateException("Current KotlinVersion is to low. Use 1.2.61 or higher")
        instance = this
    }

    override fun onEnable() = onEnable {
        try {
            EventsListener.getSimpleInstance(this)
            moduleManager = ClassJavaModuleManager(dataFolder, arrayOf(a()))
            ModulesCommand(this, mapOf("Class" to moduleManager.classModuleManager, "Java" to moduleManager.javaModuleManager))
        } catch (ex: Exception) {
            ex.printStackTrace()
            System.err.println()
            System.err.println("For security reasons, the server is shutdown!")
            System.err.println("Aus Sicherheitsgründen wird der Server heruntergefahren!")
            System.err.println()
            Bukkit.broadcastMessage(" ")
            Bukkit.broadcastMessage("§cFor security reasons, the server is shutdown!")
            Bukkit.broadcastMessage("§cAus Sicherheitsgründen wird der Server heruntergefahren!")
            Bukkit.broadcastMessage(" ")
            Bukkit.shutdown()
        }
    }

    private fun a(): (Field) -> Unit = {
        if (it.type == javaClass) it.set(javaClass, this)
    }

    companion object {
        lateinit var instance: DarkFrame
            private set
    }

}

//fun Any.getInjector() = DarkFrame.instance.injector