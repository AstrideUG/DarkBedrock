/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.plugin

import net.darkdevelopers.darkbedrock.darkness.universal.plugin.interfaces.DarkPlugin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 15.12.2017 03:21.
 * Last edit 03.07.2018
 */
@Suppress("unused")
open class DarkPlugin : JavaPlugin(), DarkPlugin {

    final override val parameter: MutableMap<String, String> = HashMap()
    override var loadTime = 0L
    override var enableTime = 0L
    private val console = Bukkit.getConsoleSender()
            ?: throw NullPointerException("Bukkit ConsoleSender can not be null")

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

    override fun onLoad() = onLoad { }

    override fun onEnable() = onEnable { }

    override fun onDisable() = onDisable { }


    override fun sendMessage(message: String) = console.sendMessage(message)

}
