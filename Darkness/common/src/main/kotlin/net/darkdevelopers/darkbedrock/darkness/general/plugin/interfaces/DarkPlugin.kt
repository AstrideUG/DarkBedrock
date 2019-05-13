/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.general.plugin.interfaces

import java.text.DecimalFormat

/**
 * Created by LartyHD on 15.12.2017 03:21.
 * Last edit 13.05.2019
 */
@Suppress("unused")
interface DarkPlugin {
    val parameter: Map<String, String>
    var loadTime: Long
    var enableTime: Long

    fun invoke() {
        if (parameter["Name"] == null) throw NullPointerException("gameName in the parameter Map can not be null")
    }

    fun onLoad(lambda: () -> Unit) {
        this.loadTime = System.currentTimeMillis()
        sendPluginInfos("Load", lambda) { sendLoadTimeMessage() }
    }

    fun onEnable(lambda: () -> Unit) {
        this.enableTime = System.currentTimeMillis()
        sendPluginInfos("Enable", lambda) { sendEnableTimeMessage() }
    }

    fun onDisable(lambda: () -> Unit) = sendPluginInfos(
        "Disable",
        lambda
    ) { sendMessage("§a${parameter["Name"]} lief seit dem Start ${format(enableTime)}ms") }

    private fun sendPluginInfos(key: String, lambda: () -> Unit, end: () -> Unit) {
        sendMessage("§2==================================================================================================")
        sendMessage("")
        sendMessage("§a$key Plugin§8...")
        sendMessage("§8--------------------------------------------------------------------------------------------------")
//        sendMessage("§aName§8: ${description.gameName}")
//        sendMessage("§aMain§8: ${description.main}")
//        sendMessage("§aVersion§8: ${description.version}")
        sendParameter()
//        if (description.authors.isNotEmpty()) sendMessage("§aAuthors§8: ${description.authors}")
//        if (description.description != null) sendMessage("§aDescription§8: ${description.description}")
//        if (description.website != null) sendMessage("§aWebsite§8: ${description.website}")
//        if (description.commands != null) sendMessage("§aCommands§8: ${description.commands}")
//        if (description.prefix != null) sendMessage("§aPrefix§8: ${description.prefix}")
//        if (description.depend.size > 0) sendMessage("§aDepend§8: ${description.depend}")
//        if (description.softDepend.size > 0) sendMessage("§aSoftDepend§8: ${description.softDepend}")
        sendMessage("§8--------------------------------------------------------------------------------------------------")
        lambda()
        sendMessage("§a${if (key.endsWith("e")) key + "d" else key + "ed"} Plugin")
        sendMessage("")
        end()
        sendMessage("")
        sendMessage("§2==================================================================================================")
    }

    private fun sendLoadTimeMessage() = sendTimeMessage(loadTime, "geladen")

    private fun sendEnableTimeMessage() = sendTimeMessage(enableTime, "gestartet")

    private fun sendTimeMessage(time: Long, reason: String) =
        sendMessage("§a${parameter["Name"]} wurde in ${format(time)}ms $reason")

    private fun format(long: Long): String = DecimalFormat("#,##0").format(System.currentTimeMillis() - long)

    fun sendParameter() = parameter.keys.forEach { sendMessage("§a$it§8: ${parameter[it]}") }

    fun sendMessage(message: String)
}
