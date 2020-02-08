/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.listener

import net.darkdevelopers.darkbedrock.darkness.spigot.listener.interfaces.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.register as register0
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.unregister as unregister0

/**
 * Created on 02.06.2018 19:01.
 * @author Lars Artmann | LartyHD
 */
@Suppress("LeakingThis")
open class Listener @JvmOverloads constructor(
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