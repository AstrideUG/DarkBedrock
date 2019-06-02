/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

abstract class Countdown internal constructor(var seconds: Int) {
    @Suppress("MemberVisibilityCanBePrivate")
    val startSeconds = seconds
    private lateinit var thread: Thread
    var isRunning: Boolean = false

    internal fun setLevel(): Unit = players.forEach { setLevel(it) }

    private fun setLevel(player: Player) {
        player.level = seconds
        player.exp = seconds.toFloat() / startSeconds.toFloat()
    }

    internal fun loop(lambda: () -> Unit) {
        thread = loop(TimeUnit.SECONDS.toMillis(1), lambda)
    }

    internal fun loop(sleep: Long, lambda: () -> Unit) = thread {
        println("[${javaClass.simpleName}] Thread started")
        try {
            while (true) {
                lambda()
                Thread.sleep(sleep)
            }
        } catch (ex: InterruptedException) {
            println("[${javaClass.simpleName}] Thread stoped")
        }
    }

    abstract fun start()

    abstract fun stop()

    internal fun defaultStop(countdownName: String) = if (isRunning) {
        thread.interrupt()
        isRunning = false
        seconds = 0
        setLevel()
        seconds = startSeconds
    } else System.err.println("The $countdownName countdown should be stopped even though it is not running")

}
