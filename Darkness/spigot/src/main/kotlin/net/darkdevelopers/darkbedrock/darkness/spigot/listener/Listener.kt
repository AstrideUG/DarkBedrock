package net.darkdevelopers.darkbedrock.darkness.spigot.listener

import net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.register as register0
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.unregister as unregister0

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 19:01.
 * Last edit 02.05.2019
 */
@Suppress("LeakingThis")
open class Listener(
    protected val javaPlugin: JavaPlugin,
    private val pluginManager: PluginManager = javaPlugin.server.pluginManager,
    override val permissionMessage: String = ""
) : Listener {

    init {
        init()
        register()
    }

    open fun init() {

    }

    final override fun register(): Unit = register0(javaPlugin, pluginManager)

    final override fun unregister(): Unit = unregister0()

    final override fun reload() {
        unregister()
        register()
    }

}