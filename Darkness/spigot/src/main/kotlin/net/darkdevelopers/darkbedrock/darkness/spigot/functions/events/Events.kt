/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions.events

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager


var pluginManager: PluginManager = Bukkit.getPluginManager()
var plugin: Plugin = pluginManager.plugins.first()
var priority: EventPriority = EventPriority.NORMAL
val activeListener: MutableMap<String, Listener> = mutableMapOf()

inline fun <reified E : Event> String.updateEvent(value: Boolean, noinline function: (Listener, Event) -> Unit) {
    val listener = activeListener[this]
    if (listener != null && !value) listener.unregister()
    else if (listener == null && value) addEvent<E>(function)
}

inline fun <reified E : Event> String.addEvent(noinline function: (Listener, Event) -> Unit) {
    activeListener[this] = register<E>(plugin, pluginManager, priority, function)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 11:36.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
inline fun <reified E : Event> register(
    plugin: Plugin,
    pluginManager: PluginManager = plugin.server.pluginManager,
    priority: EventPriority = EventPriority.NORMAL,
    noinline function: (Listener, Event) -> Unit
): Listener = object : Listener {}.apply {
    pluginManager.registerEvent(E::class.java, this, priority, function, plugin)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 07:08.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun Listener.unregister(): Unit = HandlerList.unregisterAll(this)

