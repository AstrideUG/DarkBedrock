import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.02.2018 18:36.
 * Last edit 07.07.2018
 */
class AutoMessageModule : Module {
	override val description: ModuleDescription = ModuleDescription("AutoMessageModule", "1.0", "Lars Artmann | LartyHD", "This module sends automated messages")

	private val thread = Thread {
		println("[${description.name}] AutoMessage thread started")
		val config = GsonConfig(ConfigData(description.folder, "config.json")).load()
		val list = config.getAs<Array<Array<String>>>("messages")?.toSet()
				?: throw NullPointerException("messages can not be null")
		val delay = config.getAs<Long>("delay") ?: throw NullPointerException("delay can not be null")
		try {
			while (true) list.forEach {
				Thread.sleep(delay)
				it.toSet().forEach { ProxyServer.getInstance().broadcast(TextComponent(it)) }
			}
		} catch (ex: InterruptedException) {
			println("[${description.name}] AutoMessage thread stoped")
		}
	}

	override fun start() = thread.start()

	override fun stop() = thread.interrupt()

}