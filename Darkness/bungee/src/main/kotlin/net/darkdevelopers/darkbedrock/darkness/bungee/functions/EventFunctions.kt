/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.bungee.functions

import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Event
import net.md_5.bungee.api.plugin.PluginManager

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 01:04.
 * Last edit 28.08.2018
 */
fun <O : Event> O.call(pluginManager: PluginManager = ProxyServer.getInstance().pluginManager): O =
    apply { pluginManager.callEvent(this) }
