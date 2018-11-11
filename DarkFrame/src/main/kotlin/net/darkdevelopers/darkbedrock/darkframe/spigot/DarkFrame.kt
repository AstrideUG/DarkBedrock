package net.darkdevelopers.darkbedrock.darkframe.spigot

import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.ModulesCommand
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.events.listener.EventsListener
import net.darkdevelopers.darkbedrock.darkness.spigot.plugin.DarkPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import kotlin.properties.Delegates

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 25.10.2018
 */
class DarkFrame : DarkPlugin() {

	private var moduleManager: ClassJavaModuleManager by Delegates.notNull()

	init {
//        if (!KotlinVersion.CURRENT.isAtLeast(1, 2, 61)) throw IllegalStateException("Current KotlinVersion is to low. Use 1.2.61 or higher")
		instance = this
	}

	override fun onEnable() = security {
		onEnable {
			EventsListener.getSimpleInstance(this)
			moduleManager = ClassJavaModuleManager(dataFolder)
			ModulesCommand(this, mapOf("Class" to moduleManager.classModuleManager, "Java" to moduleManager.javaModuleManager))
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

