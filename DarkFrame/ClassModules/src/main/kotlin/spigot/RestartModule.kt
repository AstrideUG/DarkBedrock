/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import org.bukkit.Bukkit
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 06:46.
 * Last edit 03.08.2018
 */
class RestartModule : Module {

	override val description: ModuleDescription = ModuleDescription("RestartModule", "1.0", "Lars Artmann | LartyHD", "")

	override fun start() {
		thread {
			val start = convert()
			try {
				while (true) {
					if (convert() > start) {
						for (i in 1..10) {
							Bukkit.broadcastMessage("§4§lDer Server Startet ${if (i == 1) "einer Sekunde" else "$i Sekunden"} kurz neu")
							Thread.sleep(1000)
						}
						Bukkit.shutdown()
					}
					Thread.sleep(10000)
				}
			} catch (ignored: InterruptedException) {
			}

		}
	}

	private fun convert() = TimeUnit.DAYS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)

}