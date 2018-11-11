/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.countdowns

import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.entity.Player
import kotlin.concurrent.thread

abstract class Countdown internal constructor(internal var seconds: Int) {
	private val startSeconds = seconds
	private lateinit var thread: Thread
	internal var isRunning: Boolean = false

	internal fun setLevel() = Utils.goThroughAllPlayers { setLevel(it) }

	private fun setLevel(player: Player) {
		player.level = seconds
		player.exp = seconds.toFloat() / startSeconds.toFloat()
	}

	internal fun loop(lambda: () -> Unit) {
		thread = loop(1000, lambda)
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

	internal abstract fun finish()
}
