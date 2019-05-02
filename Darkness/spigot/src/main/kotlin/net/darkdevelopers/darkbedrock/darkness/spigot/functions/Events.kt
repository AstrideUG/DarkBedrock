/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.05.2019 07:08.
 * Current Version: 1.0 (02.05.2019 - 02.05.2019)
 */
fun Listener.unregister(): Unit = HandlerList.unregisterAll(this)