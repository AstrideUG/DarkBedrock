package net.darkdevelopers.darkbedrock.darkness.bungee.listener

import net.darkdevelopers.darkbedrock.darkness.bungee.listener.interfaces.Listener
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.api.plugin.PluginManager

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 19:01.
 * Last edit 02.06.2018
 */
open class Listener(
    val plugin: Plugin,
    private val pluginManager: PluginManager = ProxyServer.getInstance().pluginManager,
    override val permissionMessage: String = ""
) : Listener {

    init {
        register()
    }

    final override fun register() = pluginManager.registerListener(plugin, this)

    final override fun unregister() = pluginManager.unregisterListener(this)

    final override fun reload() {
        unregister()
        register()
    }

}