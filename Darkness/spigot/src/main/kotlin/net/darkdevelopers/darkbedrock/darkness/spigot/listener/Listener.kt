package net.darkdevelopers.darkbedrock.darkness.spigot.listener

import net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces.Listener
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 19:01.
 * Last edit 02.05.2019
 */
@Suppress("LeakingThis")
open class Listener(
    protected val javaPlugin: JavaPlugin,
    private val pluginManager: PluginManager = Bukkit.getPluginManager(),
    override val permissionMessage: String = ""
) : Listener {

    init {
        init()
        register()
    }

    open fun init() {

    }

    final override fun register(): Unit = pluginManager.registerEvents(this, javaPlugin)

    final override fun unregister(): Unit = HandlerList.unregisterAll(this)

    final override fun reload() {
        unregister()
        register()
    }

}