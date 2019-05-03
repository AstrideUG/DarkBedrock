/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.plugin

import net.darkdevelopers.darkbedrock.darkness.general.plugin.interfaces.DarkPlugin
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 15.12.2017 03:21.
 * Last edit 03.05.2019
 */
@Suppress("unused")
open class DarkPlugin : JavaPlugin(), DarkPlugin {

    final override val parameter: MutableMap<String, String> = HashMap()
    override var loadTime: Long = 0L
    override var enableTime: Long = 0L
    private val console: ConsoleCommandSender = server.consoleSender!!

    init {
        parameter["Name"] = description.name.toString()
        parameter["Main"] = description.main.toString()
        parameter["Version"] = description.version.toString()
        if (description.authors.isNotEmpty()) parameter["Authors"] = description.authors.toString()
        if (description.description != null) parameter["Description"] = description.description.toString()
        if (description.website != null) parameter["Website"] = description.website.toString()
        if (description.commands != null) parameter["Commands"] = description.commands.toString()
        if (description.prefix != null) parameter["Prefix"] = description.prefix.toString()
        if (description.depend.isNotEmpty()) parameter["Depend"] = description.depend.toString()
        if (description.softDepend.isNotEmpty()) parameter["SoftDepend"] = description.softDepend.toString()
    }

    override fun onLoad(): Unit = onLoad { }

    override fun onEnable(): Unit = onEnable { }

    override fun onDisable(): Unit = onDisable { }


    override fun sendMessage(message: String): Unit = console.sendMessage(message)

}
