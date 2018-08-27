/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.universal.functions


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 01:04.
 * Last edit 28.08.2018
 */
fun <O : org.bukkit.event.Event> O.call(): O {
    org.bukkit.Bukkit.getPluginManager().callEvent(this)
    return this
}

fun <O : net.md_5.bungee.api.plugin.Event> O.call(): O {
    net.md_5.bungee.api.ProxyServer.getInstance().pluginManager.callEvent(this)
    return this
}
