/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot

import de.astride.darkbedrock.apis.modules.common.loader.ClassModuleLoader
import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.ModulesCommand
import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.OldModulesCommand
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.events.listener.EventsListener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import net.darkdevelopers.darkbedrock.darkness.spigot.plugin.DarkPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import java.io.File
import kotlin.properties.Delegates

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 19.12.2018
 */
class DarkFrame : DarkPlugin() {

    private var moduleManager: ClassJavaModuleManager by Delegates.notNull()
    private val messages =
        SpigotGsonMessages(GsonConfig(ConfigData(dataFolder, "config.json")).load()).availableMessages

    init {
//        if (!KotlinVersion.CURRENT.isAtLeast(1, 2, 61)) throw IllegalStateException("Current KotlinVersion is to low. Use 1.2.61 or higher")
        instance = this
    }

    override fun onEnable() = security {
        onEnable {
            EventsListener.getSimpleInstance(this)

            //Old Module System
            println("Enable Old Module System")
            moduleManager = ClassJavaModuleManager(File("$dataFolder${File.separator}old"))
            OldModulesCommand(
                this,
                mapOf("Class" to moduleManager.classModuleManager, "Java" to moduleManager.javaModuleManager)
            )
            println("Enabled Old Module System")

            //New Module System
            println("Enable New Module System")
            val directory = File("$dataFolder${File.separator}modules")
            val loader = setOf(ClassModuleLoader(directory)/*, JavaModuleLoader(directory)*/)
            ModulesCommand(this, loader, messages.map { it.key to it.value.firstOrNull() }.toMap())
            loader.forEach {
                it.detectModules()
                it.loadModules()
            }
            println("Enabled New Module System")
        }
    }

    companion object {
        var instance: DarkFrame by Delegates.notNull()
            private set

        /**
         * @author Lars Artmann | LartyHD
         *
         * Stops the Server when a [Throwable] is caught
         *
         * @since 25.10.2018
         */
        fun security(action: () -> Unit): Unit = try {
            action()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            System.err.println()
            System.err.println("For security reasons, the server is shutdown!")
            System.err.println("Aus Sicherheitsgründen wird der Server heruntergefahren!")
            System.err.println()
            Bukkit.broadcastMessage(" ")
            Bukkit.broadcastMessage("${ChatColor.RED}For security reasons, the server is shutdown!")
            Bukkit.broadcastMessage("${ChatColor.RED}Aus Sicherheitsgründen wird der Server heruntergefahren!")
            Bukkit.broadcastMessage(" ")
            Bukkit.shutdown()
        }
    }

}

