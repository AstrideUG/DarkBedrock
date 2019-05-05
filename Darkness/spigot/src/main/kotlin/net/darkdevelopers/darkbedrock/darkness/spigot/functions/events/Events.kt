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


var plugin: Plugin = Bukkit.getPluginManager().plugins.first()
var priority: EventPriority = EventPriority.NORMAL
val activeListener: MutableMap<String, Listener> = mutableMapOf()

inline fun <reified E : Event> String.updateEvent(value: Boolean, noinline function: (E) -> Unit) {
    val listener = activeListener[this]
    if (listener != null && !value) listener.unregister()
    else if (listener == null && value) addEvent(function)
}

inline fun <reified E : Event> String.addEvent(crossinline function: (E) -> Unit) {
    activeListener[this] = listen(plugin = plugin, priority = priority, function = function)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 11:36.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
inline fun <reified E : Event> listen(
    plugin: Plugin,
    pluginManager: PluginManager = plugin.server.pluginManager,
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    crossinline function: (E) -> Unit
): Listener = object : Listener {}.apply {
    pluginManager.registerEvent(
        E::class.java,
        this,
        priority,
        { _, event -> if (event is E) function(event) },
        plugin,
        ignoreCancelled
    )
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 07:08.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun Listener.unregister(): Unit = HandlerList.unregisterAll(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:26.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
fun Iterable<Listener>.unregister(): Unit = forEach { it.unregister() }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:05.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */
@Suppress("unused")
fun Listener.register(plugin: Plugin, pluginManager: PluginManager = plugin.server.pluginManager): Unit =
    pluginManager.registerEvents(this, plugin)

