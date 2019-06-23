/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask
import java.util.concurrent.TimeUnit

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 21:18.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
fun Plugin.schedule(
    async: Boolean = false,
    delay: Long? = null,
    period: Long? = null,
    unit: TimeUnit? = null,
    callback: BukkitTask.() -> Unit
): BukkitTask {
    lateinit var task: BukkitTask
    task = {
        val function = { task.callback() }
        val scheduler = server.scheduler
        @Suppress("NAME_SHADOWING")
        var localDelay = delay ?: 0
        localDelay = unit?.toSeconds(localDelay)?.let { it * 20 } ?: localDelay
        when {
            period != null -> {
                @Suppress("NAME_SHADOWING")
                val period = unit?.toSeconds(period)?.let { it * 20 } ?: period
                if (async) scheduler.runTaskTimerAsynchronously(this, function, localDelay, period)
                else scheduler.runTaskTimer(this, function, localDelay, period)
            }
            delay != null -> if (async) scheduler.runTaskLaterAsynchronously(this, function, localDelay)
            else scheduler.runTaskLater(this, function, localDelay)
            async -> scheduler.runTaskAsynchronously(this, function)
            else -> scheduler.runTask(this, function)
        }
    }()
    return task
}