/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.bungee.plugin

import net.darkdevelopers.darkbedrock.darkness.universal.plugin.interfaces.DarkPlugin
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Plugin

/**
 * Created by LartyHD on 03.07.2018 07:47.
 * Last edit 03.07.2018
 */
@Suppress("unused")
open class DarkPlugin : Plugin(), DarkPlugin {

	final override val parameter: MutableMap<String, String> = mutableMapOf()
	override var loadTime = 0L
	override var enableTime = 0L
	private val console = ProxyServer.getInstance().console
			?: throw NullPointerException("BungeeCord console can not be null")

	final override fun onLoad() {
		parameter["Name"] = description.name.toString()
		parameter["Main"] = description.main.toString()
		parameter["Version"] = description.version.toString()
		if (description.author != null) parameter["Author"] = description.author.toString()
		if (description.description != null) parameter["Description"] = description.description.toString()
		if (description.depends.isNotEmpty()) parameter["Depends"] = description.depends.toString()
		if (description.softDepends.isNotEmpty()) parameter["SoftDepends"] = description.softDepends.toString()
		onLoad { }
	}

	override fun onEnable() = onEnable { }

	override fun onDisable() = onDisable { }

	override fun sendMessage(message: String) = console.sendMessage(TextComponent(message))

}
